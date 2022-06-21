package me.Tamaninja.test.service;

import me.Tamaninja.test.entity.*;
import me.Tamaninja.test.enums.Errors;
import me.Tamaninja.test.repository.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InventoryManagementService {
    private final PalletRepo palletRepo;
    private final PalletContainerRepo containerRepo;
    private final PalletTypeRepo palletTypeRepo;
    private final PalletContentRepo palletContentRepo;
    private final InventoryRepo inventoryRepo;
    private final TransferRepo transferRepo;

    public InventoryManagementService(PalletRepo palletRepo, PalletContainerRepo containerRepo, PalletTypeRepo palletTypeRepo, PalletContentRepo palletContentRepo, InventoryRepo inventoryRepo, TransferRepo transferRepo) {
        this.palletRepo = palletRepo;
        this.containerRepo = containerRepo;
        this.palletTypeRepo = palletTypeRepo;
        this.palletContentRepo = palletContentRepo;
        this.inventoryRepo = inventoryRepo;
        this.transferRepo = transferRepo;
    }


    public Transfer newTransfer(Long deliveryId, Inventory transferFrom, Inventory transferUsing, Inventory transferTo) {
        Transfer transfer = new Transfer(deliveryId, transferFrom, transferUsing, transferTo);
        transferRepo.save(transfer);
        return (transfer);
    }

    public void refreshTransfer(Transfer transfer) {
        String[] data = palletRepo.totalGrossWeightByTransfer(transfer).split(",");
        transfer.refresh(Double.parseDouble(data[0]), Double.parseDouble(data[1]), Integer.parseInt(data[2]));
    }

    public void addToDeliver(Pallet pallet, Transfer transfer) {
        transfer.addToDelivery(pallet);
        pallet.setTransfer(transfer);
        palletRepo.save(pallet);
        refreshTransfer(transfer);
        transferRepo.save(transfer);
    }
    public PalletType newPalletType(String name, double weight) {
        PalletType palletType = new PalletType(name, weight);
        palletTypeRepo.save(palletType);
        return (palletType);
    }

    public PalletContent newPalletContent(String name) {
        if (palletContentRepo.existsById("name")) {System.out.println(Errors.ALREADY_EXISTS); return null;} //return if already exists
        PalletContent palletContent = new PalletContent(name);
        palletContentRepo.save(palletContent);
        return (palletContent);
    }
    public PalletContainer newPalletContainer(String name, double weight, int defaultAmount) {
        PalletContainer palletContainer = new PalletContainer(name,weight,defaultAmount);
        containerRepo.save(palletContainer);
        return (palletContainer);
    }
    public Inventory newLocation(Long id, String description) {
        Inventory inventory = new Inventory(id, description);
        inventoryRepo.save(inventory);
        return (inventory);
    }

    public Pallet savePallet(Long barcode, Long palletTypeId, Long palletContainerId, Integer amount, String content, double weightGross, Long locationId) {
        PalletContainer palletContainer = containerRepo.findById(palletContainerId).orElseThrow(() -> new RuntimeException(Errors.PALLET_CONTAINER_NOT_FOUND.toString()));
        PalletType palletType = palletTypeRepo.findById(palletTypeId).orElseThrow(() -> new RuntimeException(Errors.PALLET_TYPE_NOT_FOUND.toString()));
        PalletContent palletContent = palletContentRepo.findById(content).orElseThrow(() -> new RuntimeException(Errors.PALLET_CONTENTS_NOT_FOUND.toString()));
        Inventory inventory = inventoryRepo.findById(locationId).orElseThrow(() -> new RuntimeException(Errors.PALLET_CONTENTS_NOT_FOUND.toString()));

        if (barcode == null) barcode = generatePalletBarcode();
        if (amount == null) amount = palletContainer.getDefaultAmount();
        if (palletRepo.existsById(barcode)) {System.out.println(Errors.ALREADY_EXISTS); return null;} //return if barcode already exists


        Pallet pallet = new Pallet(barcode, palletType, palletContainer, amount, palletContent, weightGross, inventory);
        return (palletRepo.save(pallet));
    }
    private Long generatePalletBarcode() {
        if (palletRepo.maxId() == null) {
            return (1L);
        }
        return (palletRepo.maxId() + 1);
    }
}
