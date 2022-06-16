package me.Tamaninja.test;

import me.Tamaninja.test.service.InventoryManagementService;
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
	public CommandLineRunner run(InventoryManagementService inventoryManagementService) throws Exception {
		return args -> {
			try {
				System.out.println(inventoryManagementService.newPalletContainer("tray",0.97,100));
				System.out.println(inventoryManagementService.newPalletContainer("smalltray",0.27,200));
				System.out.println(inventoryManagementService.newPalletType("wooden", 15));
				System.out.println(inventoryManagementService.newPalletType("plastic", 20));
				System.out.println(inventoryManagementService.newPalletContent("dry"));
				System.out.println(inventoryManagementService.newPalletContent("wet"));
				System.out.println(inventoryManagementService.newLocation(150L,"masua warehouse"));
				System.out.println(inventoryManagementService.newLocation(200L,"tamar tov"));
				System.out.println(inventoryManagementService.savePallet(200L, 1L, 2L, null, "dry", 300, 150L));
				System.out.println(inventoryManagementService.savePallet(null, 2L, 1L, null, "wet", 250, 200L));
				System.out.println(inventoryManagementService.savePallet(null, 1L, 2L, null, "wet", 300, 200L));

			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			}
		};
	}
}
