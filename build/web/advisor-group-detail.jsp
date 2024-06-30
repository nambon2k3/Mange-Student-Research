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
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
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
                        <li><a class="dropdown-item d-flex justify-content-start align-items-center" href="logout"><i
                                    class='bx bx-log-in' style="margin-right: 20px;"></i>Logout</a></li>
                    </ul>
                </div>
            </div>
            <div class="row d-flex vh-100">
                <div class="col-2 bg-light border-end p-0">
                    <div class="d-flex flex-column mb-3">
                        <a href="advisor-dashboard" class="p-3 border-bottom d-flex justify-content-start align-items-center text-decoration-none"><i class='bx bx-table mr-10'></i>List projects</a>
                        <a href="advisor-group" class="p-3 border-bottom d-flex justify-content-start align-items-center text-decoration-none"><i class='bx bx-table mr-10'></i>List Groups</a>
                    </div>
                </div>
                <div class="col-10 p-4">
                    <div class="row">
                        <div class="col-12">
                            <h1 class="color-gray border-bottom pb-3">All Project</h1>
                            <div class="card mt-4">
                                <div class="card-header">
                                    Projects
                                </div>
                                <div class="form-group col-12 mt-5 d-flex justify-content-end align-items-center" style="margin-bottom: 0">
                                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#addStudnetModal">Add</button>
                                </div>
                                <div class="card-body">
                                    <table class="table mt-4 text-center">
                                        <thead>
                                            <tr>
                                                <th>Student ID</th>
                                                <th>First Name</th>
                                                <th>Last Name</th>
                                                <th>Email</th>
                                                <th>Enrollment Date</th>
                                                <th>Department</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="student" items="${studentInGroup}">
                                                <tr>
                                                    <td>${student.studentID}</td>
                                                    <td>${student.firstName}</td>
                                                    <td>${student.lastName}</td>
                                                    <td>${student.email}</td>
                                                    <td>${student.enrollmentDate}</td>
                                                    <td>${student.department.departmentName}</td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- The Modal -->
        <div class="modal fade" id="addStudnetModal">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">

                    <!-- Modal Header -->
                    <div class="modal-header">
                        <h4 class="modal-title">Add New Project</h4>
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                    </div>

                    <!-- Modal Body -->
                    <div class="modal-body">
                        <form action="join-studnet" method="post">
                            <div class="form-group">
                                <label for="projectTitle">Group ID:</label>
                                <input type="text" class="form-control" id="projectTitle" name="groupID" value="${groupID}" readonly/>
                            </div>

                            <div class="form-group">
                                <label for="groupID">Group ID:</label>
                                <select class="form-control" id="groupID" name="studentID">
                                    <c:forEach var="student" items="${students}">
                                        <option value="${student.studentID}">${student.firstName} ${student.lastName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <button type="submit" class="btn btn-primary">Submit</buton>
                        </form>
                    </div>

                    <!-- Modal Footer -->
                    <div class="modal-footer">

                        <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                    </div>

                </div>
            </div>
        </div>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
        crossorigin="anonymous"></script>
    </body>

</html>
