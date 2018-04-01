package me.wlins.wloj_back;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;

@SpringBootApplication
public class WlojBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(WlojBackApplication.class, args);
	}
}
