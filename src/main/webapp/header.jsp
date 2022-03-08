<%@ page import="com.codegym.dao.CategoryDao" %>
<%@ page import="com.codegym.model.Category" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>ATAG.VN</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
            integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
            crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css"
          integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
    <link rel="stylesheet" href="boostrap/css/bootstrap.css">
    <link rel="stylesheet" href="boostrap/css/mainStyle.css">
</head>
<body>
<%
    CategoryDao categoryServiceImp = new CategoryDao();
%>
<header class="header-section">
    <div class="container-fluid">
        <div class="inner-header">
            <div class="logo">
                <a href="/pagination"><img src="img/LOGO1.png" alt="logo"></a>
            </div>
            <div class="header-right">
                <a href="search.jsp"><i class="fa fa-search"></i></a>
                <i class="fa fa-user">&nbsp${sessionScope.acc.accountName}</i>
                <a href="cart.jsp">
                    <i class="fa fa-shopping-bag"></i>
                </a>
            </div>
            <div class="user-access">
                <a href="/signUp.jsp">Register</a>
                <a href="/login.jsp" > &nbsp;&nbsp;&nbsp;Login</a>
            </div>
            <nav class="main-menu mobile-menu">
                <ul>
                    <li><a style="font-size: 20px" class="active" href="/pagination">Home</a></li>
                    <li><a style="font-size: 20px" href="">Category</a>
                        <ul class="sub-menu">
                            <%
                                for (Category category : categoryServiceImp.getListCategory()) {
                            %>
                            <li>
                                <a href="/product?action=showProducByCategory&categoryId=<%=category.getCategoryId()%>"><%=category.getCategoryName()%>
                                </a></li>
                            <%
                                }
                            %>
                        </ul>
                    </li>
                    <li><a style="font-size: 20px" href="#">Blog</a>
                    </li>
                    <li><a style="font-size: 20px" href="">Contact</a>
                        <ul class="sub-menu">
                            <li><a href="">Phone : (024).0000-0000</a></li>
                            <li><a href="">Email : cskh@atag.vn</a></li>
                        </ul>
                    </li>
                </ul>
            </nav>
        </div>
    </div>
</header>
<style>
    .inner-header .user-access a.in::before {
        position: absolute;
        left: -13px;
        top: 1px;
        color: #1e1e1e;
        content: "";
    }

    .inner-header .user-access a {
        color: #1e1e1e;
        font-size: 16px;
        display: inline-block;
        font-weight: 500;
        position: relative;
        line-height: 42px;
        margin-left: 0;
        margin-right: 0;
    }

</style>
</body>
</html>
