package storysflower.com.storysflower.dto;

public class RecipientDTO {
    private Long id;
    private String fullName;
    private String address;
    private String phoneNumber;
    private String email;
    private String messageToRecipient;
    private String messageToUs;

    public RecipientDTO() {
    }

    public RecipientDTO(Long id, String fullName, String address, String phoneNumber, String email, String messageToRecipient, String messageToUs) {
        this.id = id;
        this.fullName = fullName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.messageToRecipient = messageToRecipient;
        this.messageToUs = messageToUs;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getMessageToRecipient() {
        return messageToRecipient;
    }

    public void setMessageToRecipient(String messageToRecipient) {
        this.messageToRecipient = messageToRecipient;
    }

    public String getMessageToUs() {
        return messageToUs;
    }

    public void setMessageToUs(String messageToUs) {
        this.messageToUs = messageToUs;
    }
}
