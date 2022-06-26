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

    public Transfer newTransfer(Integer deliveryId, Inventory transferFrom,  Inventory transferTo) {
        Transfer transfer = new Transfer(deliveryId, transferFrom, transferTo);
        transferRepo.save(transfer);
        return (transfer);
    }

    public void addToTransfer(Pallet pallet, Transfer transfer) {
        if (!pallet.getLocation().equals(transfer.getOrigin())) return;

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
    public PalletContainer newPalletContainer(String name, double weight, Short defaultAmount) {
        PalletContainer palletContainer = new PalletContainer(name,weight,defaultAmount);
        containerRepo.save(palletContainer);
        return (palletContainer);
    }
    public Inventory newLocation(Integer id, String description) {
        Inventory inventory = new Inventory(id, description);
        inventoryRepo.save(inventory);
        return (inventory);
    }

    public Pallet savePallet(Long barcode, Integer palletTypeId, Integer palletContainerId, Short amount, Integer content, double weightGross, Integer locationId) {
        if (locationId == null) return null;
        if (barcode == null) barcode = palletRepo.generateBarcode();
        else if (palletRepo.existsById(barcode)) return null;
        Inventory inventory = inventoryRepo.findById(locationId).orElseThrow(() -> new RuntimeException(Errors.PALLET_CONTENTS_NOT_FOUND.toString()));


        PalletType palletType;
        PalletContent palletContent;
        PalletContainer palletContainer;

        if (palletTypeId == null) {
            palletType = palletTypeRepo.suggestPalletType(inventory.getName()).orElseThrow(() -> new RuntimeException(Errors.PALLET_TYPE_NOT_FOUND.toString()));
        } else {
            palletType = palletTypeRepo.findById(palletTypeId).orElseThrow(() -> new RuntimeException(Errors.PALLET_TYPE_NOT_FOUND.toString()));
        }
        if (content == null) {
            palletContent = palletContentRepo.suggestContent(inventory.getName()).orElseThrow(() -> new RuntimeException(Errors.PALLET_CONTENTS_NOT_FOUND.toString()));
        } else {
            palletContent = palletContentRepo.findById(content).orElseThrow(() -> new RuntimeException(Errors.PALLET_CONTENTS_NOT_FOUND.toString()));
        }
        if (palletContainerId == null) {
            palletContainer = containerRepo.suggestContainer(inventory.getName()).orElseThrow(() -> new RuntimeException(Errors.PALLET_CONTAINER_NOT_FOUND.toString()));
        } else {
            palletContainer = containerRepo.findById(palletContainerId).orElseThrow(() -> new RuntimeException(Errors.PALLET_CONTAINER_NOT_FOUND.toString()));
        }



        if (amount == null) amount = palletContainer.getDefaultAmount();
        float minWeight = (float) (palletType.getWeight() + (palletContainer.getWeight() * amount));
        if (weightGross <= minWeight) return null;


        Pallet pallet = new Pallet(barcode, palletType, palletContainer, amount, palletContent, weightGross, weightGross-minWeight, inventory);
        palletRepo.save(pallet);
        return (pallet);
    }
}
