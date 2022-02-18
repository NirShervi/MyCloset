package com.example.mycloset;

public class Item {
    private String Uid;
    private String userID;
    private String type;
    private String color;
    private String brand;
    private String price;
    private String url;

    public Item() {
    }

    @Override
    public String toString() {
        return "Item{" +
                "userID='" + userID + '\'' +
                ", type='" + type + '\'' +
                ", color='" + color + '\'' +
                ", brand='" + brand + '\'' +
                ", price='" + price + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    public Item(String Uid,String userID, String type, String color, String brand, String price, String url) {
        this.Uid = Uid;
        this.userID = userID;
        this.type = type;
        this.color = color;
        this.brand = brand;
        this.price = price;
        this.url = url;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUserID() {
        return userID;
    }

    public String getType() {
        return type;
    }

    public String getColor() {
        return color;
    }

    public String getBrand() {
        return brand;
    }

    public String getUid() {
        return Uid;
    }

    public void setUid(String uid) {
        Uid = uid;
    }

    public String getPrice() {
        return price;
    }

    public String getUrl() { return url; }

}
