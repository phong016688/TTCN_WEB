package storysflower.com.storysflower.dto;

import java.util.Objects;

/**
 * @author ntynguyen
 */
public class CartDTO {
    private ProductDTO productDTO;
    private int quantity;

    public CartDTO() {
    }

    public CartDTO(ProductDTO productDTO, int quantity) {
        this.productDTO = productDTO;
        this.quantity = quantity;
    }

    public ProductDTO getProductDTO() {
        return productDTO;
    }

    public void setProductDTO(ProductDTO productDTO) {
        this.productDTO = productDTO;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "CartDTO{" +
                "productDTO=" + productDTO +
                ", quantity=" + quantity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartDTO cartDTO = (CartDTO) o;
        return Objects.equals(productDTO, cartDTO.productDTO);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productDTO);
    }
}
