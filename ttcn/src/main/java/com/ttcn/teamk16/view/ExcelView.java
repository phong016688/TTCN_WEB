package storysflower.com.storysflower.view;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.springframework.web.servlet.view.document.AbstractXlsView;
import storysflower.com.storysflower.dto.ProductRevenueDTO;
import storysflower.com.storysflower.dto.RevenueDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExcelView extends AbstractXlsView {


    @Override
    protected void buildExcelDocument(Map<String, Object> model,
                                      Workbook workbook,
                                      HttpServletRequest request,
                                      HttpServletResponse response)
          {

        // change the file name
        response.setHeader("Content-Disposition", "attachment; filename=\"my-xls-file.xls\"");

        RevenueDTO revenueDTO = (RevenueDTO) model.get("revenueDTO");

        List<ProductRevenueDTO> productRevenueDTOList = revenueDTO.getListCart();


/*        List<ProductRevenueDTO> productRevenueDTOS = new ArrayList<>();
        productRevenueDTOS.add(new ProductRevenueDTO(0, "teo", 1, 10.5));

        productRevenueDTOS.add(new ProductRevenueDTO(1, "teo", 1, 10.5));

        productRevenueDTOS.add(new ProductRevenueDTO(2, "teo", 1, 10.5));
        productRevenueDTOS.add(new ProductRevenueDTO(3, "teo", 1, 10.5));*/


        // create excel xls sheet
        Sheet sheet = workbook.createSheet("revenue for month");
        sheet.setDefaultColumnWidth(30);

        // create style for header cells
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontName("Arial");
        style.setFillForegroundColor(HSSFColor.BLUE.index);
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        font.setBold(true);
        font.setColor(HSSFColor.WHITE.index);
        style.setFont(font);

        // create header row
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("STT");
        header.getCell(0).setCellStyle(style);
        header.createCell(1).setCellValue("Name Product");
        header.getCell(1).setCellStyle(style);
        header.createCell(2).setCellValue("Quantity");
        header.getCell(2).setCellStyle(style);
        header.createCell(3).setCellValue("Total Money");
        header.getCell(3).setCellStyle(style);
        int rowCount = 1;

        for (ProductRevenueDTO productRevenueDTO : productRevenueDTOList) {
            Row userRow = sheet.createRow(rowCount++);
            userRow.createCell(0).setCellValue(productRevenueDTO.getId());
            userRow.createCell(1).setCellValue(productRevenueDTO.getName());
            userRow.createCell(2).setCellValue(productRevenueDTO.getQuatity());
            userRow.createCell(3).setCellValue(productRevenueDTO.getPrice());
        }
    }
}