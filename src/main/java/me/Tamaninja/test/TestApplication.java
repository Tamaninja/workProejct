package me.Tamaninja.test;

import me.Tamaninja.test.service.ManagerService;
import me.Tamaninja.test.service.PalletContainersService;
import me.Tamaninja.test.service.PalletService;
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
	public CommandLineRunner run(ManagerService managerService) throws Exception {
		return args -> {
			try {
				System.out.println(managerService.getPalletService().newPallet(200));
				System.out.println(managerService.getPalletService().newPallet(50L,200));
				System.out.println(managerService.getPalletService().newPallet(50L,300));
				System.out.println(managerService.getPalletContainersService().newPalletContainer("tray",0.97,100));
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			}
		};
	}
}
