package com.wolfott.mangement.epg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class EpgMangementApplication {

	public static void main(String[] args) {
		SpringApplication.run(EpgMangementApplication.class, args);
	}

}
