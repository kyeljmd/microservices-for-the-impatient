package org.brightworks.friflow.ticketing;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author kyel
 */
@SpringBootApplication
@EnableJpaRepositories(basePackages = {"org.brightworks.friflow.ticketing.repo"})
@EntityScan(basePackages =
        {"org.brightworks.friflow.ticketing.domain",
         "org.brightworks.friflow.ticketing.domain.process"
        })
@EnableEurekaClient
@EnableDiscoveryClient
public class Application {

    public static void main(String[] args) {
        new SpringApplicationBuilder(Application.class).run();
    }

}
