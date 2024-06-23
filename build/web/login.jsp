<%-- 
    Document   : login.jsp
    Created on : Jun 22, 2024, 11:10:37 AM
    Author     : Legion
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Login</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
        <link rel="stylesheet" href="style.css">
    </head>

    <body class="overflow-hidden">
        <div class="containner-fluid">
            <div class="row d-flex vh-100 justify-content-center align-items-center">
                <div class="col-4">
                    <div class="card">
                        <div class="card-header">
                            Login
                        </div>
                        <div class="card-body">
                            <form action="login" id="loginForm" method="post">
                                <input id="username" name="username" type="text" class="form-control " placeholder="Username">
                                <input  id="password" name="password" type="password" class="form-control mt-3 mb-4" placeholder="Password">
                                <input type="submit" class="btn btn-success form-control mt-2 mb-4" value="Login">
                            </form>
                            <div class="text-center mt-3">
                                <p id="loginError" class="text-danger">
                                    <% if (request.getAttribute("errorMessage") != null) { %>
                                    <%= request.getAttribute("errorMessage") %>
                                    <% } %>
                                </p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
        crossorigin="anonymous"></script>
        <script src="login.js"></script>
    </body>
</html>

