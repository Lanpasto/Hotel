<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@include file="header.jsp" %>
<%@include file="background.jsp" %>
<%--
<tf:title titleName="RoomManger"/>
--%>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<div class="container mt-3">


    <div class="bg-warning">

        <div>
            <div class="navbar-nav">
                <div class="navbar-nav">
                    <label class="text-dark border-0 h1 text-center"><fmt:message key="admin.allRoom"
                                                                                  bundle="${lang}"/></label></div>
                <div class="navbar-nav"><a class="text-white text-end " href="controller?action=adminRequestPage">
                    <h3 class="badge text-white  bg-danger text-wrap fs-5"> <fmt:message key="admin.request"
                                                                                         bundle="${lang}"/></h3>
                </a>
                </div>
            </div>


        </div>
    </div><form method="post" action="controller?action=adminReservePage">
    <table class="table table-secondary">
    <thead class="table table-secondary">
        <tr>
            <td>
                <div>
                    <fmt:message key="statusOrder.status"
                                 bundle="${lang}"/>:<input list="classOfRoomAb" value="" name="statusForSorting" id="statusForSorting">
                    <datalist id="classOfRoomAb">
                        <option>available</option>
                        <option>booked</option>
                        <option>busy</option>
                        <option>unavailable</option>
                    </datalist>
                </div>
            </td>
            <td>
            </td>
            <td>
                <div class="text-end">
                    <button style="background:#3630a3;color:white;">
                        <fmt:message key="classSelect.search"
                                     bundle="${lang}"/>
                    </button>
                </div>
            </td>

        </tr>
        </thead>


        <c:forEach items="${AllListRoom}" var="room">

            <form method="post" action="controller?action=updateStatus" class="register-form">
                <tbody class="bg-opacity-25 bg-white">
                <tr>
                    <td class="col-md-6"><img src="${pageContext.request.contextPath}${room.image}"
                                              style="height: 200px; width: 400px" alt="#"></td>
                    <td>
                        <div class=" badge text-dark text-bg-success"><fmt:message key="classSelect.class"
                                                                                   bundle="${lang}"/>:${room.typeName}</div>
                        <div class="badge text-dark bg-info text-wrap "><fmt:message key="classSelect.numberOfPerson"
                                                                                     bundle="${lang}"/>: ${room.guests}</div>
                        <c:set var="status" value="${room.status}"/>
                        <%
                            String status = "";
                            status = status + pageContext.getAttribute("status");
                        %>
                        <%if (status.equalsIgnoreCase("booked")) {%>
                        <div class="badge bg-danger text-wrap "><fmt:message key="statusOrder.status"
                                                                             bundle="${lang}"/>: ${room.status}</div>
                        <%} else if (status.equalsIgnoreCase("available")) {%>
                        <div class="badge bg-success text-wrap "><fmt:message key="statusOrder.status"
                                                                              bundle="${lang}"/>: ${room.status}</div>
                        <%} else if (status.equalsIgnoreCase("busy")) {%>
                        <div class="badge bg-warning text-wrap "><fmt:message key="statusOrder.status"
                                                                              bundle="${lang}"/>: ${room.status}</div>
                        <%} else {%>
                        <div class="badge bg-dark text-wrap "><fmt:message key="statusOrder.status"
                                                                           bundle="${lang}"/>: ${room.status}</div>
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
                                    value="${room.id}"> <fmt:message key="admin.submit"
                                                                     bundle="${lang}"/>
                            </button>
                        </div>
            </form>
        </c:forEach>
    </table>
    <nav aria-label="Page navigation example">
        <ul class="pagination justify-content-center">
            <c:if test="${currentPage != 1}">
                <li class="page-item">
                    <button type="submit" name="action1" value="${currentPage - 1}" style=" color: black;"
                            formaction="controller?action=adminReservePage&page=${currentPage - 1}" class="page-link">
                        <fmt:message key="classSelect.previous"
                                     bundle="${lang}"/>
                    </button>
                </li>
            </c:if>

            <%--For displaying Page numbers. The when condition does not display
                        a link for the current page--%>
            <table>
                <tr>
                    <c:forEach begin="1" end="${noOfPages}" var="i">
                        <c:choose>
                            <c:when test="${currentPage eq i}">
                                <li class="page-item">

                                    <button type="submit" name="action1" value="${i}" class="page-link">${i}</button>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <li class="page-item">

                                    <button type="submit" name="action1" value="${i}" style=" color: black;"
                                            formaction="controller?action=adminReservePage&page=${i}"
                                            class="page-link">${i}</button>
                                </li>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </tr>
            </table>

            <%--For displaying Next link --%>

            <c:if test="${currentPage lt noOfPages}">
                <li class="page-item">
                    <button type="submit" name="action1" value="${currentPage + 1}" style=" color: black;"
                            formaction="controller?action=adminReservePage&page=${currentPage + 1}"
                            class="page-link"><fmt:message key="classSelect.next"
                                                           bundle="${lang}"/>
                    </button>
                </li>
            </c:if>
        </ul>
    </nav>
    </form>
</div>
</body>

