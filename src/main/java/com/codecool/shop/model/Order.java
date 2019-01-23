package com.codecool.shop.model;

import java.util.ArrayList;

public class Order extends BaseModel {

    private ArrayList<Product> orderedItems;
    private ArrayList<String> orderDetails;
    private String paymentType;
    private float totalPrice;
    private static int counter = 0;
    private boolean paid;

    public Order(String name, ArrayList<String> orderDetails, float totalPrice, String paymentType) {
        super(name);
        this.paymentType = paymentType;
        this.orderDetails = orderDetails;
        this.totalPrice = totalPrice;
        this.paid = false;
    }
}
