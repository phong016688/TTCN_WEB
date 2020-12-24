package storysflower.com.storysflower.controllers.api;


import org.jooq.tools.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import storysflower.com.storysflower.dto.ApiResponse;
import storysflower.com.storysflower.dto.CartDTO;
import storysflower.com.storysflower.services.CartService;
import storysflower.com.storysflower.services.UserService;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/cart")
public class CartRestController {
    public static final String CARTS = "carts";
    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;

    @GetMapping("/{productId}")
    public Object addCart(HttpSession httpSession, @PathVariable("productId") Long productId) {
        List<CartDTO> cartDTOList;
        if (httpSession.getAttribute(CARTS) == null) {
            cartDTOList = new ArrayList<>();
        } else {
            cartDTOList = (List<CartDTO>) httpSession.getAttribute(CARTS);
        }
        if (cartService.addCard(cartDTOList, productId, "1") == false) {
            return ApiResponse.failed("The product has been added");
        }
        ;
        httpSession.setAttribute(CARTS, cartDTOList);
        return ApiResponse.success("Successfull add to cart");
    }

    @GetMapping("delete/{productId}")
    public Object deleteCart(HttpSession httpSession, @PathVariable("productId") Long productId) {
        List<CartDTO> cartDTOList;
        if (httpSession.getAttribute(CARTS) == null) {
            return ApiResponse.failed("Don't have any product");
        } else {
            cartDTOList = (List<CartDTO>) httpSession.getAttribute(CARTS);
        }
        if (cartDTOList.removeIf(t -> t.getProductDTO().getId().equals(productId))) ;
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("totalCart", cartService.calculateTotal(cartDTOList));
        return jsonObject;
    }
}
