package org.brightworks.cmm.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication(scanBasePackages = {"org.brightworks.cmm.api"})
@EnableEurekaClient
public class FriflowCmmApi {
	public static void main(String[] args) {
		SpringApplication.run(FriflowCmmApi.class, args);
	}
}
