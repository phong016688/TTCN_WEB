package storysflower.com.storysflower.dto;

import java.util.Date;

public class ProductCustomerDTO {
    private int id;
    private String product_name;
    private Double price;
    private Integer quantity;
    private Date buy_date;
    private Double total_Money;

    public ProductCustomerDTO() {
    }

    public ProductCustomerDTO(String product_name, Double price, Integer quantily, Date buy_date) {
        this.product_name = product_name;
        this.price = price;
        this.quantity = quantily;
        this.buy_date = buy_date;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getTotal_Money() {
        return total_Money;
    }

    public void setTotal_Money(Double total_Money) {
        this.total_Money = total_Money;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }


    public Date getBuy_date() {
        return buy_date;
    }

    public void setBuy_date(Date buy_date) {
        this.buy_date = buy_date;
    }

}
