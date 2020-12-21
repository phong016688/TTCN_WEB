package storysflower.com.storysflower.controllers.api;

import org.jooq.tools.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import storysflower.com.storysflower.config.security.CustomUserDetail;
import storysflower.com.storysflower.dto.ApiResponse;
import storysflower.com.storysflower.exceptions.ApiException;
import storysflower.com.storysflower.services.FavouriteService;
import storysflower.com.storysflower.services.UserService;

/**
 * @author ntynguyen
 */
@RestController
@RequestMapping("api/favourite")
public class FavouriteRestController {
    @Autowired
    private UserService userService;

    @Autowired
    private FavouriteService favouriteService;

    @GetMapping("/{productId}")
    public Object getFavourite(@PathVariable("productId") Long productId) {
        JSONObject jsonObject = new JSONObject();
        if (userService.getUser() == null) {
            jsonObject.put("favourite", false);
            return jsonObject;
        }
        CustomUserDetail customUserDetail = userService.getUser();
        jsonObject.put("favourite", favouriteService.getFavouriteByUserId(productId, customUserDetail.getId()));
        return jsonObject;
    }

    @PostMapping("/{productId}")
    public Object updateFavourite(@PathVariable("productId") Long productId, @RequestParam("isFavourite") Boolean isFavourite) {
        if (userService.getUser() == null) {
            return new ApiException(HttpStatus.UNAUTHORIZED, "Please login to favourite");
        }
        CustomUserDetail customUserDetail = userService.getUser();
        if (favouriteService.updateFavourite(productId, customUserDetail.getId(), !isFavourite)) {
            return ApiResponse.success("Favourite is successfull");
        }
        return new ApiException(HttpStatus.BAD_REQUEST, "Some thing went wrong");
    }
}
