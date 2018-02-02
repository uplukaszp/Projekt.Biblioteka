package project.repositories;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import project.model.Lend;


@Repository
public class LendRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	static SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public void add(Lend l) {
		
		String sql = "insert into wypozyczenia (Data_wypozyczenia,Id_ksiazki,Id_czytelnika) values (?,?,?)";
		jdbcTemplate.update(sql, f.format(l.getLendDate()), l.getBook().getId(), l.getReader().getId());
	}
	
	public List<Lend> getAll()
	{
		String sql="SELECT w.Data_wypozyczenia,w.Data_zwrotu,c.Imie,c.Nazwisko,c.Email,k.Tytul,k.ISBN,k.Id_ksiazki,c.Id_czytelnika FROM (wypozyczenia w INNER JOIN czytelnicy c ON w.Id_czytelnika=c.Id_czytelnika)INNER JOIN ksiazki k ON k.Id_ksiazki=w.Id_ksiazki;";
		return jdbcTemplate.query(sql,new LendMapper());
	}
	public void returnBook(Lend l)
	{
		String sql="update wypozyczenia SET Data_zwrotu=now() where Data_wypozyczenia=?;";
		jdbcTemplate.update(sql,f.format(l.getLendDate()));
	}

	public List<Lend> getCurrent() {
		String sql="SELECT w.Data_wypozyczenia,w.Data_zwrotu,c.Imie,c.Nazwisko,c.Email,k.Tytul,k.ISBN,k.Id_ksiazki,c.Id_czytelnika FROM (wypozyczenia w INNER JOIN czytelnicy c ON w.Id_czytelnika=c.Id_czytelnika)INNER JOIN ksiazki k ON k.Id_ksiazki=w.Id_ksiazki WHERE w.Data_zwrotu is NULL;";
		return jdbcTemplate.query(sql,new LendMapper());
	}
}
