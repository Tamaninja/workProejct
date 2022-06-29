package me.Tamaninja.test.service;

import me.Tamaninja.test.dto.PalletDto;
import me.Tamaninja.test.entity.Content;
import me.Tamaninja.test.entity.Pallet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.DateFormatConverter;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class ImportExportService {


    public void export(List<Pallet> pallets, String excelFilePath) throws IOException {
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet();
        CellStyle cellStyleTimestamp = workbook.createCellStyle();
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyleTimestamp.setDataFormat((short)0x16);
        cellStyleTimestamp.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        List<CellStyle> cellStyles = new ArrayList<>();
        cellStyles.add(cellStyle);
        cellStyles.add(cellStyleTimestamp);


        int rowCount = 0;

        for (Pallet pallet:pallets) {
            for (Content content:pallet.getContents()) {
                Row row = sheet.createRow(rowCount++);
                sheet.autoSizeColumn(2);
                writePallet(row, pallet, content, cellStyles);
            }
        }

        try (FileOutputStream outputStream = new FileOutputStream(excelFilePath)) {
            workbook.write(outputStream);
        }


    }
    public void palletExport(List<Pallet> pallets, String excelFilePath) throws IOException {



    }
    private void writeCell(Integer value, Cell cell, CellStyle cellStyle) {
        cell.setCellValue(value);
        cell.setCellStyle(cellStyle);
    }
    private void writeCell(String value, Cell cell, CellStyle cellStyle) {
        cell.setCellValue(value);
        cell.setCellStyle(cellStyle);
    }

    private void writeCell(double value, Cell cell, CellStyle cellStyle) {
        cell.setCellValue(value);
        cell.setCellStyle(cellStyle);
    }
    private void writeCell(Timestamp timestamp, Cell cell, CellStyle cellStyle) {
        cell.setCellValue(timestamp);
        cell.setCellStyle(cellStyle);
    }

    private int writePallet(Row row, Pallet pallet, Content content, List<CellStyle> cellStyle) {
        int cellCount = 0;
        writeCell(pallet.getBarcode(), row.createCell(cellCount++), cellStyle.get(0));
        writeCell(content.getPalletContent().getName(), row.createCell(cellCount++), cellStyle.get(0));
        writeCell(content.getContainerAmount(), row.createCell(cellCount++), cellStyle.get(0));
        writeCell(content.getPalletContainer().getName(), row.createCell(cellCount++), cellStyle.get(0));
        writeCell(content.getWeightGross(), row.createCell(cellCount++), cellStyle.get(0));
        writeCell(content.getWeightNet(), row.createCell(cellCount++), cellStyle.get(0));
        writeCell(pallet.getPalletType().getName(), row.createCell(cellCount++), cellStyle.get(0));
        writeCell(pallet.getLocation().getName(), row.createCell(cellCount++), cellStyle.get(0));
        writeCell(content.getTimestamp(), row.createCell(cellCount), cellStyle.get(1));
        return (cellCount);
    }
}
