package com.bss.iqs;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.bss.iqs.mapper*")
public class IqsApplication {

	public static void main(String[] args) {
		SpringApplication.run(IqsApplication.class, args);
	}
}
