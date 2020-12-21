package storysflower.com.storysflower.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import storysflower.com.storysflower.dto.ReviewDTO;
import storysflower.com.storysflower.services.CategoryService;
import storysflower.com.storysflower.services.ProductService;
import storysflower.com.storysflower.services.ReviewService;

/**
 * @author ntynguyen
 */
@Controller
@RequestMapping("/product")
public class ProductDetailController {
    private static final String CATEGORIES = "categories";
    private static final String PRODUCT = "product";
    private static final String REVIEWS = "reviews";
    private static final String REVIEWDTO = "reviewDTO";
    private static final String BESTRATINGPRODUCTS = "bestRatingProducts";

    @Autowired
    CategoryService categoryService;

    @Autowired
    ProductService productService;

    @Autowired
    ReviewService reviewService;

    @GetMapping("/{id}")
    public String getProductPage(Model model, @PathVariable("id") Long id) {
        model.addAttribute(CATEGORIES, categoryService.getCategories());
        model.addAttribute(PRODUCT, productService.getProductDetailDTOById(id));
        model.addAttribute(REVIEWS, reviewService.getAllReviewsByProductId(id));
        model.addAttribute(REVIEWDTO, new ReviewDTO());
        System.out.println(productService.getListBestProductDTOByRatting());
        model.addAttribute(BESTRATINGPRODUCTS, productService.getListBestProductDTOByRatting());
        return "product-detail/detail";
    }
}
