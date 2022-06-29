package me.Tamaninja.test.service;

import me.Tamaninja.test.dto.PalletDto;
import me.Tamaninja.test.entity.*;
import me.Tamaninja.test.repository.*;
import org.springframework.stereotype.Service;

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
    public PalletContent newPalletContent(String name, Double weightModifier) {
        PalletContent palletContent = new PalletContent(name, weightModifier);
        palletContentRepository.save(palletContent);
        return (palletContent);
    }
    public PalletContainer newPalletContainer(String name, double weight, Integer defaultAmount) {
        PalletContainer palletContainer = new PalletContainer(name,weight,defaultAmount);
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

    public Pallet savePallet(Long barcode, Integer palletTypeId, Integer palletContainerId, Integer containerAmount, Integer palletContentId, double grossWeight, Inventory origin) {
        if (origin == null) {
            return null;
        }
        if (barcode == null) barcode = palletRepository.generateBarcode();
        else if (palletRepository.existsById(barcode)) {
            return null;
        }

        PalletContainer palletContainer = findPalletContainer(palletContainerId, origin);
        PalletContainer palletType = findPalletContainer(palletTypeId, origin);
        PalletContent palletContent = findPalletContent(palletContentId, origin);

        if (palletContainer == null || palletContent == null || palletType == null) {return null;
        }

        if (containerAmount == null) containerAmount = palletContainer.getDefaultAmount();
        double netWeight = (grossWeight - containerAmount*palletContainer.getWeight() - palletType.getWeight())*palletContent.getWeightModifier();
        if (netWeight < 0)  {
            return null;
        }
        Pallet pallet = new Pallet(barcode, origin, palletType,palletContent, palletContainer, containerAmount, grossWeight, netWeight);
        palletRepository.save(pallet);
        PalletDto palletDto = mapClassIgnoreLazy(pallet, PalletDto.class);
        return (pallet);
    }
    public PalletContent findPalletContent(Integer id, Inventory inventory) {
        PalletContent palletContent;
        if (id == null) {            palletContent = palletContentRepository.mostUsedContent(inventory.getId());
        } else {
            palletContent = palletContentRepository.findById(id).orElse(null);
        }
        return (palletContent);
    }
    public PalletContainer findPalletContainer(Integer id, Inventory inventory) {
        PalletContainer palletContainer;
        if (id == null) {
            palletContainer = palletContainerRepository.mostUsedContainer(inventory.getId());
        } else {
            palletContainer = palletContainerRepository.findById(id).orElse(null);
        }
        return (palletContainer);
    }
}
