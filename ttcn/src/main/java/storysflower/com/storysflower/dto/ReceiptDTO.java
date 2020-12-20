package storysflower.com.storysflower.dto;

public class ReceiptDTO {
    private String id;
    private CustomerDTO customerDTO;
    private RecipientDTO recipientDTO;
    private String deliveryDate;
    private String deliveryHour;

    public ReceiptDTO() {
        customerDTO = new CustomerDTO();
        recipientDTO = new RecipientDTO();
    }

    public ReceiptDTO(String id, CustomerDTO customerDTO, RecipientDTO recipientDTO, String deliveryDate, String deliveryHour) {
        this.id = id;
        this.customerDTO = customerDTO;
        this.recipientDTO = recipientDTO;
        this.deliveryDate = deliveryDate;
        this.deliveryHour = deliveryHour;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CustomerDTO getCustomerDTO() {
        return customerDTO;
    }

    public void setCustomerDTO(CustomerDTO customerDTO) {
        this.customerDTO = customerDTO;
    }

    public RecipientDTO getRecipientDTO() {
        return recipientDTO;
    }

    public void setRecipientDTO(RecipientDTO recipientDTO) {
        this.recipientDTO = recipientDTO;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getDeliveryHour() {
        return deliveryHour;
    }

    public void setDeliveryHour(String deliveryHour) {
        this.deliveryHour = deliveryHour;
    }

    @Override
    public String toString() {
        return "ReceiptDTO{" +
                "id='" + id + '\'' +
                ", customerDTO=" + customerDTO +
                ", recipientDTO=" + recipientDTO +
                ", deliveryDate=" + deliveryDate +
                ", deliveryHour='" + deliveryHour + '\'' +
                '}';
    }
}
