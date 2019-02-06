package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoJDBC extends GeneralDaoJDBC implements ProductDao {


    @Override
    public void add(Product product) {

    }

    @Override
    public Product find(int id) {return null;}


    @Override
    public void remove(int id) {

    }

    @Override
    public List<Product> getAll() {

            String query = "SELECT * FROM product";

            try (Connection connection = getConnection();
                 Statement statement =connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(query);
            ){
                while (resultSet.next()){
                    int productid = resultSet.getInt("productid");
                    String name = resultSet.getString("name");
                    String description = resultSet.getString("description");
                    float price = resultSet.getInt("price");
                    String currency = resultSet.getString("currency");
                    String supplier = resultSet.getString("supplier");
                    String product_category = resultSet.getString("product_category");


                    // we have to create the supplier and product_category from the received String, and then add it to the Product constructor
                    Product product = new Product(productid, name, price, currency, description, null, null);
                    List<Product> productList = new ArrayList<>();
                    productList.add(product);
                    return productList;
                } else {
                    return null;
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

            return null;
        }


    @Override
    public List<Product> getBy(Supplier supplier) {
        return null;
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        return null;
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory, Supplier supplier) {
        return null;
    }

}
