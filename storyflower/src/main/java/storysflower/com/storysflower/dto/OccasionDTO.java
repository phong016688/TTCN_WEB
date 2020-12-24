package storysflower.com.storysflower.dto;

/**
 * Activity Service interface.
 *
 * @author ntynguyen
 */

public class OccasionDTO {
    private Long occasionId;
    private String nameOccasion;
    private Long imageId;

    public OccasionDTO() {
    }

    public OccasionDTO(Long occasionId, String nameOccasion, Long imageId) {
        this.occasionId = occasionId;
        this.nameOccasion = nameOccasion;
        this.imageId = imageId;
    }

    public Long getOccasionId() {
        return occasionId;
    }

    public void setOccasionId(Long occasionId) {
        this.occasionId = occasionId;
    }

    public String getNameOccasion() {
        return nameOccasion;
    }

    public void setNameOccasion(String nameOccasion) {
        this.nameOccasion = nameOccasion;
    }

    public Long getImageId() {
        return imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }

    @Override
    public String toString() {
        return "OccasionDTO{" +
                "occasionId=" + occasionId +
                ", nameOccasion='" + nameOccasion + '\'' +
                ", imageId=" + imageId +
                '}';
    }
}
