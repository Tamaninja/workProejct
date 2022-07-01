package me.Tamaninja.test.controller;


import me.Tamaninja.test.service.InventoryManagementService;
import me.Tamaninja.test.service.LookupService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(maxAge = 3600)
@RequestMapping("/")
public class ManagementController {
    private final InventoryManagementService inventoryManagementService;
    private final LookupService lookupService;
    public ManagementController(InventoryManagementService inventoryManagementService, LookupService lookupService) {
        this.inventoryManagementService = inventoryManagementService;
        this.lookupService = lookupService;
    }

    @GetMapping("/test")
    public String test(){
        return ("pallet");
    }




}
