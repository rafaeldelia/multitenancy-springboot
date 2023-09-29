package com.amran.dynamic.multitenant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.arlepton.apis.framework", "com.amran.dynamic.multitenant"})
public class DynamicmultitenantApplication {

	public static void main(String[] args) {
		SpringApplication.run(DynamicmultitenantApplication.class, args);
	}

}
