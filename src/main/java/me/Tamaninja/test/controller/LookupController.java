package me.Tamaninja.test.controller;


import me.Tamaninja.test.dto.*;
import me.Tamaninja.test.service.LookupService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lookup")
public class LookupController {

    private final LookupService lookupService;
    public LookupController(LookupService lookupService) {
        this.lookupService = lookupService;
    }

    @GetMapping("/pallet")
    public PalletDto findPallet(@RequestParam(value="barcode") Long barcode) {
        return (lookupService.findPallet(barcode));
    }

    @GetMapping("/inventory")
    public ResponseEntity<InventoryDto> findInventory(@RequestParam(value="name") Long name) {
        InventoryDto inventoryDto = lookupService.findInventory(name);
        ResponseEntity<InventoryDto> response = new ResponseEntity<InventoryDto>(inventoryDto, HttpStatus.OK);
        return (response);
    }

    @GetMapping("/transfer")
    public ResponseEntity<TransferDto> findTransfer(@RequestParam(value="id") Long id) {
        TransferDto transferDto = lookupService.findTransfer(id);
        ResponseEntity<TransferDto> response = new ResponseEntity<TransferDto>(transferDto, HttpStatus.OK);
        return (response);
    }
}
