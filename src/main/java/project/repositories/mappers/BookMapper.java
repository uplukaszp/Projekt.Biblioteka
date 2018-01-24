package project.repositories.mappers;

import java.sql.ResultSet;

import java.sql.SQLException;
import java.sql.Date;

import org.springframework.jdbc.core.RowMapper;

import project.model.Author;
import project.model.Book;
import project.model.BookStatus;
import project.model.Publisher;

public class BookMapper implements RowMapper<Book> {

	public Book mapRow(ResultSet set, int rowNum) throws SQLException {
		Book b = new Book();
		Author a = new AuthorMapper().mapRow(set, rowNum);
		Publisher p = new PublisherMapper().mapRow(set, rowNum);
		boolean isAccessible = set.getBoolean("k.Dostepnosc");
		boolean isLend=set.getBoolean("Wypozyczona");
		long id = set.getLong("k.Id_ksiazki");
		String iSBN = set.getString("k.ISBN");
		String keywords = set.getString("k.Slowa_kluczowe");
		Date publicationDate = set.getDate("k.Rok_Wydania");
		String title = set.getString("k.Tytul");
		String type = set.getString("k.Gatunek");
		if(isAccessible)
		{
			if(isLend)b.setStatus(BookStatus.lent);
			else b.setStatus(BookStatus.available);
		}	else b.setStatus(BookStatus.withdrawn);
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
