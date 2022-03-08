package com.codegym.dao;

import com.codegym.connection.DBConnect;
import com.codegym.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDao implements IProductDao {
    private static final String SELECT_ALL_PRODUCT_BY_PRODUCTID = "select * from chiendemo.product where ProductID = ?";
    private static final String SELECT_ALL_PRODUCT = "select * from chiendemo.product";
    private static final String SELECT_PRODUCT_BY_PRODUCTID = "select * from chiendemo.product where ProductID = ?";
    private static final String UPDATE_PRODUCT = "UPDATE chiendemo.product SET ProductName=?, ProductPrice=?,QuantityInStock=?,Status=?,Description = ? WHERE ProductID = ?";
    private static final String CREATE_PRODUCT = "INSERT INTO atagvn.product(ProductID,CategoryID,ProductName,ProductPrice,QuantityInStock,Image,Status,Description) VALUES (?,?,?,?,?,?,?,?);";
    public static final String SELECT_PRODUCT_BY_CATEGORYID = "select * from chiendemo.product where CategoryID = ?";
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    @Override
    public Product getProductDetail(String productId) {
        conn = DBConnect.getConnection();
        Product product = new Product();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(SELECT_ALL_PRODUCT_BY_PRODUCTID);
            rs = preparedStatement.executeQuery();
            product.setProductId(rs.getString("ProductID"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    public List<Product> selectAllProduct() {
        ArrayList<Product> products = new ArrayList<>();
        try {
            conn = DBConnect.getConnection();
            ps = conn.prepareStatement(SELECT_ALL_PRODUCT);
            rs = ps.executeQuery();
            while (rs.next()) {
                products.add(new Product(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getFloat(4),
                        rs.getInt(5),
                        rs.getString(6),
                        rs.getInt(7),
                        rs.getString(8)));
            }
        } catch (Exception e) {
        }
        return products;
    }

    @Override
    public List<Product> getListProductByCategoryId(String categoryId) throws SQLException {
        ArrayList<Product> products = new ArrayList<>();
        try {
            conn = DBConnect.getConnection();
            ps = conn.prepareStatement(SELECT_PRODUCT_BY_CATEGORYID);
            ps.setString(1,categoryId);
            rs = ps.executeQuery();
            while (rs.next()) {
                String productId = rs.getString(1);
                String cateId =rs.getString(2);
                String productName = rs.getString(3);
                float  productPrice =rs.getFloat(4);
                int quantityInStock = rs.getInt(5);
                String image = rs.getString(6);
                int status = rs.getInt(7);
                String description = rs.getString(8);
                products.add(new Product(productId,cateId,productName,productPrice,quantityInStock,image,status,description));
            }
        } catch (Exception e) {
        }

        return products;
    }

    public Product selectProduct(String productId) throws SQLException {
        try {
            conn = DBConnect.getConnection();
            ps = conn.prepareStatement(SELECT_PRODUCT_BY_PRODUCTID);
            ps.setString(1,productId);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Product(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getFloat(4),
                        rs.getInt(5),
                        rs.getString(6),
                        rs.getInt(7),
                        rs.getString(8));
            }
        } catch (SQLException e) {
        }
        return null;
    }

    @Override
    public void createProduct(Product product) throws SQLException {
        try {
            conn = DBConnect.getConnection();
            ps = conn.prepareStatement(CREATE_PRODUCT);
            ps.setString(1, product.getProductId());
            ps.setString(2, product.getCategoryId());
            ps.setString(3, product.getProductName());
            ps.setFloat(4, product.getProductPrice());
            ps.setInt(5, product.getQuantityInStock());
            ps.setString(6, product.getImage());
            ps.setInt(7, product.getStatus());
            ps.setString(8, product.getDescription());
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    @Override
    public boolean updateProduct(Product product) {
        try {
            conn = DBConnect.getConnection();
            ps = conn.prepareStatement(UPDATE_PRODUCT);
            ps.setString(1, product.getProductName());
            ps.setFloat(2, product.getProductPrice());
            ps.setInt(3, product.getQuantityInStock());
            ps.setInt(4, product.getStatus());
            ps.setString(5, product.getDescription());
            ps.setString(6, product.getProductId());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {

        }
        return false;
    }

    @Override
    public boolean deleteProduct(String productId) throws SQLException {
        return false;
    }

    @Override
    public List<Product> searchProduct(String searchName) throws SQLException {
        return null;
    }


}
