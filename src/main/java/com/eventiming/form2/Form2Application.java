package com.eventiming.form2;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.eventiming.form2")
@EnableScheduling
public class Form2Application {
	public static void main(String[] args) {
		SpringApplication.run(Form2Application.class, args);
	}

}
