package com.example.springwork;

import java.sql.Connection;
import java.util.Arrays;

import javax.sql.DataSource;

import com.example.springwork.dao.mapper.CityMapper;
import com.example.springwork.domain.City;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class SpringWorkApplication {

	@Autowired
    DataSource dataSource;


	public static void main(String[] args) {
		SpringApplication.run(SpringWorkApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
			System.out.println("##################################################");
			System.out.println("Let's inspect the beans provided by Spring Boot:");
			String[] beanNames = ctx.getBeanDefinitionNames();
			Arrays.sort(beanNames);
			for (String beanName : beanNames) {
				System.out.println(beanName);
			}
			System.out.println("##################################################");
		};
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("http://localhost:8080");
			}
		};
	}

	private final CityMapper cityMapper;

	public SpringWorkApplication(CityMapper cityMapper) {
	  this.cityMapper = cityMapper;
	}
  
	@Bean
	CommandLineRunner sampleCommandLineRunner() {
	  return args -> {

		Connection connection = dataSource.getConnection();
        System.out.println("Url: " + connection.getMetaData().getURL());
        System.out.println("UserName: " + connection.getMetaData().getUserName());
		
		City city = new City();
		city.setName("San Francisco");
		city.setState("CA");
		city.setCountry("US");
		cityMapper.insert(city);
		System.out.println(this.cityMapper.findById(city.getId()));
	  };
	}
  
}