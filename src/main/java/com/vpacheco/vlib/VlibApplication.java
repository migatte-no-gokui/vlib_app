package com.vpacheco.vlib;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackageClasses = {
		VlibApplication.class
})
public class VlibApplication {

	public static void main(String[] args) {
		SpringApplication.run(VlibApplication.class, args);
	}
}
