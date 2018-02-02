package project.repositories;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.jdbc.core.RowMapper;

import project.model.Book;
import project.model.Lend;
import project.model.Reader;
import project.repositories.mappers.BookMapper;
import project.repositories.mappers.ReaderMapper;

public class LendMapper implements RowMapper<Lend> {
	static SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public Lend mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Lend l=new Lend();
		Book b=new Book();
		Reader r=new Reader();
		r.setId(rs.getLong("c.Id_czytelnika"));
		r.setForname(rs.getString("c.Imie"));
		r.setSurname(rs.getString("c.Nazwisko"));
		r.setEmail(rs.getString("c.Email"));
		b.setTitle(rs.getString("k.Tytul"));
		b.setISBN(rs.getString("k.ISBN"));
		b.setId(rs.getLong("k.Id_ksiazki"));
		l.setBook(b);
		l.setReader(r);
		try {
			l.setLendDate(f.parse(rs.getString("w.Data_wypozyczenia")));
		} catch (ParseException e) {
			
		}
		l.setReturnDate(rs.getDate("w.Data_zwrotu"));
		return l;
	}

}
