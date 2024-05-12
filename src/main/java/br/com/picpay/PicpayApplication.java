package br.com.picpay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
@EnableJpaRepositories
@SpringBootApplication
public class PicpayApplication {

	public static void main(String[] args) {
		SpringApplication.run(PicpayApplication.class, args);
	}

}
