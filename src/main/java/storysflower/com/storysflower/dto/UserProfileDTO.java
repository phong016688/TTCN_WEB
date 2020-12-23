package storysflower.com.storysflower.dto;

/**
 * @author ntynguyen
 */
public class UserProfileDTO {
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private Long imageID;

    public UserProfileDTO() {
    }

    public UserProfileDTO(String fisrtName, String lastName, String email, String password) {
        this.firstname = fisrtName;
        this.lastname = lastName;
        this.email = email;
        this.password = password;
    }

    ;

    public UserProfileDTO(String fisrtName, String lastName, String email, String password, Long imageID) {
        this.firstname = fisrtName;
        this.lastname = lastName;
        this.email = email;
        this.password = password;
        this.imageID = imageID;
    }

    public UserProfileDTO(Long id, String fisrtName, String lastName, String email, String password, Long imageID) {
        this.id = id;
        this.firstname = fisrtName;
        this.lastname = lastName;
        this.email = email;
        this.password = password;
        this.imageID = imageID;
    }

    public static UserProfileDTO create() {
        return new UserProfileDTO();
    }

    public String getFisrtName() {
        return firstname;
    }

    public void setFisrtName(String fisrtName) {
        this.firstname = fisrtName;
    }

    public String getLastName() {
        return lastname;
    }

    public void setLastName(String lastName) {
        this.lastname = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getImageID() {
        return imageID;
    }

    public void setImageID(Long imageID) {
        this.imageID = imageID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
