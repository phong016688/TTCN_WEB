package storysflower.com.storysflower.dto;

import java.util.Date;

public class CustomerCartDTO {
    private String fullName;
    private Date buyDate;
    private String address;
    private String phoneNumber;
    private String email;
    private String messagesToUs;

    public CustomerCartDTO(String fullName, Date buyDate, String address, String phoneNumber, String email, String messagesToUs) {
        this.fullName = fullName;
        this.buyDate = buyDate;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.messagesToUs = messagesToUs;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(Date buyDate) {
        this.buyDate = buyDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessagesToUs() {
        return messagesToUs;
    }

    public void setMessagesToUs(String messagesToUs) {
        this.messagesToUs = messagesToUs;
    }
}
