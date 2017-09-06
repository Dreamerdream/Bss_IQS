package com.bss.iqs;

import com.bss.iqs.config.MyExceptionResolver;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.bss.iqs.mapper")
public class IqsApplication {

	public static void main(String[] args) {
		SpringApplication.run(IqsApplication.class, args);
	}
	@Bean
	public MyExceptionResolver myExceptionResolver(){
		return new MyExceptionResolver();
	}
}
