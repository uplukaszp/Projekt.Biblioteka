package project.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import project.config.AppConfig;

public class Main {
	private static ApplicationContext context;

	public static void main(String[] args) {
		context = new AnnotationConfigApplicationContext(AppConfig.class);
		
		//test connection
		JdbcTemplate bean = context.getBean("jdbcTemplate",JdbcTemplate.class);
		System.out.println(bean.queryForObject("select now() from dual", String.class));
	}
}
