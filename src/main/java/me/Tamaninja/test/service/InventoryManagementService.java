package me.Tamaninja.test.service;

import me.Tamaninja.test.dto.PalletDto;
import me.Tamaninja.test.entity.*;
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

    public Transfer newTransfer(String identifier, Inventory transferFrom,  Inventory transferTo) {
        Transfer transfer = new Transfer(identifier, transferFrom, transferTo);
        transferRepo.save(transfer);
        return (transfer);
    }
    public Transfer newTransfer(Inventory transferFrom, Inventory transferTo) {
        Transfer transfer = new Transfer(transferFrom,transferTo);
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
    public PalletContainer newPalletContainer(String name, double weight, Integer defaultAmount) {
        PalletContainer palletContainer = new PalletContainer(name,weight,defaultAmount);
        containerRepo.save(palletContainer);
        return (palletContainer);
    }
    public Inventory newLocation(String name) {
        Inventory inventory = new Inventory(name);
        inventoryRepo.save(inventory);
        return (inventory);
    }
    public Inventory newInventory(Inventory inventory) {
        Inventory inventory1 = new Inventory(inventory);
        inventoryRepo.save(inventory1);
        return (inventory1);
    }

    public Pallet savePallet(Long barcode, Integer palletTypeId, Integer palletContainerId, Integer containerAmount, Integer palletContentId, double grossWeight, Inventory origin) {
        if (origin == null) {
            System.out.println("origin is nal");
            return null;
        }
        if (barcode == null) barcode = palletRepo.generateBarcode();
        else if (palletRepo.existsById(barcode)) {
            System.out.printf("barcode already exists");
            return null;
        }

        PalletContainer palletContainer = findPalletContainer(palletContainerId, origin);
        PalletContent palletContent = findPalletContent(palletContentId, origin);
        PalletType palletType = findPalletType(palletTypeId, origin);
        if (palletContainer == null || palletContent == null || palletType == null) {
            System.out.printf("types are null");
            return null;
        }

        if (containerAmount == null) containerAmount = palletContainer.getDefaultAmount();
        double MIN_WEIGHT = (palletType.getWeight() + (palletContainer.getWeight() * containerAmount));
        if (grossWeight <= MIN_WEIGHT)  {
            System.out.printf("invalid weight");
            return null;
        }

        Pallet pallet = new Pallet(barcode, palletType, palletContainer, containerAmount, palletContent, grossWeight, grossWeight-MIN_WEIGHT, origin);
        palletRepo.save(pallet);
        PalletDto palletDto = mapClassIgnoreLazy(pallet, PalletDto.class);
        return (pallet);
    }
    public PalletType findPalletType(Integer id, Inventory inventory) {
        PalletType palletType;
        if (id == null) {
            palletType = palletTypeRepo.mostUsedPalletType(inventory.getId());
        } else {
            palletType = palletTypeRepo.findById(id).orElse(null);
        }
        return (palletType);
    }

    public PalletContent findPalletContent(Integer id, Inventory inventory) {
        PalletContent palletContent;
        if (id == null) {
            palletContent = palletContentRepo.mostUsedContent(inventory.getId());
        } else {
            palletContent = palletContentRepo.findById(id).orElse(null);
        }
        return (palletContent);
    }
    public PalletContainer findPalletContainer(Integer id, Inventory inventory) {
        PalletContainer palletContainer;
        if (id == null) {
            palletContainer = containerRepo.mostUsedContainer(inventory.getId());
        } else {
            palletContainer = containerRepo.findById(id).orElse(null);
        }
        return (palletContainer);
    }
}
