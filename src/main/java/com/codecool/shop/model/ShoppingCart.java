package com.codecool.shop.model;

import java.util.ArrayList;

public class ShoppingCart extends BaseModel {
    private ArrayList<Product> productList = new ArrayList<>();
    private int numberOfItems;


   public ShoppingCart(String name, String description) {
       super(name, description);
   }

    public ArrayList<Product> getProductList() {
        return productList;
    }


    public void addToCart(Product product) {
       productList.add(product);
   }

   public void incrementNumberOfItems(int number) {
       this.numberOfItems = this.numberOfItems + number;
   }

   public void removeFromCart(Product product) {
       productList.remove(product);
   }

   public void addToCartById(int id){


   }

    public int getNumberOfItems() {
        return numberOfItems;
    }
}
