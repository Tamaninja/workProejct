package me.Tamaninja.test.controller;

import me.Tamaninja.test.entity.Inventory;
import me.Tamaninja.test.service.ImportExportService;
import me.Tamaninja.test.service.LookupService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class ExportController {
    private final ImportExportService importExportService;
    private final LookupService lookupService;

    public ExportController(ImportExportService importExportService, LookupService lookupService) {
        this.importExportService = importExportService;
        this.lookupService = lookupService;
    }

    @GetMapping("/inventory/{identifier}/export")
    public void inventory(@PathVariable String identifier, HttpServletResponse response) throws IOException {

        importExportService.inventoryExport(identifier, response);
    }

    @PostMapping("/create/pallet")
    public Inventory createPallet(
            @RequestParam(required = false, value="barcode") Long barcode,
            @RequestParam(required = false, value="containerId") String containerId,
            @RequestParam(required = false, value="containerTypeId") String containerTypeId,
            @RequestParam(required = false, value="palletContent") String palletContent,
            @RequestParam(required = false, value="amount") Integer amount,
            @RequestParam("origin") String locationName,
            @RequestParam("weight") double weight )
    {
        Inventory inventory = lookupService.getInventoryByName(locationName);
        // TODO: 01/07/2022 fix idk
//        Pallet pallet = inventoryManagementService.savePallet(barcode, containerTypeId, containerId, amount, palletContent, weight, inventory);

        return (inventory);
    }
}
