package com.asuis.j2ee;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.asuis.j2ee.dao")
public class J2eeApplication {

	public static void main(String[] args) {
		SpringApplication.run(J2eeApplication.class, args);
	}
}
