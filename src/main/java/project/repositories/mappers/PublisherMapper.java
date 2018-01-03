package project.repositories.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import projekt.model.Publisher;

public class PublisherMapper implements RowMapper<Publisher> {

	public Publisher mapRow(ResultSet set, int rowNum) throws SQLException {
		Publisher p = new Publisher();
		String address=set.getString("Adres");
		String city=set.getString("Miasto");
		String email=set.getString("Email");
		long id=set.getLong("Id_Wydawnictwa");
		String name=set.getString("Nazwa");
		String phoneNumber=set.getString("Telefon");
		String zipCode=set.getString("Kod_pocztowy");
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
