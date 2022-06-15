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

    @GetMapping("/test")
    public String test() {
        return ("test");
    }

    @PostMapping("/create")
    public String test2(
            @RequestParam(required = false, value="barcode") Long barcode,
            @RequestParam("weight") double weight,
            @RequestParam("containerId") Long containerId,
            @RequestParam("containerTypeId") Long containerTypeId,
            @RequestParam("palletContent") String palletContent,
            @RequestParam("location") int locationId,
            @RequestParam(required = false, value="amount") int amount) {
        return (inventoryManagementService.savePallet(barcode,weight,containerId,containerTypeId,palletContent,locationId,amount).toString());
    }
}
