package storysflower.com.storysflower.dto;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

public class UserDTO {
    private Long id;
    @NotEmpty(message = "Vui lòng nhập LastName")
    private String lastName;
    @NotEmpty(message = "Vui lòng nhập FirstName")
    private String firstName;
    @NotEmpty(message = "Vui lòng nhập mật khẩu")
    @Size(min = 5, max = 100, message = "Độ dài dài mật khẩu không tốt!")
    private String passWord;
    private String rePassWord;
    @Email(message = "Không đúng định dạng Email!")
    private String email;
    private String role;

    public UserDTO(Long id, String lastName, String firstName, String passWord, String email, String role) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.passWord = passWord;
        this.email = email;
        this.role = role;
    }

    public UserDTO(String lastName, String firstName, String passWord, String rePassWord, String email) {
        this.rePassWord = rePassWord;
        this.lastName = lastName;
        this.firstName = firstName;
        this.passWord = passWord;
        this.email = email;
    }

    public UserDTO() {
    }

    public String getRePassWord() {
        return rePassWord;
    }

    public void setRePassWord(String rePassWord) {
        this.rePassWord = rePassWord;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", rePassWord='" + rePassWord + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

    public UserDTO(String passWord, String email) {
        this.passWord = passWord;
        this.email = email;
    }
}
