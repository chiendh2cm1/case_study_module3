package com.codegym.dao;

import com.codegym.model.Product;

import java.sql.SQLException;
import java.util.List;

public interface IProductDao {
    public Product getProductDetail(String productId) ;

    public List<Product> selectAllProduct();

    public List<Product> getListProductByCategoryId(String categoryId) throws SQLException;

    public Product selectProduct(String productId) throws SQLException;

    public void createProduct(Product product) throws SQLException;

    public boolean updateProduct(Product product) throws SQLException;

    public boolean deleteProduct(String productId) throws SQLException;

    public List<Product> searchProduct(String searchName) throws SQLException;

}
