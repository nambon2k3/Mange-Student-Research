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
                                    <div class="form-group col-11 mt-5 d-flex justify-content-end align-items-center" style="margin-bottom: 0">
                                        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#addPhaseModal">Add</button>
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
                                                        <a href="phase-task?phaseID=${phase.phaseID}&projectId=${project.projectID}" class="btn btn-primary">Details</a>
                                                        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#updatePhaseModal${phase.phaseID}">Update</button>
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


            <c:forEach var="phase" items="${phases}">
                <!-- The Modal -->
                <div class="modal fade" id="updatePhaseModal${phase.phaseID}">
                    <div class="modal-dialog modal-lg">
                        <div class="modal-content">

                            <!-- Modal Header -->
                            <div class="modal-header">
                                <h4 class="modal-title">Update Phase</h4>
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                            </div>
                            <!-- Modal Body -->
                            <div class="modal-body">
                                <form action="update-phase" method="post">
                                    <input type="hidden" name="phaseID" value="${phase.phaseID}">

                                    <div class="form-group">
                                        <label for="projectID">Project ID</label>
                                        <input type="text" class="form-control" id="projectID" name="projectID" value="${phase.projectID}" readonly>
                                    </div>
                                    <div class="form-group">
                                        <label for="phaseTitle">Phase Title</label>
                                        <input type="text" class="form-control" id="phaseTitle" name="phaseTitle" value="${phase.phaseTitle}" required>
                                    </div>
                                    <div class="form-group">
                                        <label for="startDate">Start Date</label>
                                        <input type="date" class="form-control" id="startDate" name="startDate" value="${phase.startDate}" required>
                                    </div>
                                    <div class="form-group">
                                        <label for="endDate">End Date</label>
                                        <input type="date" class="form-control" id="endDate" name="endDate" value="${phase.endDate}" required>
                                    </div>
                                    <div class="form-group">
                                        <label for="statusID" class="sr-only">Status</label>
                                        <select class="form-select" id="statusID" name="statusID">
                                            <c:forEach var="status" items="${statuses}">
                                                <option value="${status.statusID}">${status.statusName}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <button type="submit" class="btn btn-primary">Update Project Phase</button>
                                </form>
                            </div>

                            <!-- Modal Footer -->
                            <div class="modal-footer">

                                <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                            </div>

                        </div>
                    </div>
                </div>
            </c:forEach>
            <!-- The Modal -->
            <div class="modal fade" id="addPhaseModal">
                <div class="modal-dialog modal-lg">
                    <div class="modal-content">

                        <!-- Modal Header -->
                        <div class="modal-header">
                            <h4 class="modal-title">Add New Phase</h4>
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                        </div>

                        <!-- Modal Body -->
                        <div class="modal-body">
                            <form action="create-phase" method="post">
                                <div class="form-group">
                                    <label for="projectID">Project ID</label>
                                    <input type="text" class="form-control" id="projectID" name="projectID" value="${project.projectID}" readonly>
                                </div>
                                <div class="form-group">
                                    <label for="phaseTitle">Phase Title</label>
                                    <input type="text" class="form-control" id="phaseTitle" name="phaseTitle" required>
                                </div>
                                <div class="form-group">
                                    <label for="startDate">Start Date</label>
                                    <input type="date" class="form-control" id="startDate" name="startDate" required>
                                </div>
                                <div class="form-group">
                                    <label for="endDate">End Date</label>
                                    <input type="date" class="form-control" id="endDate" name="endDate" required>
                                </div>
                                <div class="form-group">
                                    <label for="statusID" class="sr-only">Status</label>
                                    <select class="form-select" id="statusID" name="statusID">
                                        <c:forEach var="status" items="${statuses}">
                                            <option value="${status.statusID}">${status.statusName}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <button type="submit" class="btn btn-primary">Create Project Phase</button>
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
