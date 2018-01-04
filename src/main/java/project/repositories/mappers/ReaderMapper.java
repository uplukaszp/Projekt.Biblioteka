package project.repositories.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import project.model.Reader;

public class ReaderMapper implements RowMapper<Reader> {

	public Reader mapRow(ResultSet set, int rowNum) throws SQLException {
		Reader r = new Reader();
		String address = set.getString("c.Adres");
		String city = set.getString("c.Miejscowosc");
		String forname = set.getString("c.Imie");
		String email = set.getString("c.Email");
		long id = set.getLong("c.Id_czytelnika");
		String surname = set.getString("c.Nazwisko");
		String zipCode = set.getString("c.Kod_pocztowy");
		r.setAddress(address);
		r.setCity(city);
		r.setEmail(email);
		r.setForname(forname);
		r.setId(id);
		r.setSurname(surname);
		r.setZipCode(zipCode);
		return r;
	}
}
