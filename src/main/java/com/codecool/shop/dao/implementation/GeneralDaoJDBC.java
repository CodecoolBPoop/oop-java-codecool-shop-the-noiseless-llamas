package com.codecool.shop.dao.implementation;

import com.codecool.shop.model.Product;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public abstract class GeneralDaoJDBC {

    protected List<Product> data = new ArrayList<>();
    protected static final String DATABASE = System.getenv("DATABASE");
    protected static final String DB_USER = System.getenv("DB_USER");
    protected static final String DB_PASSWORD = System.getenv("DB_PASSWORD");

    protected Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                DATABASE,
                DB_USER,
                DB_PASSWORD);
    }

    protected void executeQuery(String query) {
        try (Connection connection = getConnection();
             Statement statement =connection.createStatement();
        ){
            statement.execute(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
