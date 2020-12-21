package storysflower.com.storysflower.dto;

import java.util.List;

public class RevenueDTO {
    private int id;
    private String date;
    private List<ProductRevenueDTO> listCart;
    private Double totalMoney;

    public RevenueDTO() {
    }

    public RevenueDTO(int id, String date, List<ProductRevenueDTO> listCart) {
        this.id = id;
        this.date = date;
        this.listCart = listCart;
    }

    @Override
    public String toString() {
        return "RevenueDTO{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", listCart=" + listCart +
                ", totalMoney=" + totalMoney +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<ProductRevenueDTO> getListCart() {
        return listCart;
    }

    public void setListCart(List<ProductRevenueDTO> listCart) {
        this.listCart = listCart;
    }

    public Double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Double totalMoney) {
        this.totalMoney = totalMoney;
    }
}
