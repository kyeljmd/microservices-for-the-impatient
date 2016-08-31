package org.brightworks.friflow.ticketing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class FriflowAdminApplication {
	public static void main(String[] args) {
		SpringApplication.run(FriflowAdminApplication.class, args);

	}
}
