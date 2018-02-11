package project.repositories;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import project.model.Book;
import project.repositories.mappers.BookMapper;

@Repository
public class BookRepository {
	@Autowired
	JdbcTemplate jdbcTemplate;
	SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public List<Book> getAll() {

		String sql = "SELECT k.Id_ksiazki,k.Tytul,k.Rok_wydania,k.ISBN,k.Slowa_kluczowe,k.Dostepnosc,k.Gatunek,\r\n"
				+ "a.Id_autora,a.Imie,a.Nazwisko,a.Komentarz,\r\n"
				+ "(SELECT count(*)-count(wy.Data_zwrotu) From wypozyczenia wy left join ksiazki k1 on k1.Id_ksiazki=wy.Id_ksiazki where k.Id_ksiazki=k1.Id_ksiazki)as Wypozyczona,\r\n"
				+ "w.Id_wydawnictwa,w.Adres,w.Email,w.Kod_pocztowy,w.Miejscowosc,w.Nazwa,w.Telefon \r\n"
				+ "FROM ((ksiazki k INNER JOIN autorzy a ON k.Id_autora=a.Id_autora) INNER JOIN wydawnictwo w ON k.Id_wydawnictwa=w.Id_wydawnictwa);";

		return jdbcTemplate.query(sql, new BookMapper());
	}

	public Book getBook(long id) {
		String sql = "SELECT k.Id_ksiazki,k.Tytul,k.Rok_wydania,k.ISBN,k.Slowa_kluczowe,k.Dostepnosc,k.Gatunek,\r\n"
				+ "a.Id_autora,a.Imie,a.Nazwisko,a.Komentarz,\r\n"
				+ "(SELECT count(*)-count(wy.Data_zwrotu) From wypozyczenia wy left join ksiazki k1 on k1.Id_ksiazki=wy.Id_ksiazki where k.Id_ksiazki=k1.Id_ksiazki)as Wypozyczona,\r\n"
				+ "w.Id_wydawnictwa,w.Adres,w.Email,w.Kod_pocztowy,w.Miejscowosc,w.Nazwa,w.Telefon \r\n"
				+ "FROM ((ksiazki k INNER JOIN autorzy a ON k.Id_autora=a.Id_autora) INNER JOIN wydawnictwo w ON k.Id_wydawnictwa=w.Id_wydawnictwa) where k.Id_ksiazki=?";
		return jdbcTemplate.queryForObject(sql, new BookMapper(), id);
	}

	public void addBook(Book b) {
		
		jdbcTemplate.update(
				"INSERT INTO ksiazki (Tytul,Rok_wydania,ISBN,Slowa_kluczowe,Dostepnosc,Gatunek,Id_autora,Id_wydawnictwa) VALUES(?,?,?,?,?,?,?,?)",
				b.getTitle(), f.format(b.getPublicationDate()), b.getISBN(), b.getKeywords(), b.isAccesible(), b.getType(),
				b.getAuthor().getId(), b.getPublisher().getId());
	}

	public void removeBook(Book b) {
		jdbcTemplate.update("DELETE FROM ksiazki WHERE id_ksiazki=?", b.getId());
	}

	public void updateBook(Book b) {
		jdbcTemplate.update(
				"UPDATE ksiazki SET Tytul=?,Rok_wydania=?,ISBN=?,Slowa_kluczowe=?,Dostepnosc=?,Gatunek=?,Id_autora=?,Id_wydawnictwa=? WHERE Id_ksiazki=?",
				b.getTitle(), f.format(b.getPublicationDate()), b.getISBN(), b.getKeywords(), b.isAccesible(), b.getType(),
				b.getAuthor().getId(), b.getPublisher().getId(), b.getId());

	}

	public List<Book> find(String text) {
		text = "%" + text + "%";
		String sql = "SELECT k.Id_ksiazki,k.Tytul,k.Rok_wydania,k.ISBN,k.Slowa_kluczowe,k.Dostepnosc,k.Gatunek,\r\n"
				+ "a.Id_autora,a.Imie,a.Nazwisko,a.Komentarz,\r\n"
				+ "(SELECT count(*)-count(wy.Data_zwrotu) From wypozyczenia wy left join ksiazki k1 on k1.Id_ksiazki=wy.Id_ksiazki where k.Id_ksiazki=k1.Id_ksiazki)as Wypozyczona,\r\n"
				+ "w.Id_wydawnictwa,w.Adres,w.Email,w.Kod_pocztowy,w.Miejscowosc,w.Nazwa,w.Telefon \r\n"
				+ "FROM ((ksiazki k INNER JOIN autorzy a ON k.Id_autora=a.Id_autora) INNER JOIN wydawnictwo w ON k.Id_wydawnictwa=w.Id_wydawnictwa)"
				+ "WHERE k.Tytul COLLATE utf8_polish_ci LIKE ? OR k.Rok_wydania COLLATE utf8_polish_ci LIKE ? OR k.ISBN COLLATE utf8_polish_ci LIKE ? OR k.Slowa_kluczowe COLLATE utf8_polish_ci LIKE ? OR k.Gatunek COLLATE utf8_polish_ci LIKE ? OR a.Imie COLLATE utf8_polish_ci LIKE ? OR a.Nazwisko COLLATE utf8_polish_ci LIKE ? OR w.Nazwa COLLATE utf8_polish_ci LIKE ?";

		return jdbcTemplate.query(sql, new BookMapper(), text, text, text, text, text, text, text, text);
	}

	public void withdrawn(Book book) {
		jdbcTemplate.update("UPDATE ksiazki SET Dostepnosc=? WHERE Id_ksiazki=?",0,book.getId());
	}

}
