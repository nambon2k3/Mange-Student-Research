

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
                        <li><a class="dropdown-item border-bottom d-flex justify-content-start align-items-center" href="student-dashboard">
                                <i class='bx bxs-user' style="margin-right: 20px;"></i><span>List-project</span></a></li>
                        <li><a class="dropdown-item d-flex justify-content-start align-items-center" href="logout"><i
                                    class='bx bx-log-in' style="margin-right: 20px;"></i>Logout</a></li>
                    </ul>
                </div>
            </div>
            <div class="row d-flex vh-100">
                <div class="col-2 bg-light border-end p-0">
                    <div class="d-flex flex-column mb-3">
                        <a href="student-dashboard" class="p-3 border-bottom d-flex justify-content-start align-items-center text-decoration-none"><i class='bx bx-table mr-10'></i>List projects</a>
                    </div>
                </div>
                <div class="col-10 p-4">
                    <div class="row">
                        <div class="col-12">
                            <div class="card-body">
                                <div class="content">
                                    <h1 class="mt-5">Projects</h1>

                                    <form class="mb-5" method="get" action="student-dashboard">
                                        <div class="input-group mb-3">
                                            <input type="text" class="form-control" placeholder="Search by project name" name="search" value="${search}">
                                            <div class="input-group-append">
                                                <button class="btn btn-outline-secondary" type="submit">Search</button>
                                            </div>
                                        </div>
                                    </form>

                                    <c:forEach var="project" items="${projects}">
                                        <div class="card mb-3">
                                            <div class="card-body d-flex justify-content-between align-items-center">
                                                <div>
                                                    <h5 class="card-title">${project.projectTitle}</h5>
                                                    <p class="card-text">${project.description}</p>
                                                    <p class="card-text"><strong>Advisor:</strong> ${project.advisor.firstName} ${project.advisor.lastName}</p>
                                                </div>
                                                <div class="col-3 d-flex justify-content-between align-items-center">
                                                    <div>
                                                        <p class="card-text"><small class="text-muted">Start Date: ${project.startDate}</small></p>
                                                        <p class="card-text"><small class="text-muted">End Date: ${project.endDate}</small></p>
                                                    </div>
                                                    <div>
                                                        <a href="student-project-detail?projectId=${project.projectID}" class="btn btn-primary" >View</a>
                                                    </div>
                                                </div>


                                            </div>
                                        </div>
                                    </c:forEach>

                                    <nav aria-label="Page navigation">
                                        <ul class="pagination">
                                            <c:if test="${currentPage > 1}">
                                                <li class="page-item">
                                                    <a class="page-link" href="projects?page=${currentPage - 1}&search=${search}" aria-label="Previous">
                                                        <span aria-hidden="true">&laquo;</span>
                                                    </a>
                                                </li>
                                            </c:if>
                                            <c:forEach begin="1" end="${totalPages}" var="i">
                                                <li class="page-item <c:if test='${i == currentPage}'>active</c:if>'">
                                                    <a class="page-link" href="projects?page=${i}&search=${search}">${i}</a>
                                                </li>
                                            </c:forEach>
                                            <c:if test="${currentPage < totalPages}">
                                                <li class="page-item">
                                                    <a class="page-link" href="projects?page=${currentPage + 1}&search=${search}" aria-label="Next">
                                                        <span aria-hidden="true">&raquo;</span>
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



            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
                    integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
            crossorigin="anonymous"></script>
            <script src="profile.js"></script>
    </body>

</html>
