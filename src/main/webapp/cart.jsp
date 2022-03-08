<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="p" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Title</title>
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
<h3 style="color: blue; width: 1500px; text-align: center">${announcementOrderSuccessful}</h3>
<section class="cart-total-page spad">
    <div class="container">
        <form action="/cartUpdateServlet?action=Update" class="checkout-form" method="post">

            <div class="checkout-form-row" style="margin-bottom: 0px">
                <div class="col-lg-12">
                    <h3 style="margin-top: 50px; margin-bottom: 30px;">Your Cart</h3>
                </div>
                <div class="orderedListTable">
                    <table class="table table-hover" style="margin-bottom: 20px">
                        <thead>
                        <tr>
                            <th scope="col">Product ID</th>
                            <th scope="col">Ordered Product</th>
                            <th scope="col">Price</th>
                            <th scope="col">Quantity</th>
                            <th scope="col">Amount</th>
                            <th scope="col"><input class="btn btn-outline-success" type="submit" value="Update"></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${order.orderdetails}" var="orderdetail">
                            <tr>
                                <td>${orderdetail.product.productId}</td>
                                <td>${orderdetail.product.productName}</td>
                                <td><p:formatNumber value="${orderdetail.price}"></p:formatNumber>₫</td>
                                <td style="width: 80px; height: 20px;">
                                    <i class="qty mt-5" style="height: 30px;">
                                        <input style="width: 54px;height: 20px;" type="number" class="count"
                                               name="quantity" value="${orderdetail.quantity}">
                                    </i>
                                </td>
                                <td><p:formatNumber
                                        value="${orderdetail.price * orderdetail.quantity}"></p:formatNumber>₫
                                </td>
                                <td>
                                    <a href="/cartUpdateServlet?action=Delete&productId=${orderdetail.product.productId}"><input
                                            name="act" class="btn btn-outline-danger" type="button"
                                            value="Delete"></a></td>
                            </tr>
                        </c:forEach>
                        <tr>
                            <td colspan="4">Total :</td>
                            <td>
                                <c:set var="totalOrder" value="${0}"/>
                                <c:forEach var="orderdetail" items="${order.orderdetails}">
                                    <c:set var="totalOrder"
                                           value="${totalOrder + orderdetail.price* orderdetail.quantity}"/>
                                </c:forEach>
                                <p:formatNumber value="${totalOrder}"></p:formatNumber>₫

                            </td>
                        </tr>
                        <tr>
                            <td colspan="4">Delivery Cost (2%) :</td>
                            <td><p:formatNumber value="${totalOrder*2/100}"></p:formatNumber>₫</td>
                        </tr>
                        <tr>
                            <td colspan="4">Final :</td>
                            <td><p:formatNumber value="${totalOrder*102/100}"></p:formatNumber>₫</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <a style="float: right; margin-bottom: 40px" href="/pagination" class="btn btn-outline-info"
                   id="backToMain">Tiếp tục mua hàng</a>
            </div>
        </form>

        <div class="checkout-form-row">
            <div class="col-lg-12">
                <h3 style="margin-bottom: 40px">Your Information (to Order)</h3>
            </div>
            <div>
                <form action="/checkoutcart" method="post" class="deliveryInformation">
                    <a style="margin-left: 25px" class="text-primary" href="/checkoutcart">Click here to Use User's
                        default information</a>
                    <b><i style="margin-left: 25px; color: red">${announcementToLogin}${announcementToFillFields}</i></b>
                    <table class="table table-hover"
                           style="margin-left: 20px; width: 1060px; margin-bottom: 50px; margin-top: 10px">
                        <tr>
                            <th>Your Name*</th>
                            <td><input style="margin: 0; padding: 0; border: 0.5px solid lightgrey" name="fullName"
                                       type="text" placeholder="Full Name" value="${defaultAccountName}"></td>
                        </tr>
                        <tr>
                            <th>Your Email*</th>
                            <td><input style="margin: 0; padding: 0; border: 0.5px solid lightgrey" name="email"
                                       type="text" value="example@gmail.com"><i style="color:red;">${defaultEmail}</i>
                            </td>
                        </tr>
                        <tr>
                            <th>Your Phone Number*</th>
                            <td><input style="margin: 0; padding: 0; border: 0.5px solid lightgrey" name="phoneNumber"
                                       type="text" value="${defaultPhoneNumber}"></td>
                        </tr>
                        <tr>
                            <th>Your Address*</th>
                            <td><input style="margin: 0; padding: 0; border: 0.5px solid lightgrey" name="address"
                                       type="text" value="${defaultAddress}"></td>
                        </tr>
                        <tr>
                            <th>Payment Method</th>
                            <td><input style="margin: 0; padding: 0; border: 0.5px solid lightgrey" type="text"
                                       value=" Cash on Delivery" disabled></td>
                        </tr>
                        <tr>
                            <th>Delivery Date</th>
                            <td><input style="margin: 0; padding: 0; border: 0.5px solid lightgrey" type="text"
                                       value=" 3-5 working days " disabled></td>
                        </tr>
                        <tr>
                            <th></th>
                            <td colspan="3" style="width: 40px;"><input
                                    style="margin: 0; padding: 0; border-bottom: 1px solid"
                                    class="btn btn-outline-primary" type="submit" value="Order"></td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
    </div>
</section>
<style>
    .deliveryInformation table th {
        width: 150px;
    }

    .deliveryInformation table td {
        width: 500px;
        height: 20px;
    }

    .deliveryInformation table input {
        width: 500px;
        height: 35px;
    }

</style>

<%--FOOTER--%>
<jsp:include page="footer.jsp"></jsp:include>


</body>
</html>
