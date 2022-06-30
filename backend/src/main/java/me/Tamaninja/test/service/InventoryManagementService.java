package me.Tamaninja.test.service;

import me.Tamaninja.test.dto.PalletDto;
import me.Tamaninja.test.entity.*;
import me.Tamaninja.test.repository.*;
import org.springframework.stereotype.Service;
import java.util.List;

import static me.Tamaninja.test.util.ClassMapperUtil.mapClassIgnoreLazy;

@Service
public class InventoryManagementService {
    private final PalletRepository palletRepository;
    private final PalletContainerRepository palletContainerRepository;
    private final PalletContentRepository palletContentRepository;
    private final InventoryRepository inventoryRepository;
    private final TransferRepository transferRepository;
    public InventoryManagementService(PalletRepository palletRepository, PalletContainerRepository containerRepo, PalletContentRepository palletContentRepository, InventoryRepository inventoryRepository, TransferRepository transferRepository) {
        this.palletRepository = palletRepository;
        this.palletContainerRepository = containerRepo;
        this.palletContentRepository = palletContentRepository;
        this.inventoryRepository = inventoryRepository;
        this.transferRepository = transferRepository;
    }
    public Transfer newTransfer(String identifier, Inventory transferFrom,  Inventory transferTo) {
        Transfer transfer = new Transfer(identifier, transferFrom, transferTo);
        transferRepository.save(transfer);
        return (transfer);
    }
    public Transfer newTransfer(Inventory transferFrom, Inventory transferTo) {
        Transfer transfer = new Transfer(transferFrom,transferTo);
        transferRepository.save(transfer);
        return (transfer);
    }

    public void addToTransfer(Pallet pallet, Transfer transfer) {
        if (!pallet.getLocation().equals(transfer.getOrigin())) return;
        transfer.addPallet(pallet);
        transferRepository.save(transfer);
        palletRepository.save(pallet);
    }
    public PalletContent newPalletContent(String identifier, Double weightModifier) {
        PalletContent palletContent = new PalletContent(identifier, weightModifier);
        palletContentRepository.save(palletContent);
        return (palletContent);
    }
    public PalletContainer newPalletContainer(String identifier, double weight, Integer defaultAmount) {
        PalletContainer palletContainer = new PalletContainer(identifier,weight,defaultAmount);
        palletContainerRepository.save(palletContainer);
        return (palletContainer);
    }
    public Inventory newLocation(String name) {
        Inventory inventory = new Inventory(name);
        inventoryRepository.save(inventory);
        return (inventory);
    }
    public Inventory newInventory(Inventory parent) {
        Inventory inventory = new Inventory(parent);
        inventoryRepository.save(inventory);
        inventory.setName(inventory.getId() + ">" + parent.getName());
        inventoryRepository.save(inventory);
        return (inventory);
    }
    public List<PalletContainer> getAllPalletTypes() {
        return (palletContainerRepository.findAllPalletTypes());
    }

    public List<PalletContainer> getAllPalletContainers() {
        return (palletContainerRepository.findAllPalletContainers());
    }

    public List<PalletContent> getAllPalletContents() {
        return (palletContentRepository.findAll());
    }

    public Pallet savePallet(Long barcode, PalletContainer palletTypeIdentifier, PalletContainer palletContainerIdentifier, Integer containerAmount, PalletContent palletContentIdentifier, double grossWeight, Inventory origin) {
        if (origin == null) {
            return null;
        }
        if (barcode == null) barcode = palletRepository.generateBarcode();
        else if (palletRepository.existsById(barcode)) {
            return null;
        }

//        PalletContainer palletContainer = findPalletContainer(palletContainerIdentifier, origin);
//        PalletContainer palletType = findPalletContainer(palletTypeIdentifier, origin);
//        PalletContent palletContent = findPalletContent(palletContentIdentifier, origin);

        if (palletContainerIdentifier == null || palletContentIdentifier == null || palletTypeIdentifier == null) {return null;
        }

        if (containerAmount == null) containerAmount = palletContainerIdentifier.getDefaultAmount();
        double netWeight = (grossWeight - containerAmount*palletContainerIdentifier.getWeight() - palletTypeIdentifier.getWeight())*palletContentIdentifier.getWeightModifier();
        if (netWeight < 0)  {
            return null;
        }
        Pallet pallet = new Pallet(barcode, origin, palletTypeIdentifier,palletContentIdentifier, palletContainerIdentifier, containerAmount, grossWeight, netWeight);
        palletRepository.save(pallet);
        PalletDto palletDto = mapClassIgnoreLazy(pallet, PalletDto.class);
        return (pallet);
    }
    public PalletContent findPalletContent(String identifier, Inventory inventory) {
        PalletContent palletContent;
        if (identifier == null) {
            palletContent = palletContentRepository.mostUsedContent(inventory.getId());
        } else {
            palletContent = palletContentRepository.findById(identifier).orElse(null);
        }
        return (palletContent);
    }
    public PalletContainer findPalletContainer(String identifier, Inventory inventory) {
        PalletContainer palletContainer;
        if (identifier == null) {
            palletContainer = palletContainerRepository.mostUsedContainer(inventory.getId());
        } else {
            palletContainer = palletContainerRepository.findById(identifier).orElse(null);
        }
        return (palletContainer);
    }
}
