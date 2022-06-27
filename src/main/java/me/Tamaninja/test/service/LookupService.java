package me.Tamaninja.test.service;

import me.Tamaninja.test.dto.InventoryDto;
import me.Tamaninja.test.dto.PalletDto;
import me.Tamaninja.test.dto.TransferDto;
import me.Tamaninja.test.entity.Inventory;
import me.Tamaninja.test.entity.Pallet;
import me.Tamaninja.test.entity.Transfer;
import me.Tamaninja.test.enums.Errors;
import me.Tamaninja.test.repository.*;
import me.Tamaninja.test.util.ClassMapperUtil;
import org.springframework.stereotype.Service;

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

    public InventoryDto findInventory(String name) {

        Inventory inventory = inventoryRepo.findByName(name).orElseThrow(() -> new RuntimeException(Errors.NOT_FOUND.toString()));
        InventoryDto inventoryDto = mapClassIgnoreLazy(inventory, InventoryDto.class);
        inventoryDto.setPallets(ClassMapperUtil.mapListIgnoreLazyCollection(inventory.getPallets(), PalletDto.class));
        return (inventoryDto);
    }
    public TransferDto findTransfer(Integer id) {
        Transfer transfer = transferRepo.findById(id).orElseThrow(() -> new RuntimeException(Errors.NOT_FOUND.toString()));
        TransferDto transferDto = mapClassIgnoreLazy(transfer, TransferDto.class);
        transferDto.setPallets(ClassMapperUtil.mapListIgnoreLazyCollection(transfer.getPallets(), PalletDto.class));
        return (transferDto);
    }

    public Pallet getPallet(Long barcode) {
        return (palletRepo.findById(barcode).orElseThrow(() -> new RuntimeException(Errors.NOT_FOUND.toString())));
    }
    public Inventory getInventory(Long id) {
        return (inventoryRepo.findById(id).orElseThrow(() -> new RuntimeException(Errors.NOT_FOUND.toString())));
    }
    public Transfer getTransfer(Integer id) {
        return (transferRepo.findById(id).orElseThrow(() -> new RuntimeException(Errors.NOT_FOUND.toString())));
    }
}
