package cn.myapps.person.repository;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class Config {

	@Bean
	@Profile("mysql")
	public PersonRepository mysqlPersonRepository(){
		return new MysqlPersonRepository();
	}
	
	@Bean
	@Profile("connect")
	public PersonRepository connectPersonRepository(){
		return new ConnectPersonRepository();
	}
	
}
