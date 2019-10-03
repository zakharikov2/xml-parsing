package com.zakharikov.testtask;

import java.sql.*;
import java.util.Collection;
import java.util.Iterator;

public class DbConnection {

    public static final String DB_URL = "jdbc:h2:file:./db/dbConnection";
    public static final String DB_Driver = "org.h2.Driver";

    public static void createTable() {
        Connection connection;
        Statement statement;
        try {
            Class.forName(DB_Driver);
            connection = DriverManager.getConnection(DB_URL);
            statement = connection.createStatement();

            String SQL =
                    "DROP TABLE IF EXISTS doc_types;" +
                    "CREATE TABLE doc_types " +
                    "(id INTEGER not NULL, " +
                    " name VARCHAR(50), " +
                    " PRIMARY KEY (id))";

            statement.executeUpdate(SQL);
            statement.close();
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("JDBC драйвер для СУБД не найден!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Ошибка SQL !");
        }
    }

    public static void populateTable(Collection<String> data) {
        Connection connection;
        Statement statement;
        try {
            Class.forName(DB_Driver);
            connection = DriverManager.getConnection(DB_URL);
            statement = connection.createStatement();

            if (data.size() > 0) {
                StringBuilder sqlBuilder = new StringBuilder().append("INSERT INTO doc_types (id, name) VALUES ");
                int count = 0;
                for (Iterator i = data.iterator(); i.hasNext(); count++) {
                    sqlBuilder.append(String.format("(%d,'%s'),", count, i.next()));
                }
                int lastIndex = sqlBuilder.length() - 1;
                sqlBuilder.delete(lastIndex, lastIndex + 1).append(";");
                String SQL = sqlBuilder.toString();
                statement.executeUpdate(SQL);
            }

            statement.close();
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("JDBC драйвер для СУБД не найден!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Ошибка SQL !");
        }
    }
}
