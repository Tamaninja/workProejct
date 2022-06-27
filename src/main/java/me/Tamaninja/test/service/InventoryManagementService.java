package me.Tamaninja.test.service;

import me.Tamaninja.test.dto.PalletDto;
import me.Tamaninja.test.entity.*;
import me.Tamaninja.test.enums.Errors;
import me.Tamaninja.test.repository.*;
import org.springframework.stereotype.Service;

import static me.Tamaninja.test.util.ClassMapperUtil.mapClassIgnoreLazy;

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

    public PalletDto savePallet(Long barcode, Integer palletTypeId, Integer palletContainerId, Short containerAmount, Integer palletContentId, double grossWeight, Integer locationId) {
        if (locationId == null) return null;
        if (barcode == null) barcode = palletRepo.generateBarcode();
        else if (palletRepo.existsById(barcode)) return null;
        Inventory inventory = inventoryRepo.findById(locationId).orElseThrow(() -> new RuntimeException(Errors.INVALID_ID.toString()));

        PalletContainer palletContainer = findPalletContainer(palletContainerId, inventory.getName());
        PalletContent palletContent = findPalletContent(palletContentId, inventory.getName());
        PalletType palletType = findPalletType(palletTypeId, inventory.getName());

        if (containerAmount == null) containerAmount = palletContainer.getDefaultAmount();
        double MIN_WEIGHT = (palletType.getWeight() + (palletContainer.getWeight() * containerAmount));
        if (grossWeight <= MIN_WEIGHT) return null;

        Pallet pallet = new Pallet(barcode, palletType, palletContainer, containerAmount, palletContent, grossWeight, grossWeight-MIN_WEIGHT, inventory);
        palletRepo.save(pallet);
        PalletDto palletDto = mapClassIgnoreLazy(pallet, PalletDto.class);
        return (palletDto);
    }
    public PalletType findPalletType(Integer id, String inventoryName) {
        PalletType palletType;
        if (id == null) {
            palletType = palletTypeRepo.mostUsedPalletType(inventoryName).orElseThrow(() -> new RuntimeException(Errors.INVALID_ID.toString()));
        } else {
            palletType = palletTypeRepo.findById(id).orElseThrow(() -> new RuntimeException(Errors.INVALID_ID.toString()));
        }
        return (palletType);
    }

    public PalletContent findPalletContent(Integer id, String inventoryName) {
        PalletContent palletContent;
        if (id == null) {
            palletContent = palletContentRepo.mostUsedContent(inventoryName).orElseThrow(() -> new RuntimeException(Errors.INVALID_ID.toString()));
        } else {
            palletContent = palletContentRepo.findById(id).orElseThrow(() -> new RuntimeException(Errors.INVALID_ID.toString()));
        }
        return (palletContent);
    }
    public PalletContainer findPalletContainer(Integer id, String inventoryName) {
        PalletContainer palletContainer;
        if (id == null) {
            palletContainer = containerRepo.mostUsedContainer(inventoryName).orElseThrow(() -> new RuntimeException(Errors.INVALID_ID.toString()));
        } else {
            palletContainer = containerRepo.findById(id).orElseThrow(() -> new RuntimeException(Errors.INVALID_ID.toString()));
        }
        return (palletContainer);
    }
}
