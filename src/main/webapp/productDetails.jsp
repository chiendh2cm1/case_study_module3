
<%@ page import="java.text.NumberFormat" %>
<%@ page import="com.codegym.dao.ProductDao" %>
<%@ page import="com.codegym.model.Product" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Tittle</title>
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
<%--HEADER--%>
<jsp:include page="header.jsp"></jsp:include>
<jsp:include page="header-infor.jsp"></jsp:include>

<section class="product-page"   style="padding-bottom: 100px;padding-top: 100px">
    <div class="container-fluid">
        <br>
        <div class="row">
            <div class="col-lg-6 text-right">
                <img  style="width: 500px; !important; height: 500px; !important;" src="${detail.image}">
            </div>
            <div class="col-lg-6">
                <div class="product-content">
                    <h2>${detail.productName}</h2>
                    <div class="price">
                        <h5 style="color:red; padding-bottom: 15px">${detail.productPrice}&nbsp;₫</h5>
                    </div>
                    <p class="description">
                        ${detail.description}
                    </p>
                    <table class="tags">
                        <tr>
                            <td>Tồn Kho :</td>
                            <td><input style="background-color: transparent; border-color: transparent" type="text" name="quantityInStock" disabled value="${detail.quantityInStock}"></td>
                        </tr>
                    </table>
                    <a style="width: 100px; !important; height: 50px; !important;" href="/addToCartServlet?productId=${detail.productId}" class="btn btn-outline-danger" id="addToCart">
                        <i style="font-size: 30px; !important; margin-top: 5px; !important;" class="fa fa-cart-plus"></i></a>
                </div>
            </div>
        </div>
    </div>
</section>

<br>
<%--FOOTER--%>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
