package me.Tamaninja.test.Service.Impl;

import me.Tamaninja.test.Pallet;
import me.Tamaninja.test.Service.PalletService;
import me.Tamaninja.test.TestRepo;
import org.springframework.stereotype.Service;

@Service
public class PalletServiceImpl implements PalletService {
    private final TestRepo testRepo;
    public PalletServiceImpl(TestRepo testRepo) {
        this.testRepo = testRepo;
    }

    @Override
    public Pallet newPallet(int weightGross) {
        Pallet pallet = new Pallet(freshBarcode(),weightGross);
        testRepo.save(pallet);
        return (pallet);
    }

    @Override
    public Pallet newPallet(Long barcode, int weightGross) {
        if (testRepo.existsById(barcode)) {
            throw (new IllegalArgumentException("BARCODE ALREADY EXISTS"));

        }
        Pallet pallet = new Pallet(barcode,weightGross);
        testRepo.save(pallet);
        return (pallet);
    }




    @Override
    public void deletePallet(Pallet pallet) {

    }

    @Override
    public void deletePallet(Long id) {

    }
    private Long freshBarcode() {
        if (testRepo.maxId() == null) {
            return (1L);
        }
        return (testRepo.maxId() + 1);
    }
}
