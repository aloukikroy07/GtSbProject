package com.gt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.CrossOrigin;


@SpringBootApplication(scanBasePackages={"com.gt.controller", "com.gt.service", "com.gt.repository", "com.gt.model" })
@CrossOrigin(origins = "*")
public class GtSbProjectApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(GtSbProjectApplication.class, args);
	}

}
