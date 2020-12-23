package storysflower.com.storysflower.dto;

import java.util.Date;

public class ProductRevenueDTO {
    private int id;
    private String name;
    private Date date;
    private String DateStr;
    private int quatity;
    private Double price;

    public ProductRevenueDTO(int id, String name, int quatity, Double price) {
        this.id = id;
        this.name = name;
        this.quatity = quatity;
        this.price = price;
    }

    public ProductRevenueDTO(int id, String name, Date date, int quatity, Double price) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.quatity = quatity;
        this.price = price;
    }

    @Override
    public String toString() {
        return "ProductRevenueDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", DateStr='" + DateStr + '\'' +
                ", quatity=" + quatity +
                ", price=" + price +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDateStr() {
        return DateStr;
    }

    public void setDateStr(String dateStr) {
        DateStr = dateStr;
    }

    public int getQuatity() {
        return quatity;
    }

    public void setQuatity(int quatity) {
        this.quatity = quatity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
