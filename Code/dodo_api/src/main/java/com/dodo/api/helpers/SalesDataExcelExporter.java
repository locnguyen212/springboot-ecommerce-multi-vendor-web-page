package com.dodo.api.helpers;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.dodo.api.modelview.OrderView;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

public class SalesDataExcelExporter {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<OrderView> orderViews;
    
    public SalesDataExcelExporter(List<OrderView> orderViews) {
		this.orderViews = orderViews;
		workbook = new XSSFWorkbook();
	}
    
    private void writeHeaderLine() {
        sheet = workbook.createSheet("Salesdata");
         
        Row row = sheet.createRow(0);
         
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);
         
        createCell(row, 0, "Shop ID", style);      
        createCell(row, 1, "Shop Name", style);       
        createCell(row, 2, "Total Sales", style);    
        createCell(row, 3, "Month", style);
        createCell(row, 4, "Year", style);      
    }
    
    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        }else if (value instanceof Double) {
            cell.setCellValue((Double) value);
        }else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }
    
    private void writeDataLines() {
        int rowCount = 1;
 
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);
                 
        for (OrderView e : orderViews) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
             
            createCell(row, columnCount++, e.getShopowner().getOwnerId(), style);
            createCell(row, columnCount++, e.getShopowner().getShopName(), style);
            createCell(row, columnCount++, e.getTotal(), style);
            createCell(row, columnCount++, e.getMonth(), style);
            createCell(row, columnCount++, e.getYear(), style);
             
        }
    }
     
    public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();
         
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
         
        outputStream.close();
         
    }
}
