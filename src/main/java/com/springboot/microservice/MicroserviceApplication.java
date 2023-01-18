package com.springboot.microservice;
import java.sql.*;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MicroserviceApplication {
//    Class.forName("com.mysql.jdbc.Driver");

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceApplication.class, args);
	}

}
