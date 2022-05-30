package me.Tamaninja.test.Service;

import me.Tamaninja.test.Pallet;

public interface PalletService {
    Pallet newPallet(int weightGross);
    Pallet newPallet(Long barcode, int weightGross);
    void deletePallet(Pallet pallet);
    void deletePallet(Long id);
}
