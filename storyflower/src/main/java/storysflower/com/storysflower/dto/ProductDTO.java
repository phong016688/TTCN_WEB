package storysflower.com.storysflower.dto;

/**
 * @author ntynguyen
 */
public class ProductDTO {
    private Long id;
    private Long imageId;
    private String productName;
    private Double price;
    private Integer rating;

    public ProductDTO() {
    }

    public ProductDTO(Long id, String productName, Double price) {
        this.id = id;
        this.productName = productName;
        this.price = price;
    }

    public ProductDTO(Long id, Long imageId, String productName, Double price, Integer rating) {
        this.id = id;
        this.imageId = imageId;
        this.productName = productName;
        this.price = price;
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "ProductDTO{" +
                "id=" + id +
                ", imageId=" + imageId +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                ", rating=" + rating +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getImageId() {
        return imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }
}
