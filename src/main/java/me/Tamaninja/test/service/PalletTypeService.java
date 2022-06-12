package me.Tamaninja.test.service;

import me.Tamaninja.test.entity.PalletContainer;
import me.Tamaninja.test.entity.PalletType;
import me.Tamaninja.test.repository.PalletTypeRepo;
import org.springframework.stereotype.Service;

@Service
public class PalletTypeService {

    private final PalletTypeRepo palletTypeRepo;

    public PalletTypeService(PalletTypeRepo palletTypeRepo) {
        this.palletTypeRepo = palletTypeRepo;
    }

    public PalletType newPalletType(String name, double weight) {
        PalletType palletType = new PalletType(name, weight);
        palletTypeRepo.save(palletType);
        return (palletType);
    }

    public PalletType findById(Long id) {
        return palletTypeRepo.findById(id).get();
    }

    public void deletePalletContainer(PalletContainer palletContainer) {

    }

    public void deletePalletContainer(Long id) {

    }
}
