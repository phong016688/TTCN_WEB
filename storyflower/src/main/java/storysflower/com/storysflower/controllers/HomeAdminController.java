package storysflower.com.storysflower.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import storysflower.com.storysflower.services.DealService;
import storysflower.com.storysflower.services.OccasionService;

/**
 * @author hnguyen
 */

@Controller
public class HomeAdminController {
    private static final String OCCASION = "occasions";
    private static final String DEAL_PRODUCT = "dealproducts";

    @Autowired
    private OccasionService occasionService;

    @Autowired
    private DealService dealService;

    @GetMapping("homead")
    public String getHomePage(Model model) {
        // return "admin/login";
        return "fragments/admin-fr/home_ad";
        //  /home/hoang/A41/TTCN/Web/storyflower/src/main/resources/templates/admin/login.html
        // /home/hoang/A41/TTCN/Web/storyflower/src/main/resources/templates/fragments/admin-fr/home_ad.html
    }
}
