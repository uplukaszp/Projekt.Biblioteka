package project.repositories;

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
	
	public List<Book>getAll()
	{
		String sql="SELECT k.Id_ksiazki,k.Tytul,k.Rok_wydania,k.ISBN,k.Slowa_kluczowe,k.Dostepnosc,k.Gatunek,a.Id_autora,a.Imie,a.Nazwisko,a.Komentarz,w.Id_wydawnictwa,w.Adres,w.Email,w.Kod_pocztowy,w.Miejscowosc,w.Nazwa,w.Telefon FROM (ksiazki k INNER JOIN autorzy a ON k.Id_autora=a.Id_autora) INNER JOIN wydawnictwo w ON k.Id_wydawnictwa=w.Id_wydawnictwa";
		
		return jdbcTemplate.query(sql, new BookMapper());
	}
	public Book getBook(long id)
	{
		String sql="SELECT k.Id_ksiazki,k.Tytul,k.Rok_wydania,k.ISBN,k.Slowa_kluczowe,k.Dostepnosc,k.Gatunek,a.Id_autora,a.Imie,a.Nazwisko,a.Komentarz,w.Id_wydawnictwa,w.Adres,w.Email,w.Kod_pocztowy,w.Miejscowosc,w.Nazwa,w.Telefon FROM (ksiazki k INNER JOIN autorzy a ON k.Id_autora=a.Id_autora) INNER JOIN wydawnictwo w ON k.Id_wydawnictwa=w.Id_wydawnictwa where k.Id_ksiazki=?";
		return jdbcTemplate.queryForObject(sql, new BookMapper(),id);
	}
	public void addBook(Book b)
	{
		jdbcTemplate.update("INSERT INTO ksiazki (Tytul,Rok_wydania,ISBN,Slowa_kluczowe,Dostepnosc,Gatunek,Id_autora,Id_wydawnictwa) VALUES(?,?,?,?,?,?,?,?)",
				b.getTitle(),b.getPublicationDate(),b.getISBN(),b.getKeywords(),b.isAccessible(),b.getType(),b.getAuthor().getId(),b.getPublisher().getId());
	}
	public void removeBook(Book b)
	{
		jdbcTemplate.update("DELETE FROM ksiazki WHERE id_ksiazki=?",b.getId());
	}
	
}

