<%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 3/5/2022
  Time: 8:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
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
<body class="bodyImage" style='background-image: url("/img/bg.jpg"); '>

<%--HEADER--%>
<div class="containerLogin">
    <div class="loginPanel">
        <form action="/login?action=login" method="post">
            <table>
                <tr>
                    <td><a href="/pagination"><i style="font-size: 25px" class="fas fa-home"></i></a></td>
                    <td colspan="2" style="text-align: center"><b>LOGIN !</b></td>
                </tr>
                <tr>
                    <td style="width: 100px;">Username : </td>
                    <td><input type="text" name="username" style="border-radius: 2px"></td>
                </tr>
                <tr>
                    <td>Password : </td>
                    <td><input type="password" name="password" style="border-radius: 2px"></td>
                </tr>
                <tr>
                    <td><a href="/signUp.jsp"><input type="button" value="SignUp"></a></td>
                    <td><input type="submit" value="Login">&nbsp<a href=""> Forgot Password ?</a></td>
                </tr>

            </table>
        </form>
        <i style="color: red">${thongBao}</i>
    </div>
</div>
</body>
</html>
