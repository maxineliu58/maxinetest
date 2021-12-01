package com.upload.upload_Game;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@EnableCaching
@SpringBootApplication()
public class UploadGameApplication {

	public static void main(String[] args) {
		SpringApplication.run(UploadGameApplication.class, args);
	}
}
