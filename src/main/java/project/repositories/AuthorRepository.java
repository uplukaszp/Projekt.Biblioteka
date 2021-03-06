package project.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import project.model.Author;
import project.repositories.mappers.AuthorMapper;

@Repository
public class AuthorRepository {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public List<Author> getall() {
		return jdbcTemplate.query("SELECT a.Id_autora,a.Imie,a.Nazwisko,a.Komentarz FROM autorzy a",
				new AuthorMapper());
	}

	public Author getById(long id) {
		return jdbcTemplate.queryForObject(
				"SELECT a.Id_autora,a.Imie,a.Nazwisko,a.Komentarz FROM autorzy a WHERE Id_autora=?", new AuthorMapper(),
				id);
	}

	public void addAuthor(Author author) {
		jdbcTemplate.update("INSERT INTO autorzy (Imie,Nazwisko,Komentarz) VALUES(?,?,?)", author.getForename(),
				author.getSurname(), author.getComment());
	}

	public void updateAuthor(Author author) {
		jdbcTemplate.update("UPDATE autorzy SET Imie=?, Nazwisko=?,Komentarz=? WHERE Id_autora=?", author.getForename(),
				author.getSurname(), author.getComment(), author.getId());
	}

	public void deleteAuthor(Author author) {
		jdbcTemplate.update("DELETE FROM autorzy WHERE Id_autora=?", author.getId());
	}

	public List<Author> find(String text) {
		text="%"+text+"%";
		return jdbcTemplate.query(
				"SELECT a.Id_autora,a.Imie,a.Nazwisko,a.Komentarz FROM autorzy a WHERE a.Imie COLLATE utf8_polish_ci LIKE ? OR a.Nazwisko  COLLATE utf8_polish_ci LIKE ? OR a.Komentarz  COLLATE utf8_polish_ci LIKE ?",
				new AuthorMapper(), text, text, text);
	}
}
