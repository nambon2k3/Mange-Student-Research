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
                            <h1 class="color-gray border-bottom pb-3">All Group</h1>
                            <div class="card mt-4">
                                <div class="card-header">
                                    Projects
                                </div>
                                <div class="card-body">
                                    <form class="form-inline mb-3" action="advisor-group">
                                        <input type="text" class="form-control mr-sm-2" name="searchName" value="${searchName}" placeholder="Search by Name">
                                        <button type="submit" class="btn btn-primary">Search</button>
                                        <button type="button" class="btn btn-primary ml-5" data-toggle="modal" data-target="#addGroupModal">Add</button>
                                    </form>
                                    <!-- Table of groups -->
                                    <table class="table">
                                        <thead>
                                            <tr>
                                                <th>Group ID</th>
                                                <th>Group Name</th>
                                                <th>Action</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="group" items="${groups}">
                                                <tr>
                                                    <td>${group.groupID}</td>
                                                    <td>${group.groupName}</td>
                                                    <td>
                                                        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#updateGroupModal${group.groupID}">
                                                            update
                                                        </button>
                                                        <a href="advisor-group-detail?groupID=${group.groupID}" class="btn btn-primary" >
                                                            details
                                                        </a>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>

                                    <!-- Pagination -->
                                    <ul class="pagination">
                                        <c:if test="${currentPage > 1}">
                                            <li class="page-item"><a class="page-link" href="?page=1&searchName=${searchName}">First</a></li>
                                            <li class="page-item"><a class="page-link" href="?page=${currentPage - 1}&searchName=${searchName}">Previous</a></li>
                                            </c:if>

                                        <c:forEach begin="1" end="${totalPages}" varStatus="loop">
                                            <c:choose>
                                                <c:when test="${loop.index == currentPage}">
                                                    <li class="page-item active"><span class="page-link">${loop.index}</span></li>
                                                    </c:when>
                                                    <c:otherwise>
                                                    <li class="page-item"><a class="page-link" href="?page=${loop.index}&searchName=${searchName}">${loop.index}</a></li>
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:forEach>

                                        <c:if test="${currentPage < totalPages}">
                                            <li class="page-item"><a class="page-link" href="?page=${currentPage + 1}&searchName=${searchName}">Next</a></li>
                                            <li class="page-item"><a class="page-link" href="?page=${totalPages}&searchName=${searchName}">Last</a></li>
                                            </c:if>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>


        <c:forEach var="group" items="${groups}">
            <!-- The Modal -->
            <div class="modal fade" id="updateGroupModal${group.groupID}">
                <div class="modal-dialog modal-lg">
                    <div class="modal-content">

                        <!-- Modal Header -->
                        <div class="modal-header">
                            <h4 class="modal-title">Update Group</h4>
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                        </div>
                        <!-- Modal Body -->
                        <div class="modal-body">
                            <form action="update-group" method="post">
                                <div class="form-group">
                                    <label for="groupID">Group ID: </label>
                                    <input type="text" class="form-control" id="groupID" name="groupID" value="${group.groupID}" readonly/>
                                </div>
                                <div class="form-group">
                                    <label for="groupName">Group Name</label>
                                    <input type="text" class="form-control" id="groupName" name="groupName" value="${group.groupName}" required>
                                </div>
                                <button type="submit" class="btn btn-primary">Update Group</button>
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
        <div class="modal fade" id="addGroupModal">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">

                    <!-- Modal Header -->
                    <div class="modal-header">
                        <h4 class="modal-title">Add New Project</h4>
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                    </div>

                    <!-- Modal Body -->
                    <div class="modal-body">
                        <form action="add-group" method="post">
                            <input type="hidden" name="groupID" value="${group.groupID}">
                            <div class="form-group">
                                <label for="groupid">Group ID</label>
                                <input type="text" class="form-control" id="groupid"  readonly>
                            </div>
                            <div class="form-group">
                                <label for="groupName">Group Name</label>
                                <input type="text" class="form-control" id="groupName" name="groupName" required>
                            </div>
                            <button type="submit" class="btn btn-primary">Add Group</button>
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
