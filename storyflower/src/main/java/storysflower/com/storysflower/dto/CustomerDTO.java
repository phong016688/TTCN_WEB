package storysflower.com.storysflower.dto;

public class CustomerDTO {
    private Long id;
    private Long userId;
    private String fullName;
    private  String lastName;
    private  String firstName;
    private String address;
    private String phoneNumber;
    private String email;

    public CustomerDTO(Long id, String lastName, String firstName, String email) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
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

    public CustomerDTO(Long id, Long userId, String fullName, String address, String phoneNumber, String email) {
        this.id = id;
        this.userId = userId;
        this.fullName = fullName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public CustomerDTO() {
    }

    public CustomerDTO(Long id, String fullName, String address, String phoneNumber, String email) {
        this.id = id;
        this.fullName = fullName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "CustomerDTO{" +
                "id=" + id +
                ", userId=" + userId +
                ", fullName='" + fullName + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
