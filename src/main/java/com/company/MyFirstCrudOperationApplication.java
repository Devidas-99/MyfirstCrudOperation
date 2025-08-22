package com.company;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
@EnableScheduling
@SpringBootApplication
public class MyFirstCrudOperationApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyFirstCrudOperationApplication.class, args);

        System.out.println("This project created by Devidas");
	}


}
