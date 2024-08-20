package com.wolfott.mangement.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ServerMangementApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerMangementApplication.class, args);
	}

}
