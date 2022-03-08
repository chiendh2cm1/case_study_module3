package com.codegym.controller;
import com.codegym.model.Order;
import com.codegym.model.Orderdetail;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CartUpdateServlet", urlPatterns = "/cartUpdateServlet")
public class CartEditServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null)
            action = "";

        switch (action) {
            case "Update": {
                updateCart(request, response);
                break;
            }
        }
    }
    private void updateCart(HttpServletRequest request, HttpServletResponse response) {
        String[] quantity = request.getParameterValues("quantity");
            HttpSession session = request.getSession();
            Order order = (Order) session.getAttribute("order");
            List<Orderdetail> list = order.getOrderdetails();

            for (int i = 0; i < list.size(); i++) {
                list.get(i).setQuantity(Integer.parseInt(quantity[i]));
            }

            session.removeAttribute("order");
            session.setAttribute("order", order);

            RequestDispatcher requestDispatcher = request.getRequestDispatcher("cart.jsp");
            try {
                requestDispatcher.forward(request, response);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
        }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        System.out.println(action);
        if (action == null)
            action = "";

        switch (action) {
            case "Delete": {
                deleteProduct(request, response);
                break;
            }
        }
    }
    private void deleteProduct(HttpServletRequest request, HttpServletResponse response) {
        String productId = request.getParameter("productId");

        HttpSession session = request.getSession();
        Order order = (Order) session.getAttribute("order");
        List<Orderdetail> list = order.getOrderdetails();

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getProduct().getProductId().equals(productId)){
                list.remove(i);
            }
        }

        session.removeAttribute("order");
        session.setAttribute("order", order);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("cart.jsp");
        try {
            requestDispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}
