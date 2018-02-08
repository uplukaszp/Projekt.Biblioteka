package project.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import project.model.Reader;
import project.repositories.mappers.ReaderMapper;

@Repository
public class ReaderRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<Reader> getAll() {
		return jdbcTemplate.query(
				"SELECT c.Id_czytelnika, c.Imie,c.Nazwisko,c.Adres,c.Miejscowosc,c.Kod_pocztowy,c.Email FROM Czytelnicy c",
				new ReaderMapper());
	}

	public Reader getById(long id) {
		return jdbcTemplate.queryForObject(
				"SELECT c.Id_czytelnika, c.Imie,c.Nazwisko,c.Adres,c.Miejscowosc,c.Kod_pocztowy,c.Email FROM Czytelnicy c WHERE c.Id_czytelnika=?",
				new ReaderMapper(), id);
	}

	public void updateReader(Reader r) {
		jdbcTemplate.update(
				"UPDATE czytelnicy SET Imie=?, Nazwisko=?,Adres=?,Kod_pocztowy=?,Miejscowosc=?,Email=? WHERE Id_czytelnika=?",
				r.getForname(), r.getSurname(), r.getAddress(), r.getZipCode(), r.getCity(), r.getEmail(), r.getId());

	}

	public void addReader(Reader r) {
		jdbcTemplate.update(
				"INSERT INTO czytelnicy (Imie, Nazwisko,Adres,Kod_pocztowy,Miejscowosc,Email) VALUES(?,?,?,?,?,?)",
				r.getForname(), r.getSurname(), r.getAddress(), r.getZipCode(), r.getCity(), r.getEmail());

	}

	public void deleteReader(Reader r) {
		jdbcTemplate.update("DELETE FROM czytelnicy WHERE Id_czytelnika=?", r.getId());
	}

	public List<Reader> find(String text) {
		text = "%" + text + "%";
		String sql = "SELECT c.Id_czytelnika, c.Imie,c.Nazwisko,c.Adres,c.Miejscowosc,c.Kod_pocztowy,c.Email FROM Czytelnicy c WHERE c.Imie COLLATE utf8_polish_ci LIKE ? OR c.Nazwisko COLLATE utf8_polish_ci LIKE ? OR c.Adres COLLATE utf8_polish_ci LIKE ? OR c.Miejscowosc COLLATE utf8_polish_ci LIKE ? OR c.Kod_pocztowy COLLATE utf8_polish_ci LIKE ? OR c.Email COLLATE utf8_polish_ci LIKE ? ";
		return jdbcTemplate.query(sql, new ReaderMapper(), text, text, text, text, text, text);

	}
}
