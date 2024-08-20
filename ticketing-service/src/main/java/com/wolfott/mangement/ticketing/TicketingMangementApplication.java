package com.wolfott.mangement.ticketing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class TicketingMangementApplication {

	public static void main(String[] args) {
		SpringApplication.run(TicketingMangementApplication.class, args);
	}

}
