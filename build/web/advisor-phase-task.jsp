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
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
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
                        <a href="project-detail?projectId=${project.projectID}" class="p-3 border-bottom d-flex justify-content-start align-items-center text-decoration-none"><i class='bx bx-arrow-back'></i></i>Back</a>
                    </div>
                </div>
                <div class="col-10 p-4">
                    <div class="row">
                        <div class="col-12">
                            <h1 class="color-gray border-bottom pb-3">Phrase Detail</h1>
                            <div class="card-body">
                                <!-- Project Details -->
                                <div class="card mt-4">
                                    <div class="card-header">
                                        Phase Information
                                    </div>
                                    <div class="card-body">
                                        <h5 class="card-title">${phase.phaseTitle}</h5>
                                        <p class="card-text">${phase.projectPhraseStatus.statusName}</p>
                                        <p class="card-text"><strong>Start Date:</strong> ${phase.startDate}</p>
                                        <p class="card-text"><strong>End Date:</strong> ${phase.endDate}</p>
                                        <!-- Add more project details as needed -->
                                    </div>
                                </div>

                                <!-- Students in Group -->
                                <div class="card mt-4">
                                    <div class="card-header">
                                        Task
                                    </div>
                                    <div class="d-flex justify-content-end p-3 mt-4">
                                        <!-- Button to Open the Modal -->
                                        <button type="button" class="btn btn-primary mb-3" data-toggle="modal" data-target="#addTaskModal">
                                            Add New Task
                                        </button>
                                    </div>

                                    <table class="table mt-4">
                                        <thead>
                                            <tr>
                                                <th>Task ID</th>
                                                <th>Task Title</th>
                                                <th>Description</th>
                                                <th>Start Date</th>
                                                <th>End Date</th>
                                                <th>Status</th>
                                                <th>Assigned To</th>
                                                <th>Attached</th>
                                                <th>Action</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="task" items="${tasks}">
                                                <tr>
                                                    <td>${task.taskID}</td>
                                                    <td>${task.taskTitle}</td>
                                                    <td>${task.description}</td>
                                                    <td>${task.startDate}</td>
                                                    <td>${task.endDate}</td>
                                                    <td>${task.taskStatus.statusName}</td>
                                                    <td>${task.student.firstName} ${task.student.lastName}</td>
                                                    <td>
                                                        <c:if test="${not empty task.filePath && task.taskStatus.statusName eq 'Submitted'}">
                                                            <c:choose>
                                                                <c:when test="${task.filePath.endsWith('.jpg') or task.filePath.endsWith('.png') or task.filePath.endsWith('.gif')}">
                                                                    <img src="${task.filePath}" alt="Task Image" width="200"/>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <a href="${task.filePath}" download target="blank">Download File</a>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </c:if>
                                                        <c:if test="${empty task.filePath}">
                                                            No attached
                                                        </c:if>
                                                    </td>
                                                    
                                                    <td>
                                                        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#updateTaskModal${task.taskID}">
                                                            update
                                                        </button>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                    <c:if test="${isSuccess ne null && isSuccess}">
                                        <div class="alert alert-success alert-dismissible fade show mt-2" role="alert" id="mess">
                                            <strong>Save success!</strong> You should check in on some of those fields above.   
                                            <button type="button" class="btn-close"  onclick="document.getElementById('mess').style.display = 'none'"></button>
                                        </div>
                                    </c:if>
                                    <c:if test="${isSuccess ne null && !isSuccess}">
                                        <div class="alert alert-danger alert-dismissible fade show mt-2" role="alert" id="mess">
                                            <strong>Save failed!</strong> You should check your network.
                                            <button type="button" class="btn-close"  onclick="document.getElementById('mess').style.display = 'none'"></button>
                                        </div>
                                    </c:if>
                                </div>

                            </div>
                        </div>
                    </div>

                </div>
            </div>

            <c:forEach var="task" items="${tasks}">
                <!-- The Modal -->
                <div class="modal fade" id="updateTaskModal${task.taskID}">
                    <div class="modal-dialog modal-lg">
                        <div class="modal-content">

                            <!-- Modal Header -->
                            <div class="modal-header">
                                <h4 class="modal-title">Update Task</h4>
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                            </div>

                            <!-- Modal Body -->
                            <div class="modal-body">
                                <form id="updateTaskForm" action="update-task" method="post">
                                    <input type="hidden" name="projectId" value="${project.projectID}">
                                    <div class="form-group">
                                        <label for="taskID">Task ID:</label>
                                        <input type="text" class="form-control" id="taskID" name="taskID" readonly value="${task.taskID}">
                                    </div>
                                    <div class="form-group">
                                        <label for="phaseID">Phase:</label>
                                        <input type="text" class="form-control" id="phaseID" readonly value="${phase.phaseTitle}">
                                        <input type="hidden" class="form-control" id="phaseID" name="phaseID" readonly value="${phase.phaseID}">
                                    </div>
                                    <div class="form-group">
                                        <label for="taskTitle">Task Title:</label>
                                        <input type="text" class="form-control" id="taskTitle" name="taskTitle" value="${task.taskTitle}" required>
                                    </div>
                                    <div class="form-group">
                                        <label for="description">Description:</label>
                                        <input type="text" class="form-control" id="description" value="${task.description}" name="description" required>
                                    </div>
                                    <div class="form-group">
                                        <label for="startDate">Start Date (yyyy-mm-dd):</label>
                                        <input type="date" class="form-control" id="startDate" name="startDate" value="${task.startDate}" required>
                                    </div>
                                    <div class="form-group">
                                        <label for="endDate">End Date (yyyy-mm-dd):</label>
                                        <input type="date" class="form-control" id="endDate" name="endDate" value="${task.endDate}" required>
                                    </div>
                                    <div class="form-group">
                                        <label for="statusID" class="sr-only">Status</label>
                                        <select class="form-select" id="statusID" name="statusID">
                                            <c:forEach var="status" items="${statuses}">
                                                <option value="${status.statusID}" ${task.statusID == status.statusID ? 'selected' : ''}>${status.statusName}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label for="assignedTo" class="sr-only">Assigned To:</label>
                                        <select class="form-select" id="assignedTo" name="assignedTo">
                                            <c:forEach var="student" items="${students}">
                                                <option value="${student.studentID}" ${task.student.studentID == student.studentID ? 'selected' : ''}>${student.firstName} ${student.lastName}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <button type="submit" class="btn btn-primary" >Update Task</button>
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
            <div class="modal fade" id="addTaskModal">
                <div class="modal-dialog modal-lg">
                    <div class="modal-content">

                        <!-- Modal Header -->
                        <div class="modal-header">
                            <h4 class="modal-title">Add New Task</h4>
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                        </div>

                        <!-- Modal Body -->
                        <div class="modal-body">
                            <form id="addTaskForm" action="add-task" method="post">
                                <input type="hidden" name="projectId" value="${project.projectID}">
                                <div class="form-group">
                                    <label for="phaseID">Phase:</label>
                                    <input type="text" class="form-control" id="phaseID" name="phaseID" readonly value="${phase.phaseID}">
                                </div>
                                <div class="form-group">
                                    <label for="taskTitle">Task Title:</label>
                                    <input type="text" class="form-control" id="taskTitle" name="taskTitle" required>
                                </div>
                                <div class="form-group">
                                    <label for="description">Description:</label>
                                    <input type="text" class="form-control" id="description" name="description" required>
                                </div>
                                <div class="form-group">
                                    <label for="startDate">Start Date (yyyy-mm-dd):</label>
                                    <input type="date" class="form-control" id="startDate" name="startDate" required>
                                </div>
                                <div class="form-group">
                                    <label for="endDate">End Date (yyyy-mm-dd):</label>
                                    <input type="date" class="form-control" id="endDate" name="endDate" required>
                                </div>
                                <div class="form-group">
                                    <label for="statusID" class="sr-only">Status</label>
                                    <select class="form-select" id="statusID" name="statusID">
                                        <c:forEach var="status" items="${statuses}">
                                            <option value="${status.statusID}" ${param.statusID == status.statusID ? 'selected' : ''}>${status.statusName}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label for="assignedTo" class="sr-only">Assigned To:</label>
                                    <select class="form-select" id="assignedTo" name="assignedTo">
                                        <c:forEach var="student" items="${students}">
                                            <option value="${student.studentID}">${student.firstName} ${student.lastName}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <button type="submmit" class="btn btn-primary" >Add Task</button>
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
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    </body>

</html>
