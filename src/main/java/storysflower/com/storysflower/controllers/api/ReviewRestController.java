package storysflower.com.storysflower.controllers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import storysflower.com.storysflower.config.security.CustomUserDetail;
import storysflower.com.storysflower.dto.ReviewDTO;
import storysflower.com.storysflower.exceptions.ApiException;
import storysflower.com.storysflower.services.ReviewService;
import storysflower.com.storysflower.services.UserService;

import java.util.List;

/**
 * @author ntynguyen
 */
@RestController
@RequestMapping("api/review")
public class ReviewRestController {
    @Autowired
    UserService userService;

    @Autowired
    ReviewService reviewService;

    @PostMapping("/{productId}")
    public Object updateReview(@PathVariable("productId") Long productId, @RequestParam("content") String content, @RequestParam("listSize") String listSize) {
        CustomUserDetail customUserDetail = userService.getUser();
        if (customUserDetail == null) {
            ApiException apiException = new ApiException(HttpStatus.UNAUTHORIZED, "Please login to review");
            return new ResponseEntity<ApiException>(apiException, HttpStatus.UNAUTHORIZED);
        }
        reviewService.insertReview(productId, userService.getUser().getId(), content);
        if (listSize == "") {
            listSize = "0";
        }
        System.out.println(reviewService.getAllNewReiview(Integer.parseInt(listSize), productId) + "================");
        return new ResponseEntity<List<ReviewDTO>>(reviewService.getAllNewReiview(Integer.parseInt(listSize), productId), HttpStatus.OK);
    }
}
