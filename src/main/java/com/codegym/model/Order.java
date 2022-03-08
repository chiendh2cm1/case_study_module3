package com.codegym.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

public class Order implements Serializable {
    private int id;
    private Account customer;
    private List<Orderdetail> orderdetails;

    private String orderID;
    private String accountID;
    private Date orderDate;
    private String receiver;
    private String address;
    private String email;
    private String phoneNumber;
    private int status;

    public Order(String orderID, String accountID, String receiver, String address, String email, String phoneNumber) {
        this.orderID = orderID;
        this.accountID = accountID;
        this.receiver = receiver;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public Order(String orderID, String accountID, Date orderDate, String receiver, String address, String email, String phoneNumber, int status) {
        this.orderID = orderID;
        this.accountID = accountID;
        this.orderDate = orderDate;
        this.receiver = receiver;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.status = status;
    }


    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Order() {
        this.status = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Account getCustomer() {
        return customer;
    }

    public void setCustomer(Account customer) {
        this.customer = customer;
    }

    public List<Orderdetail> getOrderdetails() {
        return orderdetails;
    }

    public void setOrderdetails(List<Orderdetail> orderdetails) {
        this.orderdetails = orderdetails;
    }
}
