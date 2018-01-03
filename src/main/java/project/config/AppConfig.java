package project.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.mysql.cj.jdbc.MysqlDataSource;

@Configuration
@ComponentScan(basePackages = "project")
@PropertySource("file:./config.properties")
public class AppConfig {
	@Value("${db.login}")
	String username;
	@Value("${db.pw}")
	String password;
	@Value("${db.port}")
	String port;
	@Value("${db.url}")
	String url;
	@Value("${db.name}")
	String dbName;

	@Bean
	public DataSource dataSource() {

		MysqlDataSource dataSource = new MysqlDataSource();
		dataSource.setDatabaseName(dbName);
		dataSource.setServerName(url);
		dataSource.setUser(username);
		dataSource.setPassword(password);
		dataSource.setPort(Integer.valueOf(port));
		return dataSource;
	}

	@Bean
	public JdbcTemplate jdbcTemplate() {
		return new JdbcTemplate(dataSource());
	}

	@Bean
	public NamedParameterJdbcTemplate namedParameterJdbcTemplate() {
		return new NamedParameterJdbcTemplate(dataSource());
	}
}