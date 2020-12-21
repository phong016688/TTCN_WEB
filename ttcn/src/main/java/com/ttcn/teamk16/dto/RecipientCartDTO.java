package storysflower.com.storysflower.dto;

import java.util.Date;

public class RecipientCartDTO {
    private int id;
    private String fullName;
    private String address;
    private String phoneNumber;
    private Date deliveryDate;
    private Date deliveryHour;

    public RecipientCartDTO(int id, String fullName, String address, String phoneNumber, Date deliveryDate, Date deliveryHour) {
        this.deliveryDate = deliveryDate;
        this.deliveryHour = deliveryHour;
        this.id = id;
        this.fullName = fullName;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Date getDeliveryHour() {
        return deliveryHour;
    }

    public void setDeliveryHour(Date deliveryHour) {
        this.deliveryHour = deliveryHour;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
