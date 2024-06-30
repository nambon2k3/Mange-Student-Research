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
                        <a href="advisor-dashboard" class="p-3 border-bottom d-flex justify-content-start align-items-center text-decoration-none"><i class='bx bx-table mr-10'></i>List projects</a>
                    </div>
                </div>
                <div class="col-10 p-4">
                    <div class="row">
                        <div class="col-12">
                            <h1 class="color-gray border-bottom pb-3">Project Detail</h1>
                            <div class="card-body">
                                <!-- Project Details -->
                                <div class="card mt-4">
                                    <div class="card-header">
                                        Project Information
                                    </div>
                                    <div class="card-body">
                                        <h5 class="card-title">${project.projectTitle}</h5>
                                        <p class="card-text">${project.description}</p>
                                        <p class="card-text"><strong>Start Date:</strong> ${project.startDate}</p>
                                        <p class="card-text"><strong>End Date:</strong> ${project.endDate}</p>
                                        <!-- Add more project details as needed -->
                                    </div>
                                </div>

                                <!-- Students in Group -->
                                <div class="card mt-4">
                                    <div class="card-header">
                                        Students in Group
                                    </div>
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
                                            <c:forEach var="student" items="${students}">
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

                                <!-- Project Phases -->
                                <div class="card mt-4">
                                    <div class="card-header">
                                        Project Phases
                                    </div>
                                    <table class="table mt-4 text-center">
                                        <thead>
                                            <tr>
                                                <th>Phase ID</th>
                                                <th>Phase Title</th>
                                                <th>Start Date</th>
                                                <th>End Date</th>
                                                <th>Status</th>
                                                <th>Action</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="phase" items="${phases}">
                                                <tr>
                                                    <td>${phase.phaseID}</td>
                                                    <td>${phase.phaseTitle}</td>
                                                    <td>${phase.startDate}</td>
                                                    <td>${phase.endDate}</td>
                                                    <td>${phase.projectPhraseStatus.statusName}</td>
                                                    <td>
                                                        <a href="student-phase-task?phaseID=${phase.phaseID}&projectId=${project.projectID}" class="btn btn-primary">Details</a>
                                                    </td>
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



            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
                    integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
            crossorigin="anonymous"></script>
            <script src="profile.js"></script>
    </body>

</html>
