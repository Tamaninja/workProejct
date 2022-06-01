package me.Tamaninja.test.service;

import me.Tamaninja.test.entity.Pallet;
import me.Tamaninja.test.repository.PalletRepo;
import org.springframework.stereotype.Service;

@Service
public class PalletService {
    private final PalletRepo palletRepo;
    public PalletService(PalletRepo palletRepo) {
        this.palletRepo = palletRepo;
    }

    public Pallet newPallet(int weightGross) {
        Pallet pallet = new Pallet(freshBarcode(),weightGross);
        palletRepo.save(pallet);
        return (pallet);
    }

    public Pallet newPallet(Long barcode, int weightGross) {

        if (palletRepo.existsById(barcode)) return null; //Return if barcode already exist

        Pallet pallet = new Pallet(barcode,weightGross);
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
