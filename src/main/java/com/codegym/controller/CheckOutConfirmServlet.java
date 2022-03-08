package com.codegym.controller;

import com.codegym.dao.OrderDao;
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
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "CheckOutConfirmServlet", urlPatterns = "/checkoutconfirm")
public class CheckOutConfirmServlet extends HttpServlet {
    OrderDao orderDao = new OrderDao();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderId = request.getParameter("orderId");
        String accountId = request.getParameter("accountId");
        String receiver = request.getParameter("receiver");
        String phoneNumber = request.getParameter("phoneNumber");
        String address = request.getParameter("address");
        String email = request.getParameter("email");

        HttpSession session = request.getSession();
        Order orderSession = (Order) session.getAttribute("order");
        List<Orderdetail> list = orderSession.getOrderdetails();

        Order order = new Order(orderId, accountId, receiver, address, email,phoneNumber);
        orderDao.addOrderFromCart(order);
        for (int i = 0; i < list.size(); i++) {
            String productId = list.get(i).getProduct().getProductId();
            String quantity = String.valueOf(list.get(i).getQuantity());
            String priceEach = String.valueOf(list.get(i).getPrice());
            orderDao.addOrderProductFromCart(orderId,productId,Integer.parseInt(quantity),Float.parseFloat(priceEach),accountId);
        }

        List<Integer> orderQuantities = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            orderQuantities.add(list.get(i).getQuantity());
        }

        List<Integer> quantityInStock = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            quantityInStock.add(list.get(i).getProduct().getQuantityInStock());
        }

        for (int i = 0; i < quantityInStock.size(); i++) {
            quantityInStock.set(i,(quantityInStock.get(i)-orderQuantities.get(i)));
        }

        for (int i = 0; i < quantityInStock.size(); i++) {
            orderDao.updateQuantityProduct(quantityInStock.get(i),list.get(i).getProduct().getProductId());
        }


        String announcementOrderSuccessful = "Order Completed ! Please wait Admin to Confirm";
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("cart.jsp");
        request.setAttribute("announcementOrderSuccessful",announcementOrderSuccessful);
        requestDispatcher.forward(request,response);
        session.removeAttribute("order");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
