package com.notebook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.notebook.config.ConfigBean;

@SpringBootApplication
@EnableConfigurationProperties({ConfigBean.class})
public class NotebookApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotebookApplication.class, args);
	}
}
