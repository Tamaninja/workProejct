package me.Tamaninja.test;

import me.Tamaninja.test.enums.Errors;
import me.Tamaninja.test.service.ManagerService;
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
				System.out.println(managerService.getPalletContainersService().newPalletContainer("tray",0.97,100));
				System.out.println(managerService.getPalletContainersService().newPalletContainer("smalltray",0.27,200));
				System.out.println(managerService.getPalletTypeService().newPalletType("wooden", 15));
				System.out.println(managerService.getPalletTypeService().newPalletType("plastic", 20));
				System.out.println(managerService.getPalletService().newPallet(200,1L,1L));
				System.out.println(managerService.getPalletService().newPallet(50L,200,2L,2L));
				System.out.println(managerService.getPalletService().newPallet(30L,300,1L, 1L));
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			}
		};
	}
}
