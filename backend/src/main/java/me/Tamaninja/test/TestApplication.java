package me.Tamaninja.test;

import me.Tamaninja.test.entity.*;
import me.Tamaninja.test.service.InventoryManagementService;
import org.hibernate.Hibernate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Random;
import java.util.List;

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

				PalletContainer tray = inventoryManagementService.newPalletContainer("tray",0.97, 100);
				inventoryManagementService.newPalletContainer("smalltray",0.27,200);
				inventoryManagementService.newPalletContainer("bigtray",0.72, 150);
				PalletContainer woodenPallet = inventoryManagementService.newPalletContainer("wooden", 15, 1);
				inventoryManagementService.newPalletContainer("plastic", 20, 1);
				inventoryManagementService.newPalletContainer("bigwooden", 18, 1);
				PalletContent palletContent = inventoryManagementService.newPalletContent("dry", 1.0);
				inventoryManagementService.newPalletContent("wet",1.0);
				inventoryManagementService.newPalletContent("garbage", 1.0);
				inventoryManagementService.newPalletContent("empty",0.0);
				Inventory inv150 = inventoryManagementService.newLocation("150");
				Inventory inv200 = inventoryManagementService.newLocation("200");
				Transfer transfer = inventoryManagementService.newTransfer("303030",inv150, inv200);
				Transfer transfer1 = inventoryManagementService.newTransfer("303031",inv200, inv150);


				Random random = new Random();
				Inventory inventory = inventoryManagementService.newInventory(inv150);
				Inventory inventory1 = inventoryManagementService.newInventory(inv150);
				inventoryManagementService.savePallet(null, "wooden", "tray", 2, "dry", 200, inventory);
				List<PalletContainer> palletTypes = inventoryManagementService.getAllPalletTypes();
				List<PalletContainer> palletContainers = inventoryManagementService.getAllPalletContainers();
				List<PalletContent> palletContents = inventoryManagementService.getAllPalletContents();
				for (int i = 0; i < 250; i++) {
					PalletContainer randomType = palletTypes.get(random.nextInt(palletTypes.size()));
					PalletContainer randomContainer = palletContainers.get(random.nextInt(palletContainers.size()));
					PalletContent randomContent = palletContents.get(random.nextInt(palletContents.size()));
					int randomAmount = random.nextInt(100);
					double randomWeight = random.nextInt(700) + 150;
					Pallet pallet1 = inventoryManagementService.savePallet(null, randomType.getIdentifier(), randomContainer.getIdentifier(), 2, randomContent.getIdentifier(), 200, inventory);
					Transfer transfer2 = inventoryManagementService.newTransfer(inventory,inv150);
					inventoryManagementService.addToTransfer(pallet1, transfer2);
				}
				for (int i = 0; i < 250; i++) {
					int randomAmount = random.nextInt(100);
					double randomWeight = random.nextInt(700) + 150;
					inventoryManagementService.savePallet(null, woodenPallet.getIdentifier(), tray.getIdentifier(), 2, palletContent.getIdentifier(), 200, inventory);
				}
				Hibernate.initialize(inv150.getPallets());
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			}
		};
	}
}
