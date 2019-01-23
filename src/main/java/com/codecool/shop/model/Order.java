package com.codecool.shop.model;

import java.util.ArrayList;
import java.util.HashMap;

public class Order extends BaseModel {

    private ArrayList<Product> orderedItems;
    private HashMap<String, String> orderDetails;
    private String paymentType;
    private float totalPrice;
    private boolean paid;

    public Order(HashMap<String, String> orderDetails, float totalPrice, String paymentType) {
        super();
        this.paymentType = paymentType;
        this.orderDetails = orderDetails;
        this.totalPrice = totalPrice;
        this.paid = false;
    }
}
