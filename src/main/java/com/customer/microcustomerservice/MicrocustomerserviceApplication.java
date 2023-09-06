package com.customer.microcustomerservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableConfigurationProperties
//@EnableDiscoveryClient
@EnableSwagger2
public class MicrocustomerserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicrocustomerserviceApplication.class, args);
	}

}