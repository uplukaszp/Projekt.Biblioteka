package projekt.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import projekt.config.AppConfig;

public class Main {
	public static void main(String[] args) {
		ApplicationContext context= new AnnotationConfigApplicationContext(AppConfig.class);
		
		//test connection
		JdbcTemplate bean = context.getBean("jdbcTemplate",JdbcTemplate.class);
		System.out.println(bean.queryForObject("select now() from dual", String.class));
	}
}
