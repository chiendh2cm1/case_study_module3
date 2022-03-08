package com.codegym.dao;
import com.codegym.connection.DBConnect;
import com.codegym.model.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDao implements IOrderDao {

    public static final String SELECT_ALL_ORDER = "select * from chiendemo.orders;";
    public static final String GET_MAX_ORDER_ID_BY_ID_HEAD = "select * from chiendemo.orders where OrderID like ? order by OrderID desc limit 1;";
    public static final String ADD_NEW_ORDER_FROM_CART = "insert into chiendemo.orders(OrderID, AccountID, Receiver, Address, Email, PhoneNumber) value (?,?,?,?,?,?);";
    private static final String ADD_NEW_ORDER_PRODUCT_FROM_CART = "insert into chiendemo.orderdetail value (?,?,?,?,?);";
    public static final String UPDATE_QUANTITY_AFTER_ORDER = "update product set QuantityInStock = ? where ProductID=?";
    private static final String SELECT_BY_ID = "select * from chiendemo.orders where orderID = ?;";
    private static final String UPDATE_ORDER_BY_ID = "update chiendemo.orders set AccountID=?, OrderDate=?, Receiver=?, Address=?, Email=?, PhoneNumber=?, Status = ? where OrderID = ?;";


    @Override
    public List<Order> getRecentOrderList() {
        List<Order> orderList = new ArrayList<>();
        Connection connection = DBConnect.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ORDER);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Order order = new Order();
                String orderId = resultSet.getString("OrderID");
                String accountId = resultSet.getString("AccountID");
                String receiver = resultSet.getString("Receiver");
                String address = resultSet.getString("Address");
                String email = resultSet.getString("Email");
                String phoneNumber = resultSet.getString("PhoneNumber");

                orderList.add(new Order(orderId,accountId,receiver,address,email,phoneNumber));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return orderList;
    }

    @Override
    public String getMaxOrderIdByOrderIdHead(String orderIdHead) {
        String maxOrderIdByOrderIdHead = "";
        Connection connection = DBConnect.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_MAX_ORDER_ID_BY_ID_HEAD);
            preparedStatement.setString(1,orderIdHead+"%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                maxOrderIdByOrderIdHead = resultSet.getString("OrderID");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return maxOrderIdByOrderIdHead;
    }

    @Override
    public void addOrderFromCart(Order order) {
        Connection connection = DBConnect.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_NEW_ORDER_FROM_CART);
            preparedStatement.setString(1,order.getOrderID());
            preparedStatement.setString(2,order.getAccountID());
            preparedStatement.setString(3,order.getReceiver());
            preparedStatement.setString(4,order.getAddress());
            preparedStatement.setString(5,order.getEmail());
            preparedStatement.setString(6,order.getPhoneNumber());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void addOrderProductFromCart(String orderId, String productId, int quantity, float priceEach, String accountId) {
        Connection connection = DBConnect.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_NEW_ORDER_PRODUCT_FROM_CART);
            preparedStatement.setString(1,orderId);
            preparedStatement.setString(2,productId);
            preparedStatement.setString(3,String.valueOf(quantity));
            preparedStatement.setString(4,String.valueOf(priceEach));
            preparedStatement.setString(5,accountId);

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void updateQuantityProduct(int quantity, String productId) {
        Connection connection = DBConnect.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUANTITY_AFTER_ORDER);
            preparedStatement.setInt(1, quantity);
            preparedStatement.setString(2, productId);

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    @Override
    public List<Order> viewAllOrder() {
        List<Order> orders = new ArrayList<>();
        String orderID = "";
        String accountID = "";
        Date orderDate;
        String receiver = "";
        String address = "";
        String email = "";
        String phoneNumber = "";
        int status = 0;
        try {
            Connection connection = DBConnect.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ORDER);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                orderID = rs.getString(1);
                accountID = rs.getString(2);
                orderDate = rs.getDate(3);
                receiver = rs.getString(4);
                address = rs.getString(5);
                email = rs.getString(6);
                phoneNumber = rs.getString(7);
                status = rs.getInt(8);
                orders.add(new Order(orderID, accountID, orderDate, receiver, address, email, phoneNumber, status));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    @Override
    public Order findByID(String orderIDToFind) {
        Connection connection = DBConnect.getConnection();
        Order order = null;
        try {
            PreparedStatement ps = connection.prepareStatement(SELECT_BY_ID);
            ps.setString(1, orderIDToFind);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                String orderID = resultSet.getString("OrderID");
                String accountID = resultSet.getString("AccountID");
                Date orderDate = resultSet.getDate("OrderDate");
                String receiver = resultSet.getString("Receiver");
                String address = resultSet.getString("Address");
                String email = resultSet.getString("Email");
                String phoneNumber = resultSet.getString("PhoneNumber");
                Integer status = resultSet.getInt("Status");

                order = new Order(orderID, accountID, orderDate, receiver, address, email, phoneNumber, status);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }


    @Override
    public void updateOder(String orderID, String accountID, String orderDate, String receiver, String address, String email, String phoneNumber, int status) {
        Connection connection = DBConnect.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(UPDATE_ORDER_BY_ID);
            ps.setString(1, accountID);
            ps.setString(2, orderDate);
            ps.setString(3, receiver);
            ps.setString(4, address);
            ps.setString(5, email);
            ps.setString(6, phoneNumber);
            ps.setInt(7, status);
            ps.setString(8, orderID);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteOder(String orderID) {
        Connection connection = DBConnect.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("delete from atagvn.orders where OrderID = ?");
            ps.setString(1, orderID);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteOder_product(String orderID) {
        Connection connection = DBConnect.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("delete from atagvn.order_product where OrderID = ?");
            ps.setString(1, orderID);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
