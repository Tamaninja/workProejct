package me.Tamaninja.test.controller;


import me.Tamaninja.test.dto.*;
import me.Tamaninja.test.entity.Inventory;
import me.Tamaninja.test.service.ImportExportService;
import me.Tamaninja.test.service.LookupService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/lookup")
public class LookupController {

    private final LookupService lookupService;
    private final ImportExportService importExportService;
    public LookupController(LookupService lookupService, ImportExportService importExportService) {
        this.importExportService = importExportService;
        this.lookupService = lookupService;
    }

    @GetMapping("/pallet")
    public ResponseEntity<PalletDto> findPallet(@RequestParam(value="barcode") Long barcode) {
        PalletDto palletDto = lookupService.mapPallet(lookupService.getPallet(barcode), true);
        ResponseEntity<PalletDto> response = new ResponseEntity<PalletDto>(palletDto, HttpStatus.OK);
        return (response);
    }
    
    @GetMapping("/inventory")
    public ResponseEntity<InventoryDto> findInventory(@RequestParam(value="identifier") String identifier) throws IOException {
        Inventory inventory = lookupService.getInventoryByName(identifier);
        String fileLocation = new File("src\\main\\resources\\exports\\inventory").getAbsolutePath() + "\\" + "150.xls";
        InventoryDto inventoryDto = lookupService.mapInventory(inventory, true);
        importExportService.export(inventory.getPallets(), fileLocation);
        ResponseEntity<InventoryDto> response = new ResponseEntity<InventoryDto>(inventoryDto, HttpStatus.OK);
        return (response);
    }

    @GetMapping("/transfer")
    public ResponseEntity<TransferDto> findTransfer(@RequestParam(value="id") Long id) {
        TransferDto transferDto = lookupService.mapTransfer(lookupService.getTransfer(id), true);
        ResponseEntity<TransferDto> response = new ResponseEntity<TransferDto>(transferDto, HttpStatus.OK);
        return (response);
    }
}
