package storysflower.com.storysflower.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import storysflower.com.storysflower.services.DealService;
import storysflower.com.storysflower.services.OccasionService;

/**
 * @author ntynguyen
 */

@Controller
public class HomeController {
    private static final String OCCASION = "occasions";
    private static final String DEAL_PRODUCT = "dealproducts";

    @Autowired
    private OccasionService occasionService;

    @Autowired
    private DealService dealService;

    @GetMapping("/")
    public String getHomePage(Model model) {
        model.addAttribute(OCCASION, occasionService.findAllOccasion());
        model.addAttribute(DEAL_PRODUCT, dealService.findAllDealProducts());
        return "home";
    }
}
