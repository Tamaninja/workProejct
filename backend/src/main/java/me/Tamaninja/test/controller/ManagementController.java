package me.Tamaninja.test.controller;


import me.Tamaninja.test.service.InventoryManagementService;
import me.Tamaninja.test.service.LookupService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(maxAge = 3600)
@RequestMapping("/pallet")
public class ManagementController {

    private final InventoryManagementService inventoryManagementService;
    private final LookupService lookupService;
    public ManagementController(InventoryManagementService inventoryManagementService, LookupService lookupService) {
        this.inventoryManagementService = inventoryManagementService;
        this.lookupService = lookupService;
    }

    @GetMapping("/test")
    public String test(){
        return ("test1|test2|test3|test4|test5");
    }

//    @PostMapping("/create")
//    public ResponseEntity<PalletDto> createPallet(
//            @RequestParam(required = false, value="barcode") Long barcode,
//            @RequestParam(required = false, value="containerId") String containerId,
//            @RequestParam(required = false, value="containerTypeId") String containerTypeId,
//            @RequestParam(required = false, value="palletContent") String palletContent,
//            @RequestParam(required = false, value="amount") Integer amount,
//            @RequestParam("origin") String locationName,
//            @RequestParam("weight") float weight )
//    {
//        Inventory inventory = lookupService.getInventoryByName(locationName);
//        Pallet pallet = inventoryManagementService.savePallet(barcode, containerTypeId, containerId, amount, palletContent, weight, inventory);
//
//        ResponseEntity<PalletDto> response = new ResponseEntity<PalletDto>(lookupService.mapPallet(pallet, false), HttpStatus.I_AM_A_TEAPOT);
//        return (response);
//    }


}
