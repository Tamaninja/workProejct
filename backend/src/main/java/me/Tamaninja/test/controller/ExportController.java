package me.Tamaninja.test.controller;

import me.Tamaninja.test.dto.PalletDto;
import me.Tamaninja.test.entity.Pallet;
import me.Tamaninja.test.service.ImportExportService;
import me.Tamaninja.test.service.InventoryManagementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@CrossOrigin(maxAge = 3600)
public class ExportController {
    private final ImportExportService importExportService;

    private final InventoryManagementService inventoryManagementService;
    public ExportController(ImportExportService importExportService, InventoryManagementService inventoryManagementService) {
        this.importExportService = importExportService;
        this.inventoryManagementService = inventoryManagementService;
    }

    @GetMapping("/inventory/{identifier}/export")
    public void inventory(@PathVariable String identifier, HttpServletResponse response) throws IOException {

        importExportService.inventoryExport(identifier, response);
    }

    @PostMapping ("/create/pallet")
    public ResponseEntity<PalletDto> createPallet(@RequestBody PalletDto palletDto) {
        return (inventoryManagementService.createPallet(palletDto));
    }
}
