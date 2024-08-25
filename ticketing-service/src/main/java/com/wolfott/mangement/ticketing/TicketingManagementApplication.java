package com.wolfott.mangement.ticketing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class TicketingManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(TicketingManagementApplication.class, args);
	}

}
