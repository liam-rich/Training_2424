package com.revature.daodemo.DAO;

import com.revature.daodemo.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcProductDAO implements ProductDAO{
    private final Connection connection;

    public JdbcProductDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public long insert(Product product) throws SQLException {
        String sql =
                "INSERT INTO product (sku,name,price) VALUES (?,?,?)";

        try(PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            ps.setString(1,product.getSku());
            ps.setString(2,product.getName());
            ps.setDouble(3,product.getPrice());
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()){
                if(keys.next()){
                    return keys.getLong(1);
                }
            } catch (IllegalStateException k){
                k.printStackTrace();
            }
        }
        return 0;
    }

    @Override
    public Optional<Product> findBySku(String sku) throws Exception {
        return Optional.empty();
    }

    @Override
    public void updatePrice(String sku, double newPrice) throws SQLException {

    }

    @Override
    public void deleteBySku(String sku) throws SQLException {

    }

    @Override
    public List<Product> findAll() throws SQLException {
        String sql = "SELECT id,sku, name, price FROM product ORDER by id";
        List<Product> out = new ArrayList<>();
        try(PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()){
            while(rs.next()){
                out.add(mapRow(rs));
            }
        }
        return out;
    }

    private Product mapRow(ResultSet rs) throws SQLException {
        return new Product(
                rs.getLong("id"),
                rs.getString("sku"),
                rs.getString("name"),
                rs.getDouble("price")
        );
    }
}
