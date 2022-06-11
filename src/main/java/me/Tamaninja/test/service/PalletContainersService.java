package me.Tamaninja.test.service;

import me.Tamaninja.test.entity.PalletContainer;
import me.Tamaninja.test.repository.PalletContainerRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PalletContainersService {

    private final PalletContainerRepo containerRepo;

    public PalletContainersService(PalletContainerRepo containerRepo) {
        this.containerRepo = containerRepo;
    }

    public PalletContainer newPalletContainer(String name, double weight, int defaultAmount) {
        PalletContainer palletContainer = new PalletContainer(name,weight,defaultAmount);
        containerRepo.save(palletContainer);
        return (palletContainer);
    }

    public PalletContainer findById(Long id) {
        return containerRepo.findById(id).get();
    }

    public void deletePalletContainer(PalletContainer palletContainer) {

    }

    public void deletePalletContainer(Long id) {

    }
}
