package br.com.zup.transacaobootcamp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class TransacaoBootcampApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransacaoBootcampApplication.class, args);
	}

}
