package com.codecool.shop.dao;

import com.codecool.shop.model.ShoppingCart;

import java.util.List;

public interface ShoppingCartDao {
    void add(ShoppingCart cart);
    ShoppingCart find(int id);
    void remove(int id);

    List<ShoppingCart> getAll();
}
