package me.Tamaninja.test.service;

import me.Tamaninja.test.dto.PalletDto;
import me.Tamaninja.test.entity.*;
import me.Tamaninja.test.repository.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<PalletDto> createPallet(PalletDto request){
        ResponseEntity<PalletDto> response;
        Pallet pallet = newPallet(request.getBarcode(), request.getPalletType(), request.getPalletContent(), request.getContainerAmount(), request.getPalletContainer(), request.getWeightGross(), request.getOrigin());
        PalletDto palletDTO;

        if (pallet != null) {
            palletRepository.save(pallet);
            palletDTO = mapClassIgnoreLazy(pallet, PalletDto.class);
            response = new ResponseEntity<PalletDto>(palletDTO, HttpStatus.OK);
        } else {
            response = new ResponseEntity<PalletDto>(HttpStatus.I_AM_A_TEAPOT);
        }

        return (response);
    }

    public Pallet newPallet(Long barcode, String palletTypeIdentifier, String palletContainerIdentifier, Integer containerAmount, String palletContentIdentifier, double grossWeight, String originIdentifier) {
        Inventory origin = inventoryRepository.findByName(originIdentifier).orElse(null);
        PalletContainer palletContainer = getPalletContainer(palletContainerIdentifier, origin);
        PalletContainer palletType = getPalletContainer(palletTypeIdentifier, origin);
        PalletContent palletContent = getPalletContent(palletContentIdentifier, origin);
        if (containerAmount == null) {
            containerAmount = palletContainer.getDefaultAmount();
        }
        Pallet pallet = new Pallet(barcode, origin, palletType, palletContent, palletContainer, containerAmount, grossWeight);
        if (isValid(pallet)) {
            return (pallet);
        } else {
            return (null);
        }
    }

    public Boolean isValid(Pallet pallet) {
        if (pallet.getPalletContainer() == null
                || pallet.getPalletContent() == null
                || pallet.getPalletType() == null
                || pallet.getOrigin() == null
                || pallet.getWeightNet() < 0) {
            return (false);
        }
        if (pallet.getBarcode() == null) {
            pallet.setBarcode(palletRepository.generateBarcode());
        }
        return (true);
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
        inventory.setIdentifier(inventory.getId() + ">" + parent.getIdentifier());
        inventoryRepository.save(inventory);
        return (inventory);
    }
    public List<PalletContainer> getAllPalletTypes() {
        return (palletContainerRepository.getAllPalletTypes());
    }

    public List<PalletContainer> getAllPalletContainers() {
        return (palletContainerRepository.getAllPalletContainers());
    }

    public List<PalletContent> getAllPalletContents() {
        return (palletContentRepository.findAll());
    }

    public PalletDto savePallet(Long barcode, String palletTypeIdentifier, String palletContainerIdentifier, Integer containerAmount, String palletContentIdentifier, double grossWeight, String origin) {
        Pallet pallet = newPallet(barcode, palletTypeIdentifier, palletContainerIdentifier, containerAmount, palletContentIdentifier,grossWeight, origin);
        if (pallet != null) {
            palletRepository.save(pallet);
        }
        PalletDto palletDto = mapClassIgnoreLazy(pallet, PalletDto.class);
        return (palletDto);
    }

    public PalletContent getPalletContent(String identifier, Inventory inventory) {
        PalletContent palletContent;
        if (identifier == null) {
            palletContent = palletContentRepository.mostUsedContent(inventory.getId());
        } else {
            palletContent = palletContentRepository.findById(identifier).orElse(null);
        }
        return (palletContent);
    }
    public PalletContainer getPalletContainer(String identifier, Inventory inventory) {
        PalletContainer palletContainer;
        if (identifier == null) {
            palletContainer = palletContainerRepository.mostUsedContainer(inventory.getId());
        } else {
            palletContainer = palletContainerRepository.findById(identifier).orElse(null);
        }
        return (palletContainer);
    }
}
