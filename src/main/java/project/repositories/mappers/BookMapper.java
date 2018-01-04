package project.repositories.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

import project.model.Author;
import project.model.Book;
import project.model.Publisher;

public class BookMapper implements RowMapper<Book> {

	public Book mapRow(ResultSet set, int rowNum) throws SQLException {
		Book b = new Book();
		Author a = new AuthorMapper().mapRow(set, rowNum);
		Publisher p = new PublisherMapper().mapRow(set, rowNum);
		boolean isAccessible = set.getBoolean("b.Dostepnosc");
		long id = set.getLong("b.Id_ksiazki");
		String iSBN = set.getString("b.ISBN");
		String keywords = set.getString("b.Slowa_kluczowe");
		Date publicationDate = set.getDate("b.Rok_Wydania");
		String title = set.getString("b.Tytul");
		String type = set.getString("b.Gatunek");
		b.setAccessible(isAccessible);
		b.setAuthor(a);
		b.setId(id);
		b.setISBN(iSBN);
		b.setKeywords(keywords);
		b.setPublicationDate(publicationDate);
		b.setPublisher(p);
		b.setTitle(title);
		b.setType(type);
		return b;
	}
}
