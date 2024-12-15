package com.question3.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class Question3NodesRsApplication {

	public static void main(String[] args) {
		SpringApplication.run(Question3NodesRsApplication.class, args);
	}

}
