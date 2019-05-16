package com.chengredis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.chengredis.dao")
public class ChengredisApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChengredisApplication.class, args);
	}

}
