package project.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import project.model.Publisher;
import project.repositories.mappers.PublisherMapper;

@Repository
public class PublisherRepository {
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public List<Publisher> getall() {
		return jdbcTemplate.query("SELECT * FROM wydawnictwo", new PublisherMapper());
	}
	public Publisher getById(long id)
	{
		return jdbcTemplate.queryForObject("SELECT * FROM wydawnictwo WHERE Id_wydawnictwa=?",new PublisherMapper(),id);
	}
	public void addPublisher(Publisher publisher)
	{
		jdbcTemplate.update("INSERT INTO wydawnictwo (Nazwa,Adres,Miejscowosc,Kod_pocztowy,Telefon,Email) VALUES(?,?,?,?,?,?)",publisher.getName(),publisher.getAddress(),publisher.getCity(),publisher.getZipCode(),publisher.getPhoneNumber(),publisher.getEmail());
	}
	public void updatePublisher(Publisher publisher)
	{
		jdbcTemplate.update("UPDATE wydawnictwo SET Nazwa=?,Adres=?,Miejscowosc=?,Kod_pocztowy=?,Telefon=?,Email=? WHERE Id_wydawnictwa=?",publisher.getName(),publisher.getAddress(),publisher.getCity(),publisher.getZipCode(),publisher.getPhoneNumber(),publisher.getEmail(),publisher.getId());
	}
	public void deletePublisher(Publisher publisher)
	{
		jdbcTemplate.update("DELETE FROM wydawnictwo WHERE Id_wydawnictwa=?",publisher.getId());
	}
}
