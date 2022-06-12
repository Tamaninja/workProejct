package me.Tamaninja.test.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManagerService {
    private PalletContainersService palletContainersService;
    private PalletService palletService;
    private PalletTypeService palletTypeService;

    @Autowired
    public ManagerService(PalletContainersService palletContainersService, PalletService palletService, PalletTypeService palletTypeService) {
        this.palletContainersService = palletContainersService;
        this.palletService = palletService;
        this.palletTypeService = palletTypeService;
    }

    public PalletContainersService getPalletContainersService() {
        return palletContainersService;
    }

    public PalletService getPalletService() {
        return palletService;
    }

    public PalletTypeService getPalletTypeService() {
        return palletTypeService;
    }
}
