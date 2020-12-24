package storysflower.com.storysflower.dto;

import java.util.List;

/**
 * @author ntynguyen
 */

public class CartShoppingDTO {
    private List<CartDTO> cartDTOS;

    public CartShoppingDTO() {
    }

    public CartShoppingDTO(List<CartDTO> cartDTOS) {
        this.cartDTOS = cartDTOS;
    }

    public List<CartDTO> getCartDTOS() {
        return cartDTOS;
    }

    public void setCartDTOS(List<CartDTO> cartDTOS) {
        this.cartDTOS = cartDTOS;
    }

    @Override
    public String toString() {
        return "CartShoppingDTO{" +
                "cartDTOS=" + cartDTOS +
                '}';
    }
}
