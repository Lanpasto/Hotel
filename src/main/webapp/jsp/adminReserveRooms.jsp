<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@include file="header.jsp" %>
<%@include file="background.jsp" %>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<div class="container mt-3">


    <div class="bg-warning">

        <div class="navbar-nav">
            <label class="text-dark border-0 h1 text-center">All Rooms</label>
        </div>

        <div class="navbar-nav ">
            <form method="post" action="controller?action=adminRequestPage">
                <button style="height: 40px; width: 80px;margin-left: 1200px" class="bg-danger text-end"> Request</button>
            </form>
        </div>
        <div><form method="post" action="controller?action=adminReservePage">
            <div class="end">
                <input list="classOfRoomAb" value="" name="statusForSorting" id="statusForSorting">
                <datalist id="classOfRoomAb" >
                    <option>available</option>
                    <option>booked</option>
                    <option>busy</option>
                    <option>un_available</option>
                </datalist>
            </div>
            <div>
                    <button style="background:#3630a3;color:white;">
                       Search</button>
            </div>
        </form>
        </div>
    </div>


    <table class="table table-secondary">
        <c:forEach items="${AllListRoom}" var="room">

            <form method="post" action="controller?action=updateStatus" class="register-form">
                <tbody class="bg-opacity-25 bg-white">
                <tr>
                    <td class="col-md-6"><img src="${pageContext.request.contextPath}${room.image}"
                                              style="height: 200px; width: 400px" alt="#"></td>
                    <td>
                        <div class=" badge text-dark text-bg-success">Class:${room.typeName}</div>
                        <div class="badge text-dark bg-info text-wrap ">Number of person: ${room.guests}</div>
                        <c:set var="status" value="${room.status}"/>
                        <%
                            String status = "";
                            status = status + pageContext.getAttribute("status");
                        %>
                        <%if (status.equalsIgnoreCase("booked")) {%>
                        <div class="badge bg-danger text-wrap ">Status: ${room.status}</div>
                        <%} else if (status.equalsIgnoreCase("available")) {%>
                        <div class="badge bg-success text-wrap ">Status: ${room.status}</div>
                        <%} else if (status.equalsIgnoreCase("busy")) {%>
                        <div class="badge bg-warning text-wrap ">Status: ${room.status}</div>
                        <%} else {%>
                        <div class="badge bg-dark text-wrap ">Status: ${room.status}</div>
                        <%}%>
                    </td>
                    <td>
                        <div class="text-end">
                            <select name="statusOfRoom" required="required">
                                <option>Set status</option>
                                <option>available</option>
                                <option>booked</option>
                                <option>busy</option>
                                <option>unavailable</option>
                            </select></div>
                        <div class="text-end">
                            <button type="submit" name="room" style="margin-top: 150px" class="btn btn-success "
                                    value="${room.id}">Submit
                            </button>
                        </div>
            </form>
        </c:forEach>
    </table>
</div>
</body>

