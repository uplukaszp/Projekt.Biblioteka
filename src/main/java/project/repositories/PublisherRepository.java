package project.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import project.model.Publisher;
import project.repositories.mappers.PublisherMapper;

@Repository
public class PublisherRepository {
	@Autowired
	JdbcTemplate jdbcTemplate;

	public List<Publisher> getall() {
		String sql = "SELECT w.Id_wydawnictwa,w.Nazwa,w.Adres,w.Miejscowosc,w.Kod_pocztowy,w.Telefon,w.Email FROM wydawnictwo w";
		return jdbcTemplate.query(sql, new PublisherMapper());
	}

	public Publisher getById(long id) {
		String sql = "SELECT w.Id_wydawnictwa,w.Nazwa,w.Adres,w.Miejscowosc,w.Kod_pocztowy,w.Telefon,w.Email FROM wydawnictwo w WHERE Id_wydawnictwa=?";
		return jdbcTemplate.queryForObject(sql, new PublisherMapper(), id);
	}

	public void addPublisher(Publisher publisher) {
		jdbcTemplate.update(
				"INSERT INTO wydawnictwo (Nazwa,Adres,Miejscowosc,Kod_pocztowy,Telefon,Email) VALUES(?,?,?,?,?,?)",
				publisher.getName(), publisher.getAddress(), publisher.getCity(), publisher.getZipCode(),
				publisher.getPhoneNumber(), publisher.getEmail());
	}

	public void updatePublisher(Publisher publisher) {
		jdbcTemplate.update(
				"UPDATE wydawnictwo SET Nazwa=?,Adres=?,Miejscowosc=?,Kod_pocztowy=?,Telefon=?,Email=? WHERE Id_wydawnictwa=?",
				publisher.getName(), publisher.getAddress(), publisher.getCity(), publisher.getZipCode(),
				publisher.getPhoneNumber(), publisher.getEmail(), publisher.getId());
	}

	public void deletePublisher(Publisher publisher) {
		jdbcTemplate.update("DELETE FROM wydawnictwo WHERE Id_wydawnictwa=?", publisher.getId());
	}

	public List<Publisher> find(String text) {
		text = "%" + text + "%";
		String sql = "SELECT w.Id_wydawnictwa,w.Nazwa,w.Adres,w.Miejscowosc,w.Kod_pocztowy,w.Telefon,w.Email FROM wydawnictwo w WHERE w.Nazwa COLLATE utf8_polish_ci LIKE ? OR w.Adres COLLATE utf8_polish_ci LIKE ?  OR w.Miejscowosc COLLATE utf8_polish_ci LIKE ?  OR w.Kod_pocztowy COLLATE utf8_polish_ci LIKE ? OR  w.Telefon COLLATE utf8_polish_ci LIKE ? OR  w.Email  COLLATE utf8_polish_ci LIKE ?";
		return jdbcTemplate.query(sql, new PublisherMapper(), text, text, text, text, text, text);
	}
}
