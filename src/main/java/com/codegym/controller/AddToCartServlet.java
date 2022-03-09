package com.codegym.controller;

import com.codegym.dao.ProductDao;
import com.codegym.model.Order;
import com.codegym.model.Orderdetail;
import com.codegym.model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "AddtoCartServlet", urlPatterns = "/addToCartServlet")
public class AddToCartServlet extends HttpServlet {
    private ProductDao productDao;
    public void init() throws ServletException {
        productDao = new ProductDao();
        super.init();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
        super.doPost(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        int quantity = 1;
        String id;
        if (request.getParameter("productId") != null) {
            id = request.getParameter("productId");
            try {
                Product product = productDao.selectProduct(id);
                if (product != null) {
                    if (request.getParameter("quantity") != null ){
                        quantity = Integer.parseInt(request.getParameter("quantity"));
                    }
                    HttpSession session =request.getSession();
                    if (session.getAttribute("order") == null) {
                        Order order = new Order();
                        List<Orderdetail> orderdetailList = new ArrayList<>();
                       Orderdetail orderdetail = new Orderdetail();
                        orderdetail.setQuantity(quantity);
                        orderdetail.setProduct(product);
                        orderdetail.setPrice(product.getProductPrice());
                        orderdetailList.add(orderdetail);
                        order.setOrderdetails(orderdetailList);
                        session.setAttribute("order", order);
                    } else {
                        Order order = (Order) session.getAttribute("order");
                        List<Orderdetail> orderdetailList = order.getOrderdetails();
                        boolean check = false;
                        for (Orderdetail orderdetail: orderdetailList) {
                            if (orderdetail.getProduct().getProductId().equals(product.getProductId())) {
                                orderdetail.setQuantity(orderdetail.getQuantity() + quantity);
                                check = true;
                            }
                        }
                        if (!check) {
                            Orderdetail orderdetail = new Orderdetail();
                            orderdetail.setQuantity(quantity);
                            orderdetail.setProduct(product);
                            orderdetail.setPrice(product.getProductPrice());
                            orderdetailList.add(orderdetail);
                        }
                        session.setAttribute("order", order);
                    }
                    response.sendRedirect(request.getContextPath()+"/pagination");
                }else {
                    response.sendRedirect(request.getContextPath()+"/pagination");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
