package xyz.feedclap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;




@SpringBootApplication(scanBasePackages={"xyz.feedclap.repositories.VideogameRepository"})
public class FclapApplication {


	public static void main(String[] args) {
		SpringApplication.run(FclapApplication.class, args);
	}
}
