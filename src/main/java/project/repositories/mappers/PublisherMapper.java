package project.repositories.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import project.model.Publisher;

public class PublisherMapper implements RowMapper<Publisher> {

	public Publisher mapRow(ResultSet set, int rowNum) throws SQLException {

		Publisher p = new Publisher();
		String address = set.getString("w.Adres");
		String city = set.getString("w.Miejscowosc");
		String email = set.getString("w.Email");
		long id = set.getLong("w.Id_Wydawnictwa");
		String name = set.getString("w.Nazwa");
		String phoneNumber = set.getString("w.Telefon");
		String zipCode = set.getString("w.Kod_pocztowy");
		p.setAddress(address);
		p.setCity(city);
		p.setEmail(email);
		p.setId(id);
		p.setName(name);
		p.setPhoneNumber(phoneNumber);
		p.setZipCode(zipCode);
		return p;
	}

}
