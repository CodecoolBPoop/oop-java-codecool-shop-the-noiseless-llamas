package com.codecool.shop.dao.implementation;

import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class GeneralDaoJDBC {


    protected static final String DATABASE = System.getenv("DATABASE");
    protected static final String DB_USER = System.getenv("DB_USER");
    protected static final String DB_PASSWORD = System.getenv("DB_PASSWORD");

    protected Supplier getSupplier(String supplierName) {

        String query = "SELECT * FROM supplier;";
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query);
        ){
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                if (name.equals(supplierName)) {
                    Supplier supplier = new Supplier(name, description);
                    supplier.setId(id);
                    connection.close();
                    return supplier;
                }


            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    protected ProductCategory getProductCategory(String categoryName) {

        String query = "SELECT * FROM product_category;";
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query);
        ){
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                String department = resultSet.getString("department");
                if (name.equals(categoryName)) {
                    ProductCategory productCategory = new ProductCategory(name, department, description);
                    productCategory.setId(id);
                    connection.close();
                    return productCategory;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    protected Connection getConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(
                DATABASE,
                DB_USER,
                DB_PASSWORD);
    }




    protected void executeQuery(String query) {
        System.out.println("IS IT RUNNING??");
        try (Connection connection = getConnection();
             Statement statement =connection.createStatement();
        ){
            statement.execute(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
