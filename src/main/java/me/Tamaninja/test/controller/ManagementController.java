package me.Tamaninja.test.controller;


import me.Tamaninja.test.dto.PalletDto;
import me.Tamaninja.test.entity.Inventory;
import me.Tamaninja.test.entity.Pallet;
import me.Tamaninja.test.service.InventoryManagementService;
import me.Tamaninja.test.service.LookupService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pallet")
public class ManagementController {

    private final InventoryManagementService inventoryManagementService;
    private final LookupService lookupService;
    public ManagementController(InventoryManagementService inventoryManagementService, LookupService lookupService) {
        this.inventoryManagementService = inventoryManagementService;
        this.lookupService = lookupService;
    }

    @PostMapping("/create")
    public ResponseEntity<PalletDto> createPallet(
            @RequestParam(required = false, value="barcode") Long barcode,
            @RequestParam(required = false, value="containerId") Integer containerId,
            @RequestParam(required = false, value="containerTypeId") Integer containerTypeId,
            @RequestParam(required = false, value="palletContent") Integer palletContent,
            @RequestParam(required = false, value="amount") Integer amount,
            @RequestParam("origin") String locationName,
            @RequestParam("weight") float weight )
    {
        Inventory inventory = lookupService.getInventoryByName(locationName);
        Pallet pallet = inventoryManagementService.savePallet(barcode, containerTypeId, containerId, amount, palletContent, weight, inventory);

        ResponseEntity<PalletDto> response = new ResponseEntity<PalletDto>(lookupService.mapPallet(pallet, false), HttpStatus.I_AM_A_TEAPOT);
        return (response);
    }


}
