package me.Tamaninja.test.controller;


import me.Tamaninja.test.dto.PalletDto;
import me.Tamaninja.test.entity.Pool;
import me.Tamaninja.test.service.InventoryManagementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pallet")
public class ManagementController {

    private final InventoryManagementService inventoryManagementService;
    public ManagementController(InventoryManagementService inventoryManagementService) {
        this.inventoryManagementService = inventoryManagementService;
    }

    @PostMapping("/create")
    public ResponseEntity<PalletDto> createPallet(
            @RequestParam(required = false, value="barcode") Long barcode,
            @RequestParam(required = false, value="amount") Short amount,
            @RequestParam(required = false, value = "containerId") Integer containerId,
            @RequestParam(required = false, value = "containerTypeId") Integer containerTypeId,
            @RequestParam(required = false, value = "palletContent") Integer palletContent,
            @RequestParam("location") String inventoryName,
            @RequestParam("weight") float weight
    ) {
        PalletDto palletDto = inventoryManagementService.savePallet(barcode, containerTypeId, containerId, amount, palletContent, weight, inventoryName, new Pool());
        ResponseEntity<PalletDto> response = new ResponseEntity<PalletDto>(palletDto, HttpStatus.OK);
        return (response);
    }
}
