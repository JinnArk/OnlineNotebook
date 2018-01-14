package com.notebook;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
//扫描dao层文件
@MapperScan(basePackages = "com.notebook.dao") 
public class NotebookApplication {	
	public static void main(String[] args) {
		SpringApplication.run(NotebookApplication.class, args);
	}
}
