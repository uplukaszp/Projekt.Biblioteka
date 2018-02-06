package project.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.mail.javamail.JavaMailSenderImpl;


@Configuration
@ComponentScan(basePackages = "project")
@PropertySource("file:./config.properties")
@PropertySource("file:./email.properties")
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

	@Value("${mail.host}")
	String emailHost;
	@Value("${mail.port}")
	String emailPort;
	@Value("${mail.username}")
	String emailUsername;
	@Value("${mail.password}")
	String emailPw; 

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://"+url+":"+port+"/"+dbName);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		Properties p=new Properties();
		p.setProperty("useUnicode", "yes");
		p.setProperty("characterEncoding", "utf8");
		dataSource.setConnectionProperties( p);
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
	@Bean
	public JavaMailSenderImpl getJavaMailSender()
	{
		JavaMailSenderImpl sender=new JavaMailSenderImpl();
		System.out.println(emailPort);
		int port=Integer.valueOf(emailPort);
		sender.setHost(emailHost);
		sender.setPort(port);
		sender.setUsername(emailUsername);
		sender.setPassword(emailPw);
		
		Properties props = sender.getJavaMailProperties();
	    props.put("mail.transport.protocol", "smtp");
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.debug", "true");
	     
		return sender;
	}
}
