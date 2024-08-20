package com.wolfott.stream_mangement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class StreamMangementApplication {

	public static void main(String[] args) {
		SpringApplication.run(StreamMangementApplication.class, args);
	}

}
