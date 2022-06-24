package edu.poly.shop;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import edu.poly.shop.config.StorageProperties;
import edu.poly.shop.domain.Account;
import edu.poly.shop.service.StorageService;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class TechPolyshopApplication {

	public static void main(String[] args) {
		SpringApplication.run(TechPolyshopApplication.class, args);
	}
	
	@Bean
	CommandLineRunner init(StorageService storageService) {
		return (args ->{
			storageService.init();
		});
	}
	
	@Bean(name = "USER_BEAN")
	public Account setUser() {
		Account u = new Account();
		u.setUsername("admin123");
		u.setPassword("admin123");
		return u;
	}

}
