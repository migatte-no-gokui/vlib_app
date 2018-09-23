package com.vpacheco.vlib;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

@SpringBootApplication
@EntityScan(basePackageClasses = {
		VlibApplication.class,
		Jsr310JpaConverters.class
})
public class VlibApplication {

	public static void main(String[] args) {
		SpringApplication.run(VlibApplication.class, args);
	}
}
