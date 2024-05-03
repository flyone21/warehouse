package com.sebastian.repositories;

import com.sebastian.exceptions.WarehouseException;
import com.sebastian.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private final Connection connection;

    @Autowired
    public ProductRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void saveProduct(Product product) {

        String query = "INSERT INTO products (id, name, price, quantity) VALUES (?, ?, ?, ?)";

        try(PreparedStatement crateStmt = connection.prepareStatement(query)) {

            crateStmt.setInt(1, product.getId());
            crateStmt.setString(2, product.getName());
            crateStmt.setDouble(3, product.getPrice());
            crateStmt.setInt(4, product.getQuantity());

            crateStmt.execute();

        } catch (SQLException e) {
            throw new WarehouseException("SQL exception",e);
        }

    }

    @Override
    public Product getProductById(int id) {
        String query = "SELECT * FROM products WHERE id = ?";

        try (PreparedStatement findStmt = connection.prepareStatement(query)) {
            findStmt.setInt(1, id);

            ResultSet rs = findStmt.executeQuery();

            while (rs.next()){
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getDouble("price"));
                product.setQuantity(rs.getInt("quantity"));

                return product;
            }

        } catch (SQLException e) {
            throw new WarehouseException("SQL exception",e);
        }

        return null;
    }

    @Override
    public Product getProductByName(String name) {
        String query = "SELECT * FROM products WHERE name = ?";

        try (PreparedStatement findStmt = connection.prepareStatement(query)){


            findStmt.setString(1, name);
            ResultSet rs = findStmt.executeQuery();
            while (rs.next()){
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getDouble("price"));
                product.setQuantity(rs.getInt("quantity"));

                return product;
            }


        } catch (SQLException e) {
            throw new WarehouseException("SQL exception",e);
        }

        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM products";

        try(PreparedStatement findAllStmt = connection.prepareStatement(query)) {

             ResultSet rs = findAllStmt.executeQuery();

             while (rs.next()){
              Product product = new Product();
              product.setId(rs.getInt("id"));
              product.setName(rs.getString("name"));
              product.setPrice(rs.getDouble("price"));
              product.setQuantity(rs.getInt("quantity"));
              products.add(product);

             }

        } catch (SQLException e) {
           throw new WarehouseException("SQL exception",e);
        }

        return products;
    }

    @Override
    public int updateProductPrice(String productName, double price) {

        String query = "UPDATE products SET price = ? WHERE name = ?";

        try(PreparedStatement updateStmt = connection.prepareStatement(query)) {


            updateStmt.setDouble(1, price);
            updateStmt.setString(2, productName);

            return updateStmt.executeUpdate();

        } catch (SQLException e) {
            throw new WarehouseException("SQL exception", e);
        }
    }

    @Override
    public int updateProductQuantity(String productName, int quantity) {
        String query = "UPDATE products SET quantity = quantity + ? WHERE name = ?";

        try(PreparedStatement updateStmt = connection.prepareStatement(query)) {
            updateStmt.setInt(1, quantity);
            updateStmt.setString(2, productName);

            return updateStmt.executeUpdate();

        } catch (SQLException e) {
            throw new WarehouseException("SQL exception", e);
        }
    }

    @Override
    public int deleteProductById(int id) {
        String query = "DELETE FROM products WHERE id = ?";

        try (PreparedStatement updateStmt = connection.prepareStatement(query)) {
            updateStmt.setInt(1, id);

            return updateStmt.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
