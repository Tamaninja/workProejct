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
    private final LocationRepo locationRepo;

    public InventoryManagementService(PalletRepo palletRepo, PalletContainerRepo containerRepo, PalletTypeRepo palletTypeRepo, PalletContentRepo palletContentRepo, LocationRepo locationRepo) {
        this.palletRepo = palletRepo;
        this.containerRepo = containerRepo;
        this.palletTypeRepo = palletTypeRepo;
        this.palletContentRepo = palletContentRepo;
        this.locationRepo = locationRepo;
    }


    public Pallet newPallet(int weightGross, Long palletContainerId, Long palletTypeId, String content, int locationId) {
        return (savePallet(generatePalletBarcode(),weightGross,palletContainerId,palletTypeId,content,locationId));
    }
    public Pallet newPallet(Long barcode, int weightGross, Long palletContainerId, Long palletTypeId, String content, int locationId) {
        return (savePallet(barcode,weightGross,palletContainerId,palletTypeId,content,locationId));
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
    public Location newLocation(int id, String description) {
        Location location = new Location(id, description);
        locationRepo.save(location);
        return (location);
    }

    public Pallet savePallet(Long barcode, int weightGross, Long palletContainerId, Long palletTypeId, String content, int locationId) {
        PalletContainer palletContainer = containerRepo.findById(palletContainerId).orElseThrow(() -> new RuntimeException(Errors.PALLET_CONTAINER_NOT_FOUND.toString()));
        PalletType palletType = palletTypeRepo.findById(palletTypeId).orElseThrow(() -> new RuntimeException(Errors.PALLET_TYPE_NOT_FOUND.toString()));
        PalletContent palletContent = palletContentRepo.findById(content).orElseThrow(() -> new RuntimeException(Errors.PALLET_CONTENTS_NOT_FOUND.toString()));
        Location location = locationRepo.findById(locationId).orElseThrow(() -> new RuntimeException(Errors.PALLET_CONTENTS_NOT_FOUND.toString()));


        if (palletRepo.existsById(barcode)) {System.out.println(Errors.ALREADY_EXISTS); return null;} //return if barcode already exists


        Pallet pallet = new Pallet(barcode, weightGross, palletContainer, palletType, palletContent, location);
        return (palletRepo.save(pallet));
    }
    private Long generatePalletBarcode() {
        if (palletRepo.maxId() == null) {
            return (1L);
        }
        return (palletRepo.maxId() + 1);
    }
}
