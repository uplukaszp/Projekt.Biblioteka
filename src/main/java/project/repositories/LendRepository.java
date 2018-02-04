package project.repositories;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import project.model.Lend;

@Repository
public class LendRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public void add(Lend l) {

		String sql = "insert into wypozyczenia (Data_wypozyczenia,Id_ksiazki,Id_czytelnika) values (?,?,?)";
		jdbcTemplate.update(sql, f.format(l.getLendDate()), l.getBook().getId(), l.getReader().getId());
	}

	public List<Lend> getAll() {
		String sql = "SELECT w.Data_wypozyczenia,w.Data_zwrotu,c.Imie,c.Nazwisko,c.Email,k.Tytul,k.ISBN,k.Id_ksiazki,c.Id_czytelnika FROM (wypozyczenia w INNER JOIN czytelnicy c ON w.Id_czytelnika=c.Id_czytelnika)INNER JOIN ksiazki k ON k.Id_ksiazki=w.Id_ksiazki;";
		return jdbcTemplate.query(sql, new LendMapper());
	}

	public void returnBook(Lend l) {
		String sql = "update wypozyczenia SET Data_zwrotu=now() where Data_wypozyczenia=?;";
		jdbcTemplate.update(sql, f.format(l.getLendDate()));
	}

	public List<Lend> getAllCurrent() {
		String sql = "SELECT w.Data_wypozyczenia,w.Data_zwrotu,c.Imie,c.Nazwisko,c.Email,k.Tytul,k.ISBN,k.Id_ksiazki,c.Id_czytelnika FROM (wypozyczenia w INNER JOIN czytelnicy c ON w.Id_czytelnika=c.Id_czytelnika)INNER JOIN ksiazki k ON k.Id_ksiazki=w.Id_ksiazki WHERE w.Data_zwrotu is NULL;";
		return jdbcTemplate.query(sql, new LendMapper());
	}

	public String dateOfReturn(long id) {
		Date d = null;
		String sql = "Select date_add((Select Data_wypozyczenia from wypozyczenia where Id_ksiazki=? and Data_zwrotu is null),interval Dni_na_oddanie DAY) from opcje;";
		d = jdbcTemplate.queryForObject(sql, Date.class, id);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(d);
	}

	public float getPenalty(Lend l) {
		String sql = "Select  greatest(0,Kara_za_dzien*datediff(now(),date_add((Select Data_wypozyczenia from wypozyczenia where Id_ksiazki=? and Data_zwrotu is null),interval Dni_na_oddanie DAY))) as penalty from opcje;";
		return jdbcTemplate.queryForObject(sql, Float.class, l.getBook().getId());
	}

	public List<Lend> find(String text) {

		text="%"+text+"%";
		String sql = "SELECT w.Data_wypozyczenia,w.Data_zwrotu,c.Imie,c.Nazwisko,c.Email,k.Tytul,k.ISBN,k.Id_ksiazki,c.Id_czytelnika FROM (wypozyczenia w INNER JOIN czytelnicy c ON w.Id_czytelnika=c.Id_czytelnika)INNER JOIN ksiazki k ON k.Id_ksiazki=w.Id_ksiazki WHERE w.Data_wypozyczenia LIKE ? OR w.Data_zwrotu LIKE ? OR c.Imie LIKE ? OR c.Nazwisko LIKE ? OR c.Email LIKE ? OR k.Tytul LIKE ? OR k.ISBN LIKE ?;";
		return jdbcTemplate.query(sql, new LendMapper(),text,text,text,text,text,text,text);
	}

	public List<Lend> findInCurrent(String text) {
		text="%"+text+"%";
		String sql = "SELECT w.Data_wypozyczenia,w.Data_zwrotu,c.Imie,c.Nazwisko,c.Email,k.Tytul,k.ISBN,k.Id_ksiazki,c.Id_czytelnika FROM (wypozyczenia w INNER JOIN czytelnicy c ON w.Id_czytelnika=c.Id_czytelnika)INNER JOIN ksiazki k ON k.Id_ksiazki=w.Id_ksiazki WHERE (w.Data_wypozyczenia LIKE ? OR w.Data_zwrotu LIKE ? OR c.Imie LIKE ? OR c.Nazwisko LIKE ? OR c.Email LIKE ? OR k.Tytul LIKE ? OR k.ISBN LIKE ?)AND w.Data_zwrotu is NULL ;";
		return jdbcTemplate.query(sql, new LendMapper(),text,text,text,text,text,text,text);
	}
}
