package com.codecool.shop.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class RegistrationDaoJDBC extends GeneralDaoJDBC {

    public void saveToDB(String emailAddress, String password) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {Connection connection = getConnection();
              stmt = conn.prepareStatement("INSERT INTO \"user\" (title, id, status) " +
                     "VALUES (?,?,?);");
            stmt.setString(1, todo.title);
            stmt.setString(2, todo.id);
            stmt.setString(3,todo.status.toString());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (stmt != null) { stmt.close(); }
            }
            catch (Exception e) {
                // log this error
            }
            try {
                if (conn != null) { conn.close(); }
            }
            catch (Exception e) {
                // log this error
            }
        }


    Connection conn = null;
    PreparedStatement stmt = null;
       try {
               conn = getConnection();
               stmt = conn.prepareStatement("INSERT INTO todos (title, id, status) " +
               "VALUES (?,?,?);");
               stmt.setString(1, todo.title);
               stmt.setString(2, todo.id);
               stmt.setString(3,todo.status.toString());
               stmt.executeUpdate();
               } catch (SQLException e) {
               e.printStackTrace();
               }
               finally {
               try {
               if (stmt != null) { stmt.close(); }
               }
               catch (Exception e) {
               // log this error
               }
               try {
               if (conn != null) { conn.close(); }
               }
               catch (Exception e) {
               // log this error
               }
               }