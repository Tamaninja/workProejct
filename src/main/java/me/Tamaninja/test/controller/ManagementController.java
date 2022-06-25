package me.Tamaninja.test.controller;


import me.Tamaninja.test.service.InventoryManagementService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pallet")
public class ManagementController {

    private final InventoryManagementService inventoryManagementService;
    public ManagementController(InventoryManagementService inventoryManagementService) {
        this.inventoryManagementService = inventoryManagementService;
    }

    @PostMapping("/create")
    public String test2(
            @RequestParam(required = false, value="barcode") Long barcode,
            @RequestParam("weight") float weight,
            @RequestParam("containerId") Integer containerId,
            @RequestParam("containerTypeId") Integer containerTypeId,
            @RequestParam("palletContent") Integer palletContent,
            @RequestParam("location") Integer locationId,
            @RequestParam(required = false, value="amount") Short amount) {
        return (inventoryManagementService.savePallet(barcode, containerTypeId, containerId, amount, palletContent, weight, locationId).toString());
    }
}
