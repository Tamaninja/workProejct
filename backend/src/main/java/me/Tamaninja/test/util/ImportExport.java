package me.Tamaninja.test.util;
import me.Tamaninja.test.entity.Pallet;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;


public class ImportExport {
    private CellStyle defaultCellStyle;
    private CellStyle timestampCellStyle;
    private final Workbook workbook;
    private final Sheet sheet;



    public ImportExport() throws IOException {
        this.workbook = new XSSFWorkbook();
        this.createCellStyles();
        this.sheet = workbook.createSheet();
    }
    private void alignColumns() {
        for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {
            sheet.autoSizeColumn(i);
        }
    }
    public void export(ServletOutputStream outputStream) throws IOException {
        this.alignColumns();
        sheet.getWorkbook().write(outputStream);
        sheet.getWorkbook().close();
        outputStream.close();
    }
    private void export(FileOutputStream fileOutputStream) throws IOException {
        this.alignColumns();
        workbook.write(fileOutputStream);
        workbook.close();
        fileOutputStream.close();
    }
    public void fillSheet(List<Object[]> data) {
        for (int i = 0; i < data.size(); i++) {
            Row excelRow = sheet.createRow(i);
            Object[] dbRow = data.get(i);
            fillRow(excelRow, dbRow);
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
    private void fillRow(final Row row, final Object[] dbRow) {
        for (int i = 0; i < dbRow.length; i++) {
            writeCell(dbRow[i], row.createCell(i));
        }
    }
    private void writePallet(Row row, Pallet pallet) {
        int cellCount = 0;
        writeCell(pallet.getBarcode(), row.createCell(cellCount++));
        writeCell(pallet.getPalletContent().getIdentifier(), row.createCell(cellCount++));
        writeCell(pallet.getContainerAmount(), row.createCell(cellCount++));
        writeCell(pallet.getPalletContainer().getIdentifier(), row.createCell(cellCount++));
        writeCell(pallet.getWeightGross(), row.createCell(cellCount++));
        writeCell(pallet.getWeightNet(), row.createCell(cellCount++));
        writeCell(pallet.getPalletType().getIdentifier(), row.createCell(cellCount++));
        writeCell(pallet.getLocation().getName(), row.createCell(cellCount++));
        writeCell(pallet.getTimestamp(), row.createCell(cellCount));
    }
}
