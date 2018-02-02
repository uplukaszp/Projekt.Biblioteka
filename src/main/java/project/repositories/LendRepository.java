package project.repositories;

import java.sql.Date;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import project.model.Lend;


@Repository
public class LendRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void add(Lend l) {
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String sql = "insert into wypozyczenia (Data_wypozyczenia,Id_ksiazki,Id_czytelnika) values (?,?,?)";
		jdbcTemplate.update(sql, f.format(l.getLendDate()), l.getBook().getId(), l.getReader().getId());
	}

	public void remove(Date d) {
		
	}
}
