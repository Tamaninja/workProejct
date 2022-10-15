package me.Tamaninja.test.service;

import me.Tamaninja.test.dto.InventoryDto;
import me.Tamaninja.test.dto.PalletDto;
import me.Tamaninja.test.dto.TransferDto;
import me.Tamaninja.test.entity.*;
import me.Tamaninja.test.enums.Errors;
import me.Tamaninja.test.frontend.FrontendField;
import me.Tamaninja.test.frontend.FrontendForm;
import me.Tamaninja.test.repository.*;
import me.Tamaninja.test.util.ClassMapperUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static me.Tamaninja.test.util.ClassMapperUtil.*;
@Service
public class LookupService {
    private final PalletRepository palletRepository;
    private final InventoryRepository inventoryRepository;
    private final TransferRepository transferRepository;
    private final PalletContainerRepository palletContainerRepository;
    private final PalletContentRepository palletContentRepository;


    public LookupService(PalletRepository palletRepository, InventoryRepository inventoryRepository, TransferRepository transferRepository, PalletContainerRepository palletContainerRepository, PalletContentRepository palletContentRepository) {
        this.palletRepository = palletRepository;
        this.inventoryRepository = inventoryRepository;
        this.transferRepository = transferRepository;
        this.palletContainerRepository = palletContainerRepository;
        this.palletContentRepository = palletContentRepository;
    }

    public FrontendForm getPalletForm() {
        FrontendForm form = new FrontendForm("http://localhost:8080/create/pallet");
        List<PalletContent> palletContents = palletContentRepository.getAllPalletContents();
        List<PalletContainer> palletContainers = palletContainerRepository.getAllPalletContainers();
        List<PalletContainer> palletTypes = palletContainerRepository.getAllPalletTypes();


        form.addField(new FrontendField<>("palletContent", palletContents.get(0), palletContents));
        form.addField(new FrontendField<>("palletContainer",palletContainers.get(0), palletContainers));
        form.addField(new FrontendField<>("palletType",palletTypes.get(0), palletTypes));
        form.addField(new FrontendField<>("containerAmount", palletContainers.get(0).getDefaultAmount()));
        form.addField(new FrontendField<>("weightGross", 0.0));


        return (form);
    }

    public PalletDto mapPallet(Pallet pallet, boolean withTransfers) {
        PalletDto palletDto = mapClassIgnoreLazy(pallet, PalletDto.class);
        if (withTransfers) {
            palletDto.setTransfers(ClassMapperUtil.mapListIgnoreLazyCollection(pallet.getTransfers(), TransferDto.class));
        }
        return (palletDto);
    }

    public InventoryDto mapInventory(Inventory inventory, boolean withPallets) {
        InventoryDto inventoryDto = mapClassIgnoreLazy(inventory, InventoryDto.class);
        if (inventory.getChildren().size() > 0) {
            List<InventoryDto> children = new ArrayList<>();
            for (Inventory child:inventory.getChildren()) {
                InventoryDto childDTO = mapInventory(child, withPallets);
                childDTO.setParent(null);
                children.add(childDTO);
            }
            inventoryDto.setChildren(children);
        }

        if (!withPallets) return (inventoryDto);

        List<PalletDto> palletDtos = new ArrayList<>();
        for (Pallet pallet:inventory.getPallets()) {
            palletDtos.add(mapPallet(pallet, false));
        }
        inventoryDto.setPallets(palletDtos);

        return (inventoryDto);
    }

    public TransferDto mapTransfer(Transfer transfer, boolean withPallets) {
        TransferDto transferDto = mapClassIgnoreLazy(transfer, TransferDto.class);
        if (!withPallets) return (transferDto);

        List<PalletDto> palletDtos = new ArrayList<>();
        for (Pallet pallet:transfer.getPallets()) {
            palletDtos.add(mapPallet(pallet, false));
        }
        transferDto.setPallets(palletDtos);

        return (transferDto);
    }

    public Inventory getInventory(Long id) {
        return (inventoryRepository.findById(id).orElseThrow(() -> new RuntimeException(Errors.NOT_FOUND.toString())));
    }
    public Pallet getPallet(Long barcode) {
        return (palletRepository.findById(barcode).orElseThrow(() -> new RuntimeException(Errors.NOT_FOUND.toString())));
    }
    public Transfer getTransfer(Long id) {
        return (transferRepository.findById(id).orElseThrow(() -> new RuntimeException(Errors.NOT_FOUND.toString())));
    }
    public Inventory getInventoryByName(String name) {
        return (inventoryRepository.findByName(name).orElse(null));
    }

}
