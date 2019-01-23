package com.codecool.shop.model;

import java.util.ArrayList;

public class ShoppingCart extends BaseModel {
    private ArrayList<Product> productList = new ArrayList<>();
    private int numberOfItems;
    private float total = 0;


   public ShoppingCart(String name, String description) {
       super(name, description);
   }

   public boolean contains(Product productToCheck) {
       boolean contains = false;
       for (Product product: productList) {
           if (product.getId() == productToCheck.getId()) contains = true;
       }
       return contains;
   }

   public void incrementQuantityById(int id) {
       for (Product product: productList) {
           if (product.getId() == id) {
               product.incrementQuantityInCartBy(1);
               numberOfItems++;
           }
       }
   }

    public ArrayList<Product> getProductList() {
        return productList;
    }


    public void addToCart(Product product) {
       productList.add(product);
       product.incrementQuantityInCartBy(1);
       numberOfItems++;
   }

   public void removeFromCart(Product product) {
       productList.remove(product);
   }

    public int getNumberOfItems() {
        return numberOfItems;
    }

    public float getTotal() {
       for (Product product: productList) {
           this.total += (float) product.getSubtotal();}
       return total;
    }
}
