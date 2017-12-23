package com.djr.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/bbs";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    private static Connection conn = null;

    public static Connection getConn(){
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL,USER,PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void closeConn(Connection conn){
        if(conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
