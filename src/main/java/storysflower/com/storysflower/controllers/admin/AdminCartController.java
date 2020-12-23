package storysflower.com.storysflower.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import storysflower.com.storysflower.constants.CommonConstants;
import storysflower.com.storysflower.constants.UrlConstants;
import storysflower.com.storysflower.dto.CartAdminDTO;
import storysflower.com.storysflower.dto.CustomerCartDTO;
import storysflower.com.storysflower.dto.ProductCartDTO;
import storysflower.com.storysflower.dto.RecipientCartDTO;
import storysflower.com.storysflower.services.CartService;
import storysflower.com.storysflower.services.CustomerService;
import storysflower.com.storysflower.services.OccasionService;
import storysflower.com.storysflower.services.RecipientService;
import storysflower.com.storysflower.utils.AuthUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(UrlConstants.URL_ADMIN)
public class AdminCartController {
    @Autowired
    private CartService cartService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private RecipientService recipientService;

    private static final String OCCASIONS = "occasions";

    @Autowired
    private OccasionService occasionService;
    @ModelAttribute
    public void leftbar(Model model){
        model.addAttribute(OCCASIONS, occasionService.findAllOccasion());
    }

    @GetMapping({UrlConstants.URL_ADMIN_CART_INDEX})
    public String index(Model model, HttpServletRequest request, RedirectAttributes redirect) throws Exception {
        if(!AuthUtil.isLogin(request)) {
            return "redirect:" + UrlConstants.URL_ADMIN + UrlConstants.URL_LOGIN;
        }
        request.getSession().setAttribute("cart", null);
        if (model.asMap().get("success") != null)
            redirect.addFlashAttribute("success", model.asMap().get("success").toString());
        return "redirect:" + UrlConstants.URL_ADMIN + UrlConstants.URL_ADMIN_CART_INDEX + "/1";
    }

    @GetMapping({UrlConstants.URL_ADMIN_CART_INDEX_PAGINATION})
    public String shoListCustomerPage(HttpServletRequest request, @PathVariable int page, Model model) throws Exception {
        if(!AuthUtil.isLogin(request)) {
            return "redirect:" + UrlConstants.URL_ADMIN + UrlConstants.URL_LOGIN;
        }
        PagedListHolder<?> pages = (PagedListHolder<?>) request.getSession().getAttribute("cart");
        List<CartAdminDTO> list = cartService.findAll();
        if (pages == null) {
            pages = new PagedListHolder<>(list);
            pages.setPageSize(CommonConstants.DEFAULT_PAGING_CART_SIZE);
        } else {
            final int goToPage = page - 1;
            if (goToPage <= pages.getPageCount() && goToPage >= 0) {
                pages.setPage(goToPage);
            }
        }
        request.getSession().setAttribute("cart", pages);
        int current = pages.getPage() + 1;
        int begin = Math.max(1, current - list.size());
        int end = Math.min(begin + 5, pages.getPageCount());
        int totalPageCount = pages.getPageCount();
        String baseUrl = UrlConstants.URL_ADMIN + UrlConstants.URL_ADMIN_CART_INDEX + "/";
        model.addAttribute("beginIndex", begin);
        model.addAttribute("endIndex", end);
        model.addAttribute("currentIndex", current);
        model.addAttribute("totalPageCount", totalPageCount);
        model.addAttribute("baseUrl", baseUrl);
        model.addAttribute("cartlist", pages);
        return "admin/cart/index";
    }

    @GetMapping({UrlConstants.URL_ADMIN_CART_DETAIL})
    public String detail(@PathVariable Long id, Model model, HttpServletRequest request) throws Exception {
        if(!AuthUtil.isLogin(request)) {
            return "redirect:" + UrlConstants.URL_ADMIN + UrlConstants.URL_LOGIN;
        }
        RecipientCartDTO recipientCartDTO = recipientService.findRecipientCartDTObyIdBuyProduct(id);
        if (recipientCartDTO == null) {
            return "redirect:" + UrlConstants.URL_ADMIN + UrlConstants.URL_404_;
        }
        CustomerCartDTO customerCartDTO = customerService.findCustomerCartDTOByIdBuyProduct(id);
        List<ProductCartDTO> listProduct = cartService.findAllListProductByIdCart(id);
        int status = cartService.getStatus(id);
        Double totalMoney = 0.0;
        for (ProductCartDTO p : listProduct) {
            System.out.println(p);
            totalMoney += p.getTotalMoney();
        }
        model.addAttribute("recipientCartDTO", recipientCartDTO);
        model.addAttribute("customerCartDTO", customerCartDTO);
        model.addAttribute("productCartDTO", listProduct);
        model.addAttribute("totalMoney", totalMoney);
        model.addAttribute("status", status);
        return "admin/cart/detail";
    }

    @GetMapping({UrlConstants.URL_ADMIN_CART_ORDER})
    public String order(@PathVariable Long id, RedirectAttributes redirect, HttpServletRequest request) throws Exception {
        if(!AuthUtil.isLogin(request)) {
            return "redirect:" + UrlConstants.URL_ADMIN + UrlConstants.URL_LOGIN;
        }
        if (cartService.updateStatus(id)) {
            redirect.addFlashAttribute("msg", "Success");
            return "redirect:" + UrlConstants.URL_ADMIN + UrlConstants.URL_ADMIN_CART_INDEX;
        } else {
            redirect.addFlashAttribute("msg", "Error");
            return "redirect:" + UrlConstants.URL_ADMIN + "/cart/detail/" + id;
        }
    }
}
