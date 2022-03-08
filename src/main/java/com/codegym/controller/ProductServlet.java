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
import java.util.List;

@MultipartConfig
@WebServlet(name = "AddProductServlet", urlPatterns = "/product")
public class ProductServlet extends HttpServlet {
    ProductDao productServiceImp = new ProductDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        action(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        action(request, response);
    }

    private void action(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "showProducByCategory":
                showProductByCateId(request, response);
                break;
        }
        
    }

    private void showProductByCateId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String categoryId = request.getParameter("categoryId");
        List<Product> products = null;
        try {
            products = productServiceImp.getListProductByCategoryId(categoryId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("listP", products);
        request.getRequestDispatcher("showAllProduct.jsp").forward(request, response);
    }
}
