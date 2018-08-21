package cn.free.pojo;


import cn.free.util.JsonDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class SaleDetail {



    private int id;
    private double price;
    private int quantity;
    private double totalPrice;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonSerialize(using=JsonDateSerializer.class)
    private Date saleDate;
    private String salesman;
    private String product;

    public SaleDetail() {
    }

    public SaleDetail(int id, double price, int quantity, double totalPrice, Date saleDate, String salesman, String product) {
        this.id = id;
        this.price = price;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.saleDate = saleDate;
        this.salesman = salesman;
        this.product = product;
    }

    @Override
    public String toString() {
        return "SaleDetail{" +
                "id=" + id +
                ", price=" + price +
                ", quantity=" + quantity +
                ", totalPrice=" + totalPrice +
                ", saleDate=" + saleDate +
                ", salesman=" + salesman +
                ", product=" + product +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    public String getSalesman() {
        return salesman;
    }

    public void setSalesman(String salesman) {
        this.salesman = salesman;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }
}
