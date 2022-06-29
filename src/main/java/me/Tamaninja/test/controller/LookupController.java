package me.Tamaninja.test.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import me.Tamaninja.test.dto.*;
import me.Tamaninja.test.service.ImportExportService;
import me.Tamaninja.test.service.LookupService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
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
    public String findInventory(@RequestParam(value="identifier") String identifier, HttpServletResponse response, Model model) throws IOException {
        InventoryDto inventoryDto = lookupService.mapInventory(lookupService.getInventoryByName(identifier), true);
        model.addAttribute("exportLink","/inventory/"+identifier+"/export");
        model.addAttribute("body", new ObjectMapper().writeValueAsString(inventoryDto));
        return ("inventory");
    }

    @GetMapping("/transfer")
    public ResponseEntity<TransferDto> findTransfer(@RequestParam(value="id") Long id) {
        TransferDto transferDto = lookupService.mapTransfer(lookupService.getTransfer(id), true);
        ResponseEntity<TransferDto> response = new ResponseEntity<TransferDto>(transferDto, HttpStatus.OK);
        return (response);
    }

}
