package project.repositories.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import project.model.Author;

public class AuthorMapper implements RowMapper<Author> {

	public Author mapRow(ResultSet set, int arg1) throws SQLException {
		Author aut=new Author();
		String comment=set.getString("komentarz");
		String forename=set.getString("Imie");;
		long id=set.getLong("Id_autora");
		String surname=set.getString("Nazwisko");;
		aut.setComment(comment);
		aut.setForename(forename);
		aut.setId(id);		
		aut.setSurname(surname);
		return aut;
	}

}
