package storysflower.com.storysflower.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import storysflower.com.storysflower.constants.CommonConstants;
import storysflower.com.storysflower.constants.UrlConstants;
import storysflower.com.storysflower.dto.CustomerDTO;
import storysflower.com.storysflower.dto.ProductCustomerDTO;
import storysflower.com.storysflower.services.CustomerService;
import storysflower.com.storysflower.services.OccasionService;
import storysflower.com.storysflower.services.ProductService;
import storysflower.com.storysflower.utils.AuthUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Controller
@RequestMapping(UrlConstants.URL_ADMIN)
public class AdminCustomerController {

    @Autowired
    CustomerService customerService;

    @Autowired
    ProductService productService;

    private static final String OCCASIONS = "occasions";

    @Autowired
    private OccasionService occasionService;
    @ModelAttribute
    public void leftbar(Model model){
        model.addAttribute(OCCASIONS, occasionService.findAllOccasion());
    }

    @GetMapping({UrlConstants.URL_ADMIN_CUSTOMER_INDEX})
    public String index(Model model, HttpServletRequest request, RedirectAttributes redirect) throws Exception {
        if(!AuthUtil.isLogin(request)) {
            return "redirect:" + UrlConstants.URL_ADMIN + UrlConstants.URL_LOGIN;
        }
        request.getSession().setAttribute("customer", null);
        if (model.asMap().get("success") != null)
            redirect.addFlashAttribute("success", model.asMap().get("success").toString());
        return "redirect:" + UrlConstants.URL_ADMIN + UrlConstants.URL_ADMIN_CUSTOMER_INDEX + "/1";

    }

    @GetMapping({UrlConstants.URL_ADMIN_CUSTOMER_INDEX_PAGINATION})
    public String shoListCustomerPage(HttpServletRequest request, @PathVariable int page, Model model) throws Exception {
        if(!AuthUtil.isLogin(request)) {
            return "redirect:" + UrlConstants.URL_ADMIN + UrlConstants.URL_LOGIN;
        }
        PagedListHolder<?> pages = (PagedListHolder<?>) request.getSession().getAttribute("customer");
        List<CustomerDTO> list = (List<CustomerDTO>) customerService.findAll();
        if (pages == null) {
            pages = new PagedListHolder<>(list);
            pages.setPageSize(CommonConstants.DEFAULT_PAGING_CUSTOMER_SIZE);
        } else {
            final int goToPage = page - 1;
            if (goToPage <= pages.getPageCount() && goToPage >= 0) {
                pages.setPage(goToPage);
            }
        }
        request.getSession().setAttribute("customer", pages);
        int current = pages.getPage() + 1;
        int begin = Math.max(1, current - list.size());
        int end = Math.min(begin + 5, pages.getPageCount());
        int totalPageCount = pages.getPageCount();
        String baseUrl = UrlConstants.URL_ADMIN + UrlConstants.URL_ADMIN_CUSTOMER_INDEX + "/";

        model.addAttribute("beginIndex", begin);
        model.addAttribute("endIndex", end);
        model.addAttribute("currentIndex", current);
        model.addAttribute("totalPageCount", totalPageCount);
        model.addAttribute("baseUrl", baseUrl);
        model.addAttribute("customerlist", pages);

        return "admin/customer/index";
    }

    @GetMapping({UrlConstants.URL_ADMIN_CUSTOMER_DETAIL})
    public String showListProductByCustomer(HttpServletRequest request, @PathVariable Long id, Model model) throws Exception {
        if(!AuthUtil.isLogin(request)) {
            return "redirect:" + UrlConstants.URL_ADMIN + UrlConstants.URL_LOGIN;
        }
        CustomerDTO customerDTO = customerService.findCustomerById(id);
        List<ProductCustomerDTO> listProduct = customerService.findAllProductByIdCustomer(id);
        int totalAmountAll = 0;
        if (listProduct.size() > 0) {
            for (ProductCustomerDTO productCustomerDTO : listProduct) {
                totalAmountAll += productCustomerDTO.getTotal_Money();
            }
        }
        model.addAttribute("customername", "Orderer: " + customerDTO.getFullName());
        model.addAttribute("listProduct", listProduct);
        model.addAttribute("totalAmountAll", totalAmountAll);
        return "admin/customer/product";
    }

    @PostMapping("/ajax/active")
    public void ajax(HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        out.println("<p>Done</p>");
    }
}
