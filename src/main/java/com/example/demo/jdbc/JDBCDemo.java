package com.example.demo.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCDemo {
    public static void main(String[] args) {
        Connection connection = DBUtil.getConnection();
        try {
            String sql = "select id,name from legends where id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, 1);
            ResultSet resultset = statement.executeQuery();
            while (resultset.next()){
                System.out.println(resultset.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}