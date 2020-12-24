package storysflower.com.storysflower.dto;

/**
 * @author ntynguyen
 */
public class CategoryDTO {
    private String categoryName;
    private String categoryMeaning;

    public CategoryDTO() {
    }

    public CategoryDTO(String categoryName, String categoryMeaning) {
        this.categoryName = categoryName;
        this.categoryMeaning = categoryMeaning;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryMeaning() {
        return categoryMeaning;
    }

    public void setCategoryMeaning(String categoryMeaning) {
        this.categoryMeaning = categoryMeaning;
    }

    @Override
    public String toString() {
        return "CategoryDTO{" +
                "categoryName='" + categoryName + '\'' +
                ", categoryMeaning='" + categoryMeaning + '\'' +
                '}';
    }
}
