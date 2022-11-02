package me.Tamaninja.test.service;


import me.Tamaninja.test.entity.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;


@Service
public class DebugService {
    private final InventoryManagementService inventoryManagementService;

    public DebugService(InventoryManagementService inventoryManagementService) {
        this.inventoryManagementService = inventoryManagementService;
    }

    public void insertRandom(int amount) {
        List<PalletContainer> palletTypes = inventoryManagementService.getAllPalletTypes();
        List<PalletContainer> palletContainers = inventoryManagementService.getAllPalletContainers();
        List<PalletContent> palletContents = inventoryManagementService.getAllPalletContents();

        Random random = new Random();
        for (int i = 0; i < amount; i++) {
            PalletContainer randomType = palletTypes.get(random.nextInt(palletTypes.size()));
            PalletContainer randomContainer = palletContainers.get(random.nextInt(palletContainers.size()));
            PalletContent randomContent = palletContents.get(random.nextInt(palletContents.size()));
            inventoryManagementService.savePallet(null, randomType.getId(), randomContainer.getId(), 2, randomContent.getId(), 200, "150");
        }

    }



    public void init() {
        PalletContainer tray = inventoryManagementService.newPalletContainer("tray",0.97, 100);
        PalletContainer smallTray = inventoryManagementService.newPalletContainer("smalltray",0.27,200);
        PalletContainer bigTray = inventoryManagementService.newPalletContainer("bigtray",0.72, 150);
        PalletContainer testtest = inventoryManagementService.newPalletContainer("assssss",0.72, 150);

        PalletContainer woodenPallet = inventoryManagementService.newPalletContainer("wooden", 15, 1);
        PalletContainer plasticPallet = inventoryManagementService.newPalletContainer("plastic", 20, 1);
        PalletContainer bigWoodenPallet = inventoryManagementService.newPalletContainer("bigwooden", 18, 1);

        PalletContent dry = inventoryManagementService.newPalletContent("dry", 1.0);
        PalletContent wet = inventoryManagementService.newPalletContent("wet",1.0);
        PalletContent garbage = inventoryManagementService.newPalletContent("garbage", 1.0);
        PalletContent empty = inventoryManagementService.newPalletContent("empty",0.0);

        Inventory inv150 = inventoryManagementService.newLocation("150");
        Inventory inv200 = inventoryManagementService.newLocation("200");
    }


}
