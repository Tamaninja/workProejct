package me.Tamaninja.test.controller;

import me.Tamaninja.test.service.ImportExportService;
import me.Tamaninja.test.service.LookupService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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
        importExportService.inventoryExport(lookupService.getInventoryByName(identifier), response);
    }
}
