<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="p" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>ATAG.VN</title>
    <link rel="stylesheet" href="boostrap/css/mainStyle.css">
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

</head>
<body>

<%--HEADER--%>
<jsp:include page="header.jsp"></jsp:include>
<jsp:include page="header-infor.jsp"></jsp:include>

<%--BODY--%>
<div class="container">
    <form action="/checkoutconfirm" method="post">
        <table class="table table-hover">
            <tr>
                <td colspan="2"><h3><b>PRODUCT ORDER INFORMATION</b></h3></td>
            </tr>
            <tr>
                <th style="width: 250px;"><b>OrderID</b></th>
                <td><input type="hidden" name="orderId" value="${orderId}">${orderId}</td>
            </tr>
            <c:forEach items="${list}" var="list">
                <tr>
                    <th><b>ProductID</b></th>
                    <td><input type="hidden" name="productId"
                               value="${list.getProduct().getProductId()}">${list.getProduct().getProductId()}</td>
                </tr>
                <tr>
                    <th><b>ProductName</b></th>
                    <td><input type="hidden" name="productName"
                               value="${list.getProduct().getProductName()}">${list.getProduct().getProductName()}</td>
                </tr>
                <tr>
                    <th><b>Quantity</b></th>
                    <td><input type="hidden" name="quantity" value="${list.getQuantity()}">${list.getQuantity()}</td>
                </tr>
                <tr>
                    <th><b>PriceEach</b></th>
                    <td><input type="hidden" name="priceEach"productIdproductNamepriceEachqty
                               value="${list.getPrice()}"><p:formatNumber>${list.getPrice()}</p:formatNumber>₫
                    </td>
                </tr>
                <tr>
                    <td><b>Amount (Included 2% Delivery Cost)</b></td>
                    <td><p:formatNumber>${list.getQuantity()*list.getPrice()*102/100}</p:formatNumber>₫</td>
                </tr>
            </c:forEach>

            <tr>
                <td><b>AccountID</b></td>
                <td><input type="hidden" name="accountId" value="${accountId}">${accountId}</td>
            </tr>
            <tr>
                <th style="width: 250px;"><b>Account Name</b></th>
                <td><input type="hidden" name="accountName" value="${accountName}">${accountName}</td>
            </tr>
            <tr>
                <td><b>OrderDate</b></td>
                <td><input type="hidden" name="orderDate" value="${orderDate}">${orderDate}</td>
            </tr>
            <tr>
                <td><b>Receiver</b></td>
                <td><input type="hidden" name="receiver" value="${receiver}">${receiver}</td>
            </tr>
            <tr>
                <td><b>Address</b></td>
                <td><input type="hidden" name="address" value="${address}">${address}</td>
            </tr>
            <tr>
                <td><b>PhoneNumber</b></td>
                <td><input type="hidden" name="phoneNumber" value="${phoneNumber}">${phoneNumber}</td>
            </tr>
            <tr>
                <td><b>Email</b></td>
                <td><input type="hidden" name="email" value="${email}">${email}</td>
            </tr>
            <tr>
                <td><a href="cart.jsp"><input class="btn btn-outline-danger" type="button" value="Back To Cart"></a>
                </td>
                <td><input class="btn btn-outline-primary" type="submit" value="Confirm"></td>
            </tr>
        </table>
    </form>
</div>


<%--FOOTER--%>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
