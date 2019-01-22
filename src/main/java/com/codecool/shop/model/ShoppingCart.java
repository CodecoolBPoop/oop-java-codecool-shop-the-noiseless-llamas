package com.codecool.shop.model;

import java.util.ArrayList;

public class ShoppingCart extends BaseModel {
    private ArrayList<Product> productList = new ArrayList<>();


   public ShoppingCart(String name, String description) {
       super(name, description);
   }

   public void addToCart(Product product) {
       productList.add(product);
   }

   public void removeFromCart(Product product) {
       productList.remove(product);
   }
}
