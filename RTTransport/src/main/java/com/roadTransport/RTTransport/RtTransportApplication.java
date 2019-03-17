package com.roadTransport.RTTransport;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.FeignClient;

@SpringBootApplication
@EnableCaching
@FeignClient
public class RtTransportApplication {

	public static void main(String[] args) {
		SpringApplication.run(RtTransportApplication.class, args);
	}

}
