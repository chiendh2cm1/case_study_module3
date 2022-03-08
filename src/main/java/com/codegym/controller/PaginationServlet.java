package com.codegym.controller;

import com.codegym.dao.ProductDao;
import com.codegym.model.Product;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

@WebServlet(name = "PaginationServlet", urlPatterns = "/pagination")
public class PaginationServlet extends HttpServlet {
    public static final int PRODUCT_QUANTITY_PER_PAGE = 6;
    ProductDao productServiceImp = new ProductDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        boolean checkSessionLoginExist = false;
        boolean checkSessionOrderList = false;

        Enumeration keyCheckOrderList = session.getAttributeNames();
        while (keyCheckOrderList.hasMoreElements()) {
            String key = (String) keyCheckOrderList.nextElement();
            if (key.equals("order")) {
                checkSessionOrderList = true;
            }
        }

        if (!checkSessionOrderList) {
            String noHaveOrder = "not yet";
            session.setAttribute("haveOrder", noHaveOrder);
        } else {
            String alreadyHaveOrder = "ok";
            session.removeAttribute("haveOrder");
            session.setAttribute("haveOrder", alreadyHaveOrder);
        }

        Enumeration keyCheckLogin = session.getAttributeNames();
        while (keyCheckLogin.hasMoreElements()) {
            String key = (String) keyCheckLogin.nextElement();
            if (key.equals("cookieIsLogin")) {
                checkSessionLoginExist = true;
            }
        }

        if (!checkSessionLoginExist) {
            String isLogined = "not yet";
            session.setAttribute("cookieIsLogin", isLogined);
        }
        String page = request.getParameter("page");
        int pageNumber;
        if (page == null) {
            pageNumber = 1;
        } else {
            pageNumber = Integer.parseInt(request.getParameter("page"));
        }
        int perPage = 6;
        List<Product> productList = null;
        List<Product> subList = null;

        productList = productServiceImp.selectAllProduct();

        int start = (pageNumber - 1) * perPage;
        int lastNumber = productList.size();
        int end;
        if (start < lastNumber - PRODUCT_QUANTITY_PER_PAGE) {
            end = start + PRODUCT_QUANTITY_PER_PAGE;
        } else {
            end = start + (lastNumber-start);
        }


        subList = productList.subList(start, end);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp?page=1");
        request.setAttribute("subList", subList);
        requestDispatcher.forward(request, response);
    }
}
