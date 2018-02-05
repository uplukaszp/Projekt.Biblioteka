package project.repositories;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class LendSettingsRepository {
	private int daysOfRental;
	private float penaltyForDay;
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public LendSettingsRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate=jdbcTemplate;
		Map<String, Object> map = jdbcTemplate.queryForMap("SELECT * FROM opcje");
		daysOfRental=(Integer) map.get("Dni_na_oddanie");
		penaltyForDay=(Float) map.get("Kara_za_dzien");
	}
	public int getDaysOfRental() {
		return daysOfRental;
	}
	public void setDaysOfRental(int daysOfRental) {
		jdbcTemplate.update("UPDATE opcje SET Dni_na_oddanie=?",daysOfRental);
		this.daysOfRental = daysOfRental;
	}
	public float getPenaltyForDay() {
		return penaltyForDay;
	}
	public void setPenaltyForDay(float penaltyForDay) {
		jdbcTemplate.update("UPDATE opcje SET Kara_za_dzien=?",penaltyForDay);
		this.penaltyForDay = penaltyForDay;
	}
	
}
