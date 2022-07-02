package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String URL = "jdbc:mysql://localhost:3306/mysql";
    private static final String user = "root";
    private static final String password = "root";
    private static Connection connection;

    public static Connection getConnectionWithDb() {
        try {
            connection = DriverManager.getConnection(URL, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
