package com.revature.daodemo;

import com.revature.daodemo.DAO.JdbcProductDAO;
import com.revature.daodemo.model.Product;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Launcher {
    public static void main(String[] args) throws SQLException
            {
        String url = "jdbc:sqlite:week4_jdbc_dao_demo.db";

        try (Connection conn = DriverManager.getConnection(url)) {

            //open database connection
            try (Statement st = conn.createStatement()) {

                st.executeUpdate("DROP TABLE IF EXISTS product");

                st.executeUpdate("""
                    CREATE TABLE product (
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        sku TEXT NOT NULL UNIQUE,
                        name TEXT NOT NULL,
                        price REAL NOT NULL
                    )

                    """);
            }
            JdbcProductDAO dao = new JdbcProductDAO(conn);

            Product p = new Product(0,"SKU-1","Mug",12.5);

            long id = dao.insert(p);
            System.out.println("inserted id=" + id);

            List<Product> products = dao.findAll();

            for(Product p1: products){
                System.out.println(p1);
            }

        }
    }
}
