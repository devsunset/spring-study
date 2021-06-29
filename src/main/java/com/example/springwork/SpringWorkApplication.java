package com.example.springwork;

import java.sql.Connection;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.sql.DataSource;

import com.example.springwork.dao.mapper.CityMapper;
import com.example.springwork.domain.City;
// import com.example.springwork.domain.Customer;
import com.example.springwork.service.BookingService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import org.mybatis.spring.annotation.MapperScan;

@MapperScan(basePackageClasses = SpringWorkApplication.class)
@SpringBootApplication
public class SpringWorkApplication {

	private static final Logger log = LoggerFactory.getLogger(SpringWorkApplication.class);
	
	private final CityMapper cityMapper;

	@Autowired
    DataSource dataSource;

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	BookingService bookingService;


	public SpringWorkApplication(CityMapper cityMapper) {
	  this.cityMapper = cityMapper;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringWorkApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
			log.info("##################################################");
			log.info("Let's inspect the beans provided by Spring Boot:");
			String[] beanNames = ctx.getBeanDefinitionNames();
			Arrays.sort(beanNames);
			for (String beanName : beanNames) {
				log.info(beanName);
			}
			log.info("##################################################");
		};
	}

	@Bean
	CommandLineRunner sampleJdbcCommandLineRunner() {
	  return args -> {
		log.info("Creating tables");

		jdbcTemplate.execute("DROP TABLE customers IF EXISTS");
		jdbcTemplate.execute("CREATE TABLE customers(" +
			"id SERIAL, first_name VARCHAR(255), last_name VARCHAR(255))");

		// Split up the array of whole names into an array of first/last names
		List<Object[]> splitUpNames = Arrays.asList("John Woo", "Jeff Dean", "Josh Bloch", "Josh Long").stream()
			.map(name -> name.split(" "))
			.collect(Collectors.toList());

		// Use a Java 8 stream to print out each tuple of the list
		splitUpNames.forEach(name -> log.info(String.format("Inserting customer record for %s %s", name[0], name[1])));

		// Uses JdbcTemplate's batchUpdate operation to bulk load data
		jdbcTemplate.batchUpdate("INSERT INTO customers(first_name, last_name) VALUES (?,?)", splitUpNames);

		log.info("Querying for customer records where first_name = 'Josh':");
		// jdbcTemplate.query(
		// 	"SELECT id, first_name, last_name FROM customers WHERE first_name = ?", new Object[] { "Josh" },
		// 	(rs, rowNum) -> new Customer(rs.getLong("id"), rs.getString("first_name"), rs.getString("last_name"))
		// ).forEach(customer -> log.info(customer.toString()));
	  };
	}

	@Bean
	CommandLineRunner sampleTransactionCommandLineRunner() {
	  return args -> {
			bookingService.book("Alice", "Bob", "Carol");
			//Assert.isTrue(bookingService.findAllBookings().size() == 3,	"First booking should work with no problem");
			log.info("Alice, Bob and Carol have been booked");
			try {
			bookingService.book("Chris", "Samuel");
			} catch (RuntimeException e) {
				log.info("v--- The following exception is expect because 'Samuel' is too " + "big for the DB ---v");
				log.error(e.getMessage());
			}

			for (String person : bookingService.findAllBookings()) {
				log.info("So far, " + person + " is booked.");
			}
			log.info("You shouldn't see Chris or Samuel. Samuel violated DB constraints, " +"and Chris was rolled back in the same TX");
			//Assert.isTrue(bookingService.findAllBookings().size() == 3,	"'Samuel' should have triggered a rollback");

			try {
			bookingService.book("Buddy", null);
			} catch (RuntimeException e) {
				log.info("v--- The following exception is expect because null is not " + "valid for the DB ---v");
				log.error(e.getMessage());
			}

			for (String person : bookingService.findAllBookings()) {
				log.info("So far, " + person + " is booked.");
			}
			log.info("You shouldn't see Buddy or null. null violated DB constraints, and " + "Buddy was rolled back in the same TX");
			//Assert.isTrue(bookingService.findAllBookings().size() == 3,	"'null' should have triggered a rollback");
	  };
	}

    @Bean
	CommandLineRunner sampleMybitasCommandLineRunner() {
	  return args -> {
		Connection connection = dataSource.getConnection();
        log.info("Url: " + connection.getMetaData().getURL());
        log.info("UserName: " + connection.getMetaData().getUserName());
		
		City city = new City();
		city.setName("San Francisco");
		city.setState("CA");
		city.setCountry("US");
		cityMapper.insert(city);
		log.info(this.cityMapper.findById(city.getId()).toString());
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
}