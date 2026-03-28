package com.example.hotel_ops;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HotelOpsApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelOpsApplication.class, args);
		String name = "Kaif";
		System.out.println("My name is " + name);
	}
}