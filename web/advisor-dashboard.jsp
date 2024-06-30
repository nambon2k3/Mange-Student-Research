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
                                <div class="card-body">
                                    <!-- Search and Filter Form -->
                                    <form class="form-inline mb-3 d-flex col-12" method="get" action="advisor-dashboard">
                                        <input type="hidden" name="advisorID" value="${param.advisorID}">
                                        <div class="form-group col-2 mx-sm-3 mb-2">
                                            <label for="projectTitle" class="sr-only">Project Title</label>
                                            <input type="text" class="form-control" id="projectTitle" name="projectTitle" placeholder="Project Title" value="${param.projectTitle}">
                                        </div>
                                        <div class="form-group col-2 mx-sm-3 mb-2">
                                            <label for="statusID" class="sr-only">Status</label>
                                            <select class="form-control" id="statusID" name="statusID">
                                                <option value="">All Statuses</option>
                                                <c:forEach var="status" items="${statuses}">
                                                    <option value="${status.statusID}" ${param.statusID == status.statusID ? 'selected' : ''}>${status.statusName}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <div class="form-group col-2  mx-sm-3 mb-2">
                                            <label for="startDate" class="sr-only">Start Date</label>
                                            <input type="date" class="form-control" id="startDate" name="startDate" value="${param.startDate}">
                                        </div>
                                        <div class="form-group col-2  mx-sm-3 mb-2">
                                            <label for="endDate" class="sr-only">End Date</label>
                                            <input type="date" class="form-control" id="endDate" name="endDate" value="${param.endDate}">
                                        </div>
                                        <div class="form-group col-1  mx-sm-3 mb-2">
                                            <label for="endDate" class="sr-only" style="visibility: hidden">Filter</label><br>
                                            <button type="submit" class="btn btn-primary mb-2">Filter</button>
                                        </div>
                                        <div class="form-group col-1  mx-sm-3 mb-2">
                                            <label for="endDate" class="sr-only" style="visibility: hidden">Filter</label><br>
                                            <button type="button" class="btn btn-primary mb-2" data-toggle="modal" data-target="#addProjectModal">Add</button>
                                        </div>

                                    </form>
                                    <table class="table table-bordered table-striped mt-3">
                                        <thead class="thead-dark">
                                            <tr>
                                                <th>Project ID</th>
                                                <th>Project Title</th>
                                                <th>Description</th>
                                                <th>Start Date</th>
                                                <th>End Date</th>
                                                <th>Status ID</th>
                                                <th>Group ID</th>
                                                <th>Action</th>
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
                                                    <td>${project.projectStatus.statusName}</td>
                                                    <td>${project.groupID}</td>
                                                    <td>
                                                        <a href="project-detail?projectId=${project.projectID}" class="btn btn-primary">Details</a>
                                                        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#updateProjectModal${project.projectID}">
                                                            update
                                                        </button>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                    <nav aria-label="Page navigation">
                                        <ul class="pagination justify-content-center">
                                            <c:if test="${currentPage > 1}">
                                                <li class="page-item">
                                                    <a class="page-link" href="advisor-dashboard?page=${currentPage - 1}&advisorID=${param.advisorID}&projectTitle=${param.projectTitle}&statusID=${param.statusID}&startDate=${param.startDate}&endDate=${param.endDate}" aria-label="Previous">
                                                        <span aria-hidden="true">&laquo;</span>
                                                        <span class="sr-only">Previous</span>
                                                    </a>
                                                </li>
                                            </c:if>
                                            <c:forEach begin="1" end="${noOfPages}" var="i">
                                                <li class="page-item <c:if test='${i == currentPage}'>active</c:if>">
                                                    <a class="page-link" href="advisor-dashboard?page=${i}&advisorID=${param.advisorID}&projectTitle=${param.projectTitle}&statusID=${param.statusID}&startDate=${param.startDate}&endDate=${param.endDate}">${i}</a>
                                                </li>
                                            </c:forEach>
                                            <c:if test="${currentPage < noOfPages}">
                                                <li class="page-item">
                                                    <a class="page-link" href="advisor-dashboard?page=${currentPage + 1}&advisorID=${param.advisorID}&projectTitle=${param.projectTitle}&statusID=${param.statusID}&startDate=${param.startDate}&endDate=${param.endDate}" aria-label="Next">
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


        <c:forEach var="project" items="${projects}">
            <!-- The Modal -->
            <div class="modal fade" id="updateProjectModal${project.projectID}">
                <div class="modal-dialog modal-lg">
                    <div class="modal-content">

                        <!-- Modal Header -->
                        <div class="modal-header">
                            <h4 class="modal-title">Update Project</h4>
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                        </div>
                        <!-- Modal Body -->
                        <div class="modal-body">
                            <form action="update-project" method="post">
                                <div class="form-group">
                                    <label for="projectID">Project Title:</label>
                                    <input type="text" class="form-control" id="projectID" name="projectID" value="${project.projectID}" readonly>
                                </div>
                                <div class="form-group">
                                    <label for="projectTitle">Project Title:</label>
                                    <input type="text" class="form-control" id="projectTitle" name="projectTitle" value="${project.projectTitle}" required>
                                </div>
                                <div class="form-group">
                                    <label for="description">Description:</label>
                                    <input type="text" class="form-control" id="description" name="description" value="${project.description}" required>
                                </div>
                                <div class="form-group">
                                    <label for="startDate">Start Date:</label>
                                    <input type="date" class="form-control" id="startDate" name="startDate" value="${project.startDate}" required>
                                </div>
                                <div class="form-group">
                                    <label for="endDate">End Date:</label>
                                    <input type="date" class="form-control" id="endDate" name="endDate" value="${project.endDate}" required>
                                </div>
                                <div class="form-group">
                                    <label for="statusID">Status ID:</label>
                                    <select class="form-control" id="statusID" name="statusID">
                                        <c:forEach var="status" items="${statuses}">
                                            <option value="${status.statusID}" ${project.projectStatus.statusID == status.statusID ? 'selected' : ''} >${status.statusName}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label for="groupID">Group ID:</label>
                                    <select class="form-control" id="groupID" name="groupID">
                                        <c:forEach var="group" items="${groups}">
                                            <option value="${group.groupID}" ${project.groupID == group.groupID ? 'selected' : ''}>${group.groupName}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <button type="submit" class="btn btn-primary">Submit</button>
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
        <div class="modal fade" id="addProjectModal">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">

                    <!-- Modal Header -->
                    <div class="modal-header">
                        <h4 class="modal-title">Add New Project</h4>
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                    </div>

                    <!-- Modal Body -->
                    <div class="modal-body">
                        <form action="add-project" method="post">
                            <div class="form-group">
                                <label for="projectTitle">Project Title:</label>
                                <input type="text" class="form-control" id="projectTitle" name="projectTitle" required>
                            </div>
                            <div class="form-group">
                                <label for="description">Description:</label>
                                <textarea class="form-control" id="description" name="description" rows="4"></textarea>
                            </div>
                            <div class="form-group">
                                <label for="startDate">Start Date:</label>
                                <input type="date" class="form-control" id="startDate" name="startDate" required>
                            </div>
                            <div class="form-group">
                                <label for="endDate">End Date:</label>
                                <input type="date" class="form-control" id="endDate" name="endDate" required>
                            </div>
                            <div class="form-group">
                                <label for="statusID">Status ID:</label>
                                <select class="form-control" id="statusID" name="statusID">
                                    <c:forEach var="status" items="${statuses}">
                                        <option value="${status.statusID}" >${status.statusName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="groupID">Group ID:</label>
                                <select class="form-control" id="groupID" name="groupID">
                                    <c:forEach var="group" items="${groups}">
                                        <option value="${group.groupID}">${group.groupName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <button type="submit" class="btn btn-primary">Submit</button>
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
