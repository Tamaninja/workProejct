package me.Tamaninja.test.service;

import me.Tamaninja.test.entity.*;
import me.Tamaninja.test.enums.Errors;
import me.Tamaninja.test.repository.*;
import org.springframework.stereotype.Service;

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

    public Transfer newTransfer(Integer deliveryId, Inventory transferFrom, Inventory transferTruck, Inventory transferTo) {
        Transfer transfer = new Transfer(deliveryId, transferFrom, transferTruck, transferTo);
        transferRepo.save(transfer);
        return (transfer);
    }

    public void addTransfer(Pallet pallet, Transfer transfer) {
        transfer.addPallet(pallet);
        transferRepo.save(transfer);
        palletRepo.save(pallet);
    }
    public PalletType newPalletType(String name, float weight) {
        PalletType palletType = new PalletType(name, weight);
        palletTypeRepo.save(palletType);
        return (palletType);
    }

    public PalletContent newPalletContent(String name) {
        PalletContent palletContent = new PalletContent(name);
        palletContentRepo.save(palletContent);
        return (palletContent);
    }
    public PalletContainer newPalletContainer(String name, float weight, Short defaultAmount) {
        PalletContainer palletContainer = new PalletContainer(name,weight,defaultAmount);
        containerRepo.save(palletContainer);
        return (palletContainer);
    }
    public Inventory newLocation(Integer id, String description) {
        Inventory inventory = new Inventory(id, description);
        inventoryRepo.save(inventory);
        return (inventory);
    }

    public Pallet savePallet(Long barcode, Integer palletTypeId, Integer palletContainerId, Short amount, Integer content, float weightGross, Integer locationId) {
        PalletContainer palletContainer = containerRepo.findById(palletContainerId).orElseThrow(() -> new RuntimeException(Errors.PALLET_CONTAINER_NOT_FOUND.toString()));
        PalletType palletType = palletTypeRepo.findById(palletTypeId).orElseThrow(() -> new RuntimeException(Errors.PALLET_TYPE_NOT_FOUND.toString()));
        PalletContent palletContent = palletContentRepo.findById(content).orElseThrow(() -> new RuntimeException(Errors.PALLET_CONTENTS_NOT_FOUND.toString()));
        Inventory inventory = inventoryRepo.findById(locationId).orElseThrow(() -> new RuntimeException(Errors.PALLET_CONTENTS_NOT_FOUND.toString()));

        if (barcode == null) barcode = generatePalletBarcode();
        if (amount == null) amount = palletContainer.defaultAmount();
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
