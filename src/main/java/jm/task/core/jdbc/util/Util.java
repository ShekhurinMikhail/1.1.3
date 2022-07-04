package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.*;
import java.util.Properties;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String URL = "jdbc:mysql://localhost:3306/mysql";
    private static final String user = "root";
    private static final String password = "root";
    private static Connection connection;
    private static SessionFactory sessionFactory;

    public static Connection getConnectionWithDb() {
        try {
            connection = DriverManager.getConnection(URL, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static SessionFactory getSessionFactory() {
        try {
            Properties prop = new Properties();
            prop.setProperty("hibernate.connection.url", URL);
            prop.setProperty("hibernate.connection.username", user);
            prop.setProperty("hibernate.connection.password", password);
            prop.setProperty("dialect", "org.hibernate.dialect.MySQLDialect");

            prop.setProperty("hibernate.hbm2ddl.auto", "update");

            sessionFactory = new Configuration()
                    .setProperties(prop)
                    .addAnnotatedClass(User.class)
                    .buildSessionFactory();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return sessionFactory;
    }
}
