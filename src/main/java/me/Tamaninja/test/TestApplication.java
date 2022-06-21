package me.Tamaninja.test;

import me.Tamaninja.test.entity.Inventory;
import me.Tamaninja.test.entity.Pallet;
import me.Tamaninja.test.entity.Transfer;
import me.Tamaninja.test.entity.Truck;
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
				inventoryManagementService.newPalletContainer("tray",0.97,100);
				inventoryManagementService.newPalletContainer("smalltray",0.27,200);
				inventoryManagementService.newPalletType("wooden", 15);
				inventoryManagementService.newPalletType("plastic", 20);
				inventoryManagementService.newPalletContent("dry");
				inventoryManagementService.newPalletContent("wet");
				Inventory inv150 = inventoryManagementService.newLocation(150L,"masua warehouse");
				Inventory inv200 = inventoryManagementService.newLocation(200L,"tamar tov");
				Pallet pallet1 = inventoryManagementService.savePallet(200L, 1L, 2L, null, "dry", 300, 150L);
				Pallet pallet2 = inventoryManagementService.savePallet(null, 2L, 1L, null, "wet", 250, 200L);
				Pallet pallet3 = inventoryManagementService.savePallet(null, 1L, 2L, null, "wet", 300, 200L);
				Truck truck1 = inventoryManagementService.newTruck(999999999L,"tama ninja");
				Transfer transfer = inventoryManagementService.newTransfer(303030L,inv150, truck1, inv200);
				inventoryManagementService.addPalletToTransfer(pallet1, transfer);
				inventoryManagementService.addPalletToTransfer(pallet2,transfer);
				Transfer transfer1 = inventoryManagementService.newTransfer(303031L,inv150,truck1,inv200);
				inventoryManagementService.addPalletToTransfer(pallet2,transfer1);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			}
		};
	}
}
