package storysflower.com.storysflower.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * @author ntynguyen
 */
public class ReviewDTO {
    private Long productId;
    private Long userId;
    private String email;
    private String firstname;
    private String lastname;
    private Long imageId;
    @NotNull
    @Max(1000)
    private String content;
    private LocalDate dateCreate;

    public ReviewDTO() {
    }

    public ReviewDTO(Long productId, Long userId, String email, Long imageId, String content, LocalDate dateCreate) {
        this.productId = productId;
        this.userId = userId;
        this.email = email;
        this.imageId = imageId;
        this.content = content;
        this.dateCreate = dateCreate;
    }

    public ReviewDTO(Long productId, Long userId, String email, String firstname, String lastname, Long imageId, String content, LocalDate dateCreate) {
        this.productId = productId;
        this.userId = userId;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.imageId = imageId;
        this.content = content;
        this.dateCreate = dateCreate;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getImageId() {
        return imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDate getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(LocalDate dateCreate) {
        this.dateCreate = dateCreate;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Override
    public String toString() {
        return "ReviewDTO{" +
                "productId=" + productId +
                ", userId=" + userId +
                ", email='" + email + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", imageId=" + imageId +
                ", content='" + content + '\'' +
                ", dateCreate=" + dateCreate +
                '}';
    }
}
