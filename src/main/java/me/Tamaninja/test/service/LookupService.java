package me.Tamaninja.test.service;

import me.Tamaninja.test.dto.PalletContainerDto;
import me.Tamaninja.test.dto.PalletDto;
import me.Tamaninja.test.dto.TransferDto;
import me.Tamaninja.test.entity.Inventory;
import me.Tamaninja.test.entity.Pallet;
import me.Tamaninja.test.entity.PalletContainer;
import me.Tamaninja.test.enums.Errors;
import me.Tamaninja.test.repository.*;
import me.Tamaninja.test.util.ClassMapperUtil;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.List;

import static me.Tamaninja.test.util.ClassMapperUtil.mapClass;
import static me.Tamaninja.test.util.ClassMapperUtil.mapClassIgnoreLazy;

@Service
public class LookupService {
    private final PalletRepo palletRepo;
    private final PalletContainerRepo containerRepo;
    private final PalletTypeRepo palletTypeRepo;
    private final PalletContentRepo palletContentRepo;
    private final InventoryRepo inventoryRepo;
    private final TransferRepo transferRepo;

    public LookupService(PalletRepo palletRepo, PalletContainerRepo containerRepo, PalletTypeRepo palletTypeRepo, PalletContentRepo palletContentRepo, InventoryRepo inventoryRepo, TransferRepo transferRepo) {
        this.palletRepo = palletRepo;
        this.containerRepo = containerRepo;
        this.palletTypeRepo = palletTypeRepo;
        this.palletContentRepo = palletContentRepo;
        this.inventoryRepo = inventoryRepo;
        this.transferRepo = transferRepo;
    }

    public PalletDto findPallet(Long barcode) {
        Pallet pallet = palletRepo.findById(barcode).orElseThrow(() -> new RuntimeException(Errors.NOT_FOUND.toString()));
        PalletDto palletDto = mapClassIgnoreLazy(pallet, PalletDto.class);
        palletDto.setTransfers(ClassMapperUtil.mapListIgnoreLazyCollection(pallet.getTransfers(), TransferDto.class));
        return (palletDto);
    }
}
