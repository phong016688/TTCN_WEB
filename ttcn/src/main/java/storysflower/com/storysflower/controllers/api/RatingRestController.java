package storysflower.com.storysflower.controllers.api;

import org.jooq.tools.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import storysflower.com.storysflower.config.security.CustomUserDetail;
import storysflower.com.storysflower.dto.ApiResponse;
import storysflower.com.storysflower.exceptions.ApiException;
import storysflower.com.storysflower.services.RattingService;
import storysflower.com.storysflower.services.UserService;

/**
 * @author ntynguyen
 */
@RestController
@RequestMapping("/api/rating")
public class RatingRestController {
    @Autowired
    private RattingService rattingService;

    @Autowired
    private UserService userService;

    @GetMapping("/{productId}")
    public Object getRate(@PathVariable("productId") Long productId) {
        CustomUserDetail customUserDetail = userService.getUser();
        if (customUserDetail == null) {
            return new ApiException(HttpStatus.UNAUTHORIZED, "Please login to rating");
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("rating", rattingService.getRateProductByUserId(productId, customUserDetail.getId()));
        return jsonObject;
    }

    @PostMapping("/{productId}")
    public Object updateRate(@PathVariable("productId") Long productId, @RequestParam("rating") int value) {
        CustomUserDetail customUserDetail = userService.getUser();
        if (customUserDetail == null) {
            return new ApiException(HttpStatus.UNAUTHORIZED, "Please login to rating");
        }
        if (rattingService.updateRatingOfActivityByUserId(productId, customUserDetail.getId(), value)) {
            return ApiResponse.success("Rating Successfully!");
        }
        return ApiResponse.failed("Something went wrong!");
    }

    @GetMapping("/update/{productId}")
    public Object updateRateInDetailPage(@PathVariable("productId") Long productId) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("rating", rattingService.getRate(productId));
        return jsonObject;
    }
}
