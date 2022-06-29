package me.Tamaninja.test.service;


import me.Tamaninja.test.enums.Errors;
import me.Tamaninja.test.repository.InventoryRepository;
import me.Tamaninja.test.util.ImportExport;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;




@Service
public class ImportExportService {
    private final InventoryRepository inventoryRepository;

    public ImportExportService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }
    public void inventoryExport(String identifier, HttpServletResponse response) throws IOException {
        Long id = inventoryRepository.findByName(identifier).orElseThrow(() -> new RuntimeException(Errors.NOT_FOUND.toString())).getId();
        List<Object[]> query = inventoryRepository.getAllPallets(id);


        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=inventory_" + id + ".xlsx";
        response.setHeader(headerKey, headerValue);
        ImportExport importExport = new ImportExport();
        importExport.fillSheet(query);
        importExport.export(response.getOutputStream());
//        importExport.exportPallets(response, pa);
    }
}
