package project.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import project.repositories.mappers.AuthorMapper;
import projekt.model.Author;

@Repository
public class AuthorRepository {
	@Autowired
	NamedParameterJdbcTemplate namedJdbcTemplate;

	@Autowired
	JdbcTemplate jdbcTemplate;

	public List<Author> getall() {
		return jdbcTemplate.query("SELECT * FROM autorzy", new AuthorMapper());
	}
	public Author getById(long id)
	{
		return jdbcTemplate.queryForObject("SELECT * FROM autorzy WHERE Id_autora=?",new AuthorMapper(),id);
	}
	public void addAuthor(Author author)
	{
		jdbcTemplate.update("INSERT INTO autorzy (Imie,Nazwisko,Komentarz) VALUES(?,?,?)",author.getForename(),author.getSurname(),author.getComment());
	}
	public void updateAuthor(Author author)
	{
		jdbcTemplate.update("UPDATE autorzy SET Imie=?, Nazwisko=?,Komentarz=? WHERE Id_autora=?",author.getForename(),author.getSurname(),author.getComment(),author.getId());
	}
	public void deleteAuthor(Author author)
	{
		jdbcTemplate.update("DELETE FROM autorzy WHERE Id_autora=?",author.getId());
	}
}
