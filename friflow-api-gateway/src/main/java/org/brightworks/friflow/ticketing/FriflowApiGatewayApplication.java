package org.brightworks.friflow.ticketing;

import org.brightworks.friflow.ticketing.filter.ApiAccessFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
@EnableZuulProxy
public class FriflowApiGatewayApplication {

	@Value("${jwt.security.key}")
	private String jwtKey;

	public static void main(String[] args) {
		SpringApplication.run(FriflowApiGatewayApplication.class, args);
	}

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}

	@Bean
	public FilterRegistrationBean filterRegistrationBean() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		ApiAccessFilter securityFilter = new ApiAccessFilter(jwtKey);
		registrationBean.setFilter(securityFilter);
		registrationBean.addUrlPatterns("/api/*");
		return registrationBean;
	}
}


