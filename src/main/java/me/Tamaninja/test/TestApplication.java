package me.Tamaninja.test;

import me.Tamaninja.test.entity.Inventory;
import me.Tamaninja.test.entity.Pallet;
import me.Tamaninja.test.entity.Transfer;
import me.Tamaninja.test.service.ImportExportService;
import me.Tamaninja.test.service.InventoryManagementService;
import me.Tamaninja.test.service.LookupService;
import org.hibernate.Hibernate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Random;

@SpringBootApplication
public class TestApplication {
	public static void main(String[] args) {
		System.out.println("test");
		SpringApplication.run(TestApplication.class, args);

	}
	@Bean
	public CommandLineRunner run(InventoryManagementService inventoryManagementService, ImportExportService importExportService, LookupService lookupService) throws Exception {
		return args -> {
			try {

				inventoryManagementService.newPalletContainer("tray",0.97, 100);
				inventoryManagementService.newPalletContainer("smalltray",0.27,200);
				inventoryManagementService.newPalletContainer("bigtray",0.72, 150);
				inventoryManagementService.newPalletType("wooden", 15);
				inventoryManagementService.newPalletType("plastic", 20);
				inventoryManagementService.newPalletType("bigwooden", 18);
				inventoryManagementService.newPalletContent("dry");
				inventoryManagementService.newPalletContent("wet");
				inventoryManagementService.newPalletContent("garbage");
				Inventory inv150 = inventoryManagementService.newLocation("150");
				Inventory inv200 = inventoryManagementService.newLocation("200");
				Transfer transfer = inventoryManagementService.newTransfer("303030",inv150, inv200);
				Transfer transfer1 = inventoryManagementService.newTransfer("303031",inv200, inv150);


				inventoryManagementService.savePallet(null, 1, 2, 2, 2, 200, inv150);


				Random random = new Random();
				Inventory inventory = inventoryManagementService.newInventory(inv150);
				Inventory inventory1 = inventoryManagementService.newInventory(inv150);
				inventoryManagementService.savePallet(null, 1, 2, 2, 2, 200, inventory);
				for (int i = 0; i < 250; i++) {
					int randomType = random.nextInt(3) + 1;
					int randomContainer = random.nextInt(3) + 1;
					int randomContent = random.nextInt(3) + 1;
					int randomAmount = random.nextInt(100);
					double randomWeight = random.nextInt(700) + 150;
					Pallet pallet1 = inventoryManagementService.savePallet(null, randomType, randomContainer, randomAmount, randomContent, randomWeight, inventory);
					Transfer transfer2 = inventoryManagementService.newTransfer(inventory,inv150);
					inventoryManagementService.addToTransfer(pallet1, transfer2);
				}
				for (int i = 0; i < 250; i++) {
					int randomAmount = random.nextInt(100);
					double randomWeight = random.nextInt(700) + 150;
					inventoryManagementService.savePallet(null, 1, 1, randomAmount, 1, randomWeight,inventory1);
				}
				Hibernate.initialize(inv150.getPallets());
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			}
		};
	}
}
