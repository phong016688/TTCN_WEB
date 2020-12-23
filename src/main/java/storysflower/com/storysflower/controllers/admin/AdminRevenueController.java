package storysflower.com.storysflower.controllers.admin;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import storysflower.com.storysflower.constants.UrlConstants;
import storysflower.com.storysflower.dto.ProductRevenueDTO;
import storysflower.com.storysflower.dto.RevenueDTO;
import storysflower.com.storysflower.services.OccasionService;
import storysflower.com.storysflower.services.RevenueService;
import storysflower.com.storysflower.utils.AuthUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(UrlConstants.URL_ADMIN)
public class AdminRevenueController {

    public static List<Integer> list = null;
    @Autowired
    RevenueService revenueService;

    private static final String OCCASIONS = "occasions";

    @Autowired
    private OccasionService occasionService;
    @ModelAttribute
    public void leftbar(Model model){
        model.addAttribute(OCCASIONS, occasionService.findAllOccasion());
    }

    @GetMapping({UrlConstants.URL_ADMIN_REVENUE_INDEX})
    public String edit(Model model, @PathVariable(value = "id", required = false) Long id, HttpServletRequest request) throws Exception {
        if(!AuthUtil.isLogin(request)) {
            return "redirect:" + UrlConstants.URL_ADMIN + UrlConstants.URL_LOGIN;
        }
        List<RevenueDTO> listRevenueDTOS = revenueService.findAllRevenue();
        model.addAttribute("listRevenueDTOS", listRevenueDTOS);
        return "admin/revenue/index";
    }

    @GetMapping({UrlConstants.URL_ADMIN_REVENUE_DETAIL})
    public String detail(Model model, @PathVariable(value = "date", required = false) String date, HttpServletRequest request) throws Exception {
        if(!AuthUtil.isLogin(request)) {
            return "redirect:" + UrlConstants.URL_ADMIN + UrlConstants.URL_LOGIN;
        }
        RevenueDTO revenueDTO = revenueService.findRevenueDTOByDate(date);
        if (revenueDTO == null) return "";
        model.addAttribute("revenueDTO", revenueDTO);
        return "admin/revenue/detail";
    }

    @GetMapping({UrlConstants.URL_ADMIN_REVENUE_SEARCH})
    public String search() {
        return "redirect:" + UrlConstants.URL_ADMIN + UrlConstants.URL_ADMIN_REVENUE_INDEX;
    }

    @PostMapping({UrlConstants.URL_ADMIN_REVENUE_SEARCH})
    public String edit(HttpServletResponse resp, Model model, @RequestParam(value = "bdaymonth") String bdaymonth) throws IOException {
        if ("".equals(bdaymonth)) return "redirect:" + UrlConstants.URL_ADMIN + UrlConstants.URL_ADMIN_REVENUE_INDEX;
        String month = bdaymonth.split("-")[1];
        String year = bdaymonth.split("-")[0];
        RevenueDTO revenueDTO = revenueService.findRevenueDTOByDate(month + "-" + year);

        if (revenueDTO == null) {
            revenueDTO = new RevenueDTO();
            revenueDTO.setDate(month + "-" + year);
            revenueDTO.setTotalMoney(0.0);
        }
        model.addAttribute("revenueDTO", revenueDTO);
        return "admin/revenue/detail";
    }

    @PostMapping({UrlConstants.URL_ADMIN_REVENUE_DOWNLOAD})
    public void exportExcel(HttpServletResponse resp,
                            @RequestParam(value = "bdaymonth") String bdaymonth)
            throws IOException {
        RevenueDTO revenueDTO = null;
        List<ProductRevenueDTO> productRevenueDTOList = null;
        if (bdaymonth != null) {
            String month = bdaymonth.split("-")[0];
            String year = bdaymonth.split("-")[1];
            revenueDTO = revenueService.findRevenueDTOByDate(month + "-" + year);
        }
        if (revenueDTO != null) {
            productRevenueDTOList = revenueDTO.getListCart();
        }
        if (productRevenueDTOList != null) {
            Workbook workbook = new HSSFWorkbook();

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
            FileOutputStream fileOut = new FileOutputStream("revenue.xlsx");
            workbook.write(fileOut);
            fileOut.close();
            workbook.close();
            resp.setContentType("application/vnd.ms-excel");

            OutputStream outputStream = resp.getOutputStream();
            if (list == null) {
                File file = new File("revenue.xlsx");
                BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
                int i = 0;
                if (list == null) {
                    list = new ArrayList<>();
                    while ((i = bufferedInputStream.read()) != -1) {
                        list.add(i);
                    }
                    bufferedInputStream.close();
                }
            }
            for (int i = 0; i < list.size(); i++) {
                outputStream.write(list.get(i));
            }
            outputStream.close();
        }
    }
}
