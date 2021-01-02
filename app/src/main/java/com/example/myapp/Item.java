package com.example.myapp;

import java.io.Serializable;

public class Item implements Serializable {
    private String Grade;
    private String capacity;
    private String category;
    private String channel;
    private String model;
    private String price;
    private String sale1;
    private String sale2;
    private String sale3;
    private String sale_price;


    public Item() { }
    public Item(String Grade, String capacity , String category , String channel , String model , String price, String sale1 , String sale2 , String sale3 , String sale_price){
        this.Grade = Grade;
        this.capacity = capacity;
        this.category = category;
        this.channel = channel;
        this.model = model;
        this.price = price;
        this.sale1 = sale1;
        this.sale2 = sale2;
        this.sale3 = sale3;
        this.sale_price = sale_price;
    }

    public String getGrade() {
        return Grade;
    }

    public void setGrade(String grade) {
        Grade = grade;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSale1() {
        return sale1;
    }

    public void setSale1(String sale1) {
        this.sale1 = sale1;
    }

    public String getSale2() {
        return sale2;
    }

    public void setSale2(String sale2) {
        this.sale2 = sale2;
    }

    public String getSale3() {
        return sale3;
    }

    public void setSale3(String sale3) {
        this.sale3 = sale3;
    }

    public String getSale_price() {
        return sale_price;
    }

    public void setSale_price(String sale_price) {
        this.sale_price = sale_price;
    }

//    public Item(String text1, String text2){
//        this.text1 = text1;
//        this.text2 = text2;
//    }
}
