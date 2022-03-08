package com.codegym.controller;

import com.codegym.dao.ProductDao;
import com.codegym.model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@MultipartConfig
@WebServlet(name = "ProductDetailServlet", urlPatterns = "/detail")
public class ProductDetailServlet extends HttpServlet {
    ProductDao productServiceImp = new ProductDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("productId");
        Product product = null;
        try {
            product = productServiceImp.selectProduct(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("detail", product);
            request.getRequestDispatcher("productDetails.jsp").forward(request, response);
    }
}
