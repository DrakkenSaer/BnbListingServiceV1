package com.prntpage.BnbServiceV1;

import com.prntpage.BnbServiceV1.repositories.custom.UUIDMongoRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
//@EnableMongoRepositories(repositoryBaseClass = UUIDMongoRepositoryImpl.class)
public class BnbServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BnbServiceApplication.class, args);
	}

}
