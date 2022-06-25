package me.Tamaninja.test.controller;


import me.Tamaninja.test.dto.PalletDto;
import me.Tamaninja.test.entity.Pallet;
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
    public PalletDto findPallet(@RequestParam(required = false, value="barcode") Long barcode) {
        PalletDto pallet = lookupService.findPallet(barcode);
        return (pallet);
    }
}
