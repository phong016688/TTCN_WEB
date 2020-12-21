package storysflower.com.storysflower.dto;

import java.util.Date;
import java.util.List;

public class CartAdminDTO {
    private Long id;
    private String full_name;
    private List<CartDTO> listCart;
    private int status;
    private Date delivery_date;
    private String message_to_us;

    public CartAdminDTO() {
    }

    public CartAdminDTO(Long id, List<CartDTO> listCart) {
        this.id = id;
        this.listCart = listCart;
    }

    public CartAdminDTO(Long id, String full_name, Date delivery_date, String message_to_us, int status) {
        this.id = id;
        this.full_name = full_name;
        this.delivery_date = delivery_date;
        this.message_to_us = message_to_us;
        this.status = status;
    }

    @Override
    public String toString() {
        return "CartAdminDTO{" +
                "id=" + id +
                ", full_name='" + full_name + '\'' +
                ", listCart=" + listCart +
                ", status=" + status +
                ", delivery_date=" + delivery_date +
                ", message_to_us='" + message_to_us + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public List<CartDTO> getListCart() {
        return listCart;
    }

    public void setListCart(List<CartDTO> listCart) {
        this.listCart = listCart;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getDelivery_date() {
        return delivery_date;
    }

    public void setDelivery_date(Date delivery_date) {
        this.delivery_date = delivery_date;
    }

    public String getMessage_to_us() {
        return message_to_us;
    }

    public void setMessage_to_us(String message_to_us) {
        this.message_to_us = message_to_us;
    }
}
