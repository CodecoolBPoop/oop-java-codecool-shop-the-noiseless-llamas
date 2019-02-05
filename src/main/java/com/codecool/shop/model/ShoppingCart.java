package com.codecool.shop.model;

import java.util.ArrayList;

public class ShoppingCart extends BaseModel {
    private ArrayList<Product> productList = new ArrayList<>();
    private int numberOfItems;


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
           }
       }
   }

   public void decrementQuantityById(int id){
       for (Product product: productList){
           if (product.getId() == id){
               product.decrementQuantityInCartBy(1);
               numberOfItems--;
           }
       }
   }


    public void addToCart(Product product) {
       productList.add(product);
       product.incrementQuantityInCartBy(1);
   }

   public void removeFromCart(Product product){
       productList.remove(product);
       product.decrementQuantityInCartBy(1);
   }


    public int getNumberOfItems() {
       int numberOfItems = 0;
       for (Product product: productList) {
           numberOfItems = numberOfItems + product.getQuantityInCart();
       } return numberOfItems;
    }

    public float getTotal() {
       float total = 0;
       for (Product product: productList) {
           total += product.getSubtotal();
       } return total;
    }

    public ArrayList<Product> getProductList() {
        return productList;
    }
}
