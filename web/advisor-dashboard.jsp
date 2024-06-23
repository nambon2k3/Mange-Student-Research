<%-- 
    Document   : advisor-dashboard
    Created on : Jun 22, 2024, 2:19:14 PM
    Author     : Legion
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Homepage</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
        <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>

        <link rel="stylesheet" href="style.css">
    </head>

    <body>
        <div class="container-fluid">
            <div class="row d-flex bg-light border justify-content-between align-items-center vh-6">
                <div class="col-1">
                    <span>CMS</span>
                </div>
                <div class="dropdown col-1" style="text-align: right;">
                    <button class="btn dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
                        <i class='bx bxs-user'></i>
                    </button>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item border-bottom d-flex justify-content-start align-items-center" href="#">
                                <i class='bx bxs-user' style="margin-right: 20px;"></i><span>User Profile</span></a></li>
                        <li><a class="dropdown-item d-flex justify-content-start align-items-center" href="login.html"><i
                                    class='bx bx-log-in' style="margin-right: 20px;"></i>Logout</a></li>
                    </ul>
                </div>
            </div>
            <div class="row d-flex vh-100">
                <div class="col-2 bg-light border-end p-0">
                    <div class="d-flex flex-column mb-3">
                        <a href="view-content.html" class="p-3 border-bottom d-flex justify-content-start align-items-center text-decoration-none"><i class='bx bx-table mr-10'></i>View Contents</a>
                        <a href="form-content.html" class="p-3 border-bottom d-flex justify-content-start align-items-center text-decoration-none" ><i class='bx bx-edit mr-10' ></i>Form content</a>
                    </div>
                </div>
                <div class="col-10 p-4">
                    <div class="row">
                        <div class="col-12">
                            <h1 class="color-gray border-bottom pb-3">Edit Profile</h1>
                            <div class="card mt-4">
                                <div class="card-header">
                                    Projects
                                </div>
                                <div class="card-body">
                                    <table class="table table-bordered table-striped mt-3">
                                        <thead class="thead-dark">
                                            <tr>
                                                <th>Project ID</th>
                                                <th>Project Title</th>
                                                <th>Description</th>
                                                <th>Start Date</th>
                                                <th>End Date</th>
                                                <th>Advisor ID</th>
                                                <th>Status ID</th>
                                                <th>Group ID</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach var="project" items="${projects}">
                                            <tr>
                                                <td>${project.projectID}</td>
                                                <td>${project.projectTitle}</td>
                                                <td>${project.description}</td>
                                                <td>${project.startDate}</td>
                                                <td>${project.endDate}</td>
                                                <td>${project.advisorID}</td>
                                                <td>${project.statusID}</td>
                                                <td>${project.groupID}</td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                    <nav aria-label="Page navigation">
                                        <ul class="pagination justify-content-center">
                                            <c:if test="${currentPage > 1}">
                                                <li class="page-item">
                                                    <a class="page-link" href="projects?page=${currentPage - 1}&advisorID=${advisorID}" aria-label="Previous">
                                                        <span aria-hidden="true">&laquo;</span>
                                                        <span class="sr-only">Previous</span>
                                                    </a>
                                                </li>
                                            </c:if>
                                            <c:forEach begin="1" end="${noOfPages}" var="i">
                                                <li class="page-item <c:if test='${i == currentPage}'>active</c:if>">
                                                    <a class="page-link" href="projects?page=${i}&advisorID=${advisorID}">${i}</a>
                                                </li>
                                            </c:forEach>
                                            <c:if test="${currentPage < noOfPages}">
                                                <li class="page-item">
                                                    <a class="page-link" href="projects?page=${currentPage + 1}&advisorID=${advisorID}" aria-label="Next">
                                                        <span aria-hidden="true">&raquo;</span>
                                                        <span class="sr-only">Next</span>
                                                    </a>
                                                </li>
                                            </c:if>
                                        </ul>
                                    </nav>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>



        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
        crossorigin="anonymous"></script>
        <script src="profile.js"></script>
    </body>

</html>
