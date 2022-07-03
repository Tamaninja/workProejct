package me.Tamaninja.test;

import me.Tamaninja.test.service.DebugService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class TestApplication {
	public static void main(String[] args) {
		System.out.println("test");
		SpringApplication.run(TestApplication.class, args);
	}

	@Bean
	public CommandLineRunner run(DebugService debugService){
		return args -> {
			try {
				debugService.init();
				debugService.insertRandom(500);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			}
		};
	}
}