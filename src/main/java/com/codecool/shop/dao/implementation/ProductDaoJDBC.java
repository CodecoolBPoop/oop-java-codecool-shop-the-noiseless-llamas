package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoJDBC extends GeneralDaoJDBC implements ProductDao {

    protected List<Product> data = new ArrayList<>();


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

            String query = "SELECT * FROM product;";
            List<Product> productList = new ArrayList<>();

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
                    Supplier supplierObj = getSupplier(supplier);
                    ProductCategory productCategoryObj = getProductCategory(product_category);
                    Product product = new Product(productid, name, price, currency, description, productCategoryObj, supplierObj);
                    productList.add(product);
                    ProductDao productDataStore = ProductDaoMem.getInstance();
                    productDataStore.add(product);
                }
                return productList;

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
