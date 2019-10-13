package com.prntpage.BnbServiceV1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableMongoRepositories(repositoryBaseClass = UUIDMongoRepositoryImpl.class)
public class BnbServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BnbServiceApplication.class, args);
	}

}
