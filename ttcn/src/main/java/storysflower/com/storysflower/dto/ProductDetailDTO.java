package storysflower.com.storysflower.dto;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author ntynguyen
 */
public class ProductDetailDTO {
    private Long id;
    private Long imageId;
    private List<Long> imageIds;
    private String productName;
    private String nameOccasion;
    private Long idOccasion;
    private Long idTopic;
    private String topicName;
    private String description;
    private String mean;
    private BigDecimal price;
    private Integer rating;
    private List<CategoryDTO> categories;

    public ProductDetailDTO() {
    }

    public ProductDetailDTO(Long id, String productName, Long idOccasion, Long idTopic,
                            String description, BigDecimal price) {
        this.id = id;
        this.productName = productName;
        this.idOccasion = idOccasion;
        this.idTopic = idTopic;
        this.description = description;
        this.price = price;
    }

    public ProductDetailDTO(Long id, Long imageId, List<Long> imageIds,
                            String productName, String nameOccasion, Long idOccasion,
                            Long idTopic, String topicName, String description, String mean, BigDecimal price, Integer rating, List<CategoryDTO> categories) {
        this.id = id;
        this.imageId = imageId;
        this.imageIds = imageIds;
        this.productName = productName;
        this.nameOccasion = nameOccasion;
        this.idOccasion = idOccasion;
        this.idTopic = idTopic;
        this.topicName = topicName;
        this.description = description;
        this.mean = mean;
        this.price = price;
        this.rating = rating;
        this.categories = categories;
    }

    public ProductDetailDTO(Long id, Long imageId, List<Long> imageIds,
                            String productName, String nameOccasion, String topicName,
                            String description, String mean, BigDecimal price, Integer rating, List<CategoryDTO> categories) {
        this.id = id;
        this.imageId = imageId;
        this.imageIds = imageIds;
        this.productName = productName;
        this.nameOccasion = nameOccasion;
        this.topicName = topicName;
        this.description = description;
        this.mean = mean;
        this.price = price;
        this.rating = rating;
        this.categories = categories;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getImageId() {
        return imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }

    public List<Long> getImageIds() {
        return imageIds;
    }

    public void setImageIds(List<Long> imageIds) {
        this.imageIds = imageIds;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getNameOccasion() {
        return nameOccasion;
    }

    public void setNameOccasion(String nameOccasion) {
        this.nameOccasion = nameOccasion;
    }

    public Long getIdOccasion() {
        return idOccasion;
    }

    public void setIdOccasion(Long idOccasion) {
        this.idOccasion = idOccasion;
    }

    public Long getIdTopic() {
        return idTopic;
    }

    public void setIdTopic(Long idTopic) {
        this.idTopic = idTopic;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMean() {
        return mean;
    }

    public void setMean(String mean) {
        this.mean = mean;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public List<CategoryDTO> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryDTO> categories) {
        this.categories = categories;
    }

    @Override
    public String toString() {
        return "ProductDetailDTO{" +
                "id=" + id +
                ", imageId=" + imageId +
                ", imageIds=" + imageIds +
                ", productName='" + productName + '\'' +
                ", nameOccasion='" + nameOccasion + '\'' +
                ", idOccasion=" + idOccasion +
                ", idTopic=" + idTopic +
                ", topicName='" + topicName + '\'' +
                ", description='" + description + '\'' +
                ", mean='" + mean + '\'' +
                ", price=" + price +
                ", rating=" + rating +
                ", categories=" + categories +
                '}';
    }
}
