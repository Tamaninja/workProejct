package me.Tamaninja.test.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManagerService {
    private PalletContainersService palletContainersService;
    private PalletService palletService;

    @Autowired
    public ManagerService(PalletContainersService palletContainersService, PalletService palletService) {
        this.palletContainersService = palletContainersService;
        this.palletService = palletService;
    }

    public PalletContainersService getPalletContainersService() {
        return palletContainersService;
    }

    public PalletService getPalletService() {
        return palletService;
    }
}
