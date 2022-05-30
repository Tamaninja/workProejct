package me.Tamaninja.test;

import me.Tamaninja.test.Service.Impl.PalletServiceImpl;
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
	public CommandLineRunner run(PalletServiceImpl palletService) throws Exception {
		return args -> {
			try {
				System.out.println(palletService.newPallet(200));
				System.out.println(palletService.newPallet(50L,200));
				System.out.println(palletService.newPallet(50L,300));
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			}
		};
	}
}
