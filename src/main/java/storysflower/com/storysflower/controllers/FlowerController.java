package storysflower.com.storysflower.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import storysflower.com.storysflower.services.OccasionService;
import storysflower.com.storysflower.services.ProductService;

@Controller
@RequestMapping("/flower")
public class FlowerController {
    private static final String FLOWERS = "flowers";
    private static final String OCCASION = "occasions";
    private static final String TOPIC = "topic";
    @Autowired
    private ProductService productService;
    @Autowired
    private OccasionService occasionService;

    @GetMapping()
    public String getOccasionPage(Model model) {
        model.addAttribute(FLOWERS, productService.getAllFlower());
        model.addAttribute(OCCASION, occasionService.findAllOccasion());
        model.addAttribute(TOPIC, "Flowers");
        return "flower";
    }
}
