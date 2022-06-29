package me.Tamaninja.test.service;

import me.Tamaninja.test.entity.Inventory;
import me.Tamaninja.test.util.ImportExport;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;


@Service
public class ImportExportService {

    public void inventoryExport(Inventory inventory, HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        String fileLocation = new File("src\\main\\resources\\exports").getAbsolutePath() + "\\" + "export.xlsx";

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=inventory_" + inventory.getName() + ".xlsx";
        response.setHeader(headerKey, headerValue);
        ImportExport importExport = new ImportExport(inventory, response);
    }
}
