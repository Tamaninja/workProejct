package me.Tamaninja.test;

import me.Tamaninja.test.dto.PalletDto;
import me.Tamaninja.test.entity.Inventory;
import me.Tamaninja.test.entity.Pallet;
import me.Tamaninja.test.entity.Pool;
import me.Tamaninja.test.entity.Transfer;
import me.Tamaninja.test.service.InventoryManagementService;
import me.Tamaninja.test.service.LookupService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Random;
import java.util.UUID;

@SpringBootApplication
public class TestApplication {
	public static void main(String[] args) {
		System.out.println("test");
		SpringApplication.run(TestApplication.class, args);

	}
	@Bean
	public CommandLineRunner run(InventoryManagementService inventoryManagementService, LookupService lookupService) throws Exception {
		return args -> {
			try {
				inventoryManagementService.newPalletContainer("tray",0.97, (short) 100);
				inventoryManagementService.newPalletContainer("smalltray",0.27,(short) 200);
				inventoryManagementService.newPalletContainer("bigtray",0.72,(short) 150);
				inventoryManagementService.newPalletType("wooden", 15);
				inventoryManagementService.newPalletType("plastic", 20);
				inventoryManagementService.newPalletType("bigwooden", 18);
				inventoryManagementService.newPalletContent("dry");
				inventoryManagementService.newPalletContent("wet");
				inventoryManagementService.newPalletContent("garbage");
				Inventory inv150 = inventoryManagementService.newLocation(UUID.randomUUID(),"150");
				Inventory inv200 = inventoryManagementService.newLocation(UUID.randomUUID(),"200");
				Transfer transfer = inventoryManagementService.newTransfer(303030,inv150, inv200);
				Transfer transfer1 = inventoryManagementService.newTransfer(303031,inv200, inv150);


				Random random = new Random();
				Pool pool = inventoryManagementService.newPool("hahaha");
				for (int i = 0; i < 250; i++) {
					Pool pool1 = inventoryManagementService.newPool("bababa");
					pool1.setParent(pool);
					int randomType = random.nextInt(3) + 1;
					int randomContainer = random.nextInt(3) + 1;
					int randomContent = random.nextInt(3) + 1;
					Short randomAmount = (short) random.nextInt(100);
					double randomWeight = random.nextInt(700) + 150f;
					PalletDto palletDto = inventoryManagementService.savePallet(null, randomType, randomContainer, randomAmount, randomContent, randomWeight, "150", pool1);
					Pallet pallet = lookupService.getPallet(palletDto.getBarcode());
					inventoryManagementService.addToTransfer(pallet, transfer);
					inventoryManagementService.addToTransfer(pallet, transfer1);
				}
				for (int i = 0; i < 250; i++) {
					Short randomAmount = (short) random.nextInt(100);
					double randomWeight = random.nextInt(700) + 150f;
					inventoryManagementService.savePallet(null, null, null, randomAmount, null, randomWeight, "150",pool);
				}
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			}
		};
	}
}
