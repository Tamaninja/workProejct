package me.Tamaninja.test.util;
import me.Tamaninja.test.entity.Inventory;
import me.Tamaninja.test.entity.Pallet;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;


public class ImportExport {
    private CellStyle defaultCellStyle;
    private CellStyle timestampCellStyle;
    private Workbook workbook;
    private Sheet sheet;


    public ImportExport(Object object, HttpServletResponse response) throws IOException {
        this.workbook = new XSSFWorkbook();
        this.createCellStyles();
        this.sheet = workbook.createSheet();
        this.export(response, object);
    }
    public void export(HttpServletResponse response, Object object) throws IOException {
        if (object instanceof Inventory) {
            inventoryExport((Inventory)object);
        }

        for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {
            sheet.autoSizeColumn(i);
        }
        ServletOutputStream outputStream = response.getOutputStream();
        sheet.getWorkbook().write(outputStream);
        sheet.getWorkbook().close();
        outputStream.close();
    }

    private void inventoryExport(Inventory inventory) {
        int rowCount = 0;
        for (Pallet pallet:inventory.getPallets()) {
            Row row = sheet.createRow(rowCount++);
            this.writePallet(row, pallet);
        }
    }

    private void createCellStyles() {
        this.defaultCellStyle = this.workbook.createCellStyle();
        this.defaultCellStyle.setAlignment(HorizontalAlignment.CENTER);

        this.timestampCellStyle = this.workbook.createCellStyle();
        this.timestampCellStyle.setAlignment(HorizontalAlignment.CENTER);
        this.timestampCellStyle.setDataFormat((short)0x16);
    }
    private void writeCell(Object value, Cell cell) {
        cell.setCellStyle(this.defaultCellStyle);
        if (value instanceof Double) {
            cell.setCellValue((double)value);
        } else if (value instanceof Integer) {
            cell.setCellValue((int)value);
        } else if (value instanceof Timestamp) {
            cell.setCellValue((Timestamp)value);
            cell.setCellStyle(this.timestampCellStyle);
        } else {
            cell.setCellValue(value.toString());
        }
    }
    public void writeRow(Row row, List<Object> values) {
        int cellCount = 0;
        for (Object value:values) {
            this.writeCell(value, row.createCell(cellCount++));
        }
    }
    private void writePallet(Row row, Pallet pallet) {
        int cellCount = 0;
        writeCell(pallet.getBarcode(), row.createCell(cellCount++));
        writeCell(pallet.getPalletContent().getName(), row.createCell(cellCount++));
        writeCell(pallet.getContainerAmount(), row.createCell(cellCount++));
        writeCell(pallet.getPalletContainer().getName(), row.createCell(cellCount++));
        writeCell(pallet.getWeightGross(), row.createCell(cellCount++));
        writeCell(pallet.getWeightNet(), row.createCell(cellCount++));
        writeCell(pallet.getPalletType().getName(), row.createCell(cellCount++));
        writeCell(pallet.getLocation().getName(), row.createCell(cellCount++));
        writeCell(pallet.getTimestamp(), row.createCell(cellCount));
    }
}
