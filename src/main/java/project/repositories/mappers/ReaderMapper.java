package project.repositories.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import project.model.Reader;

public class ReaderMapper implements RowMapper<Reader> {

	public Reader mapRow(ResultSet set, int rowNum) throws SQLException {
		Reader r = new Reader();
		String address = set.getString("Adres");
		String city = set.getString("Miejscowosc");
		String forname = set.getString("Imie");
		String email = set.getString("Email");
		long id = set.getLong("Id_czytelnika");
		String surname = set.getString("Nazwisko");
		String zipCode = set.getString("Kod_pocztowy");
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
