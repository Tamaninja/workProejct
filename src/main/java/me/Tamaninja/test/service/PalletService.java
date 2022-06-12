package me.Tamaninja.test.service;

import me.Tamaninja.test.entity.Pallet;
import me.Tamaninja.test.entity.PalletContainer;
import me.Tamaninja.test.entity.PalletType;
import me.Tamaninja.test.enums.Errors;
import me.Tamaninja.test.repository.PalletContainerRepo;
import me.Tamaninja.test.repository.PalletRepo;
import me.Tamaninja.test.repository.PalletTypeRepo;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

import static org.apache.logging.log4j.ThreadContext.isEmpty;

@Service
public class PalletService {
    private final PalletRepo palletRepo;
    private final PalletContainerRepo containerRepo;
    private final PalletTypeRepo palletTypeRepo;
    public PalletService(PalletRepo palletRepo, PalletContainerRepo palletContainerRepo, PalletTypeRepo palletTypeRepo) {
        this.containerRepo = palletContainerRepo;
        this.palletRepo = palletRepo;
        this.palletTypeRepo = palletTypeRepo;
    }

    public Pallet newPallet(int weightGross, Long palletContainerId, Long palletTypeId) {
        Optional<PalletContainer> palletContainer = containerRepo.findById(palletContainerId);
        Optional<PalletType> palletType = palletTypeRepo.findById(palletTypeId);

        if (palletContainer.isEmpty()) {System.out.println(Errors.PALLET_CONTAINER_NOT_FOUND); return null;} //return if the container is not found
        if (palletType.isEmpty()) {System.out.println(Errors.PALLET_TYPE_NOT_FOUND); return null;} //return if the pallet type is not found


        Pallet pallet = new Pallet(freshBarcode(), weightGross, palletContainer.get(), palletType.get());
        palletRepo.save(pallet);
        return (pallet);
    }

    public Pallet newPallet(Long barcode, int weightGross, Long palletContainerId, Long palletTypeId) {
        Optional<PalletContainer> palletContainer = containerRepo.findById(palletContainerId);
        Optional<PalletType> palletType = palletTypeRepo.findById(palletTypeId);


        if (palletRepo.existsById(barcode)) {System.out.println(Errors.BARCODE_ALREADY_EXISTS); return null;} //return if barcode already exists
        if (palletContainer.isEmpty()) {System.out.println(Errors.PALLET_CONTAINER_NOT_FOUND); return null;} //return if the container is not found
        if (palletType.isEmpty()) {System.out.println(Errors.PALLET_TYPE_NOT_FOUND); return null;} //return if the pallet type is not found


        Pallet pallet = new Pallet(barcode, weightGross, palletContainer.get(), palletType.get());
        palletRepo.save(pallet);
        return (pallet);
    }




    public void deletePallet(Pallet pallet) {

    }

    public void deletePallet(Long id) {

    }
    private Long freshBarcode() {
        if (palletRepo.maxId() == null) {
            return (1L);
        }
        return (palletRepo.maxId() + 1);
    }
}
