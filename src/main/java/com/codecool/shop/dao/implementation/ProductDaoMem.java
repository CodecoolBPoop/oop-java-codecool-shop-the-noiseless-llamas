package com.codecool.shop.dao.implementation;


import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductDaoMem implements ProductDao {

    private List<Product> data = new ArrayList<>();
    private static ProductDaoMem instance = null;

    /* A private Constructor prevents any other class from instantiating.
     */
    private ProductDaoMem() {
    }

    public static ProductDaoMem getInstance() {
        if (instance == null) {
            instance = new ProductDaoMem();
        }
        return instance;
    }

    @Override
    public void add(Product product) {
        data.add(product);
    }

    @Override
    public Product find(int id) {
        return data.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }

    @Override
    public void remove(int id) {
        data.remove(find(id));
    }

    @Override
    public List<Product> getAll() {
        return data;
    }

    @Override
    public List<Product> getBy(Supplier supplier) {
        if(supplier.getName().equals("All")) {
            return data;
        } else {
            return data.stream().filter(t -> t.getSupplier().equals(supplier)).collect(Collectors.toList());
        }
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        if (productCategory.getName().equals("All")) {
            return data;
        } else {
            return data.stream().filter(t -> t.getProductCategory().equals(productCategory)).collect(Collectors.toList());
        }
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory, Supplier supplier) {
        List<Product> filteredByCategory = getBy(productCategory);
        if(supplier.getName().equals("All")) {
            return filteredByCategory;
        } else {
            return filteredByCategory.stream().filter(t -> t.getSupplier().equals(supplier)).collect(Collectors.toList());
        }
    }
}
