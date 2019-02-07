package com.codecool.shop.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/*public class RegistrationDaoJDBC extends GeneralDaoJDBC {

    public void saveToDB(String emailAddress, String password) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            Connection connection = getConnection();
            stmt = conn.prepareStatement("INSERT INTO \"user\" (name, password) " +
                    "VALUES (?,?);");
            stmt.setString(1, emailAddress);
            stmt.setString(2, password);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (Exception e) {
                // log this error
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                // log this error
            }
        }
    }
}
*/
