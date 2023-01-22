<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@include file="header.jsp" %>
<%@include file="background.jsp" %>
<%--<tf:title titleName="RequestManger"/>--%>
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
            <div class="navbar-nav">
                <label class="text-dark border-0 h1 text-center">All Request</label></div>
            <div class="navbar-nav"><a class="text-white text-start " href="controller?action=adminReservePage">
                <h3 class="badge text-white  bg-danger text-wrap fs-5"> Rooms</h3>
            </a>
            </div>
        </div>


        <table class="table table-secondary">


            <%--@elvariable id="requestList" type="java.util.List"--%>
            <c:forEach items="${requestList}" var="request">


                <form method="post" action="controller?action=confirmCommand" class="register-form">
                    <tbody class="bg-opacity-25 bg-white">
                    <tr>
                        <td>

                            <div class="text-dark text-bg-success">Name:<input name="userId" value="${request.userId}"
                                                                               hidden="hidden">${request.name} ${request.lastName}
                            </div>
                            <div class="text-dark text-bg-success">Class:<input name="idRequest" value="${request.id}"
                                                                                hidden="hidden">${request.typeName}
                            </div>
                            <div class="text-dark text-bg-success">Guests:${request.guests} </div>
                            <div class="text-dark text-bg-success">Email:${request.email}</div>
                        </td>
                        <td>
                            <div>Length of reserve:
                                <div class="badge bg-primary text-wrap " id="dateOfRequest" name="dateOfRequest"
                                     value="${request.dateOfSettlement}">
                                    <input name="dateOfSettlement"
                                           value="<fmt:formatDate value="${request.dateOfSettlement}" pattern="MM/dd/yyyy"/>"
                                           hidden="hidden"> <fmt:formatDate value="${request.dateOfSettlement}"
                                                                            pattern=" MM/dd/yyyy"/> -
                                    <input name="dateOfOut"
                                           value="<fmt:formatDate value="${request.dateOfOut}" pattern="MM/dd/yyyy"/>"
                                           hidden="hidden"> <fmt:formatDate value="${request.dateOfOut}"
                                                                            pattern="MM/dd/yyyy"/></div>
                            </div>
                            <c:set var="status" value="${request.status}"/>
                            <%
                                String status = "";
                                status = status + pageContext.getAttribute("status");
                            %>
                            <%if (status.equalsIgnoreCase("Pending")) {%>
                            <div class="badge bg-danger text-wrap ">Status: ${request.status}</div>
                            <%} else if (status.equalsIgnoreCase("Successful")) {%>
                            <div class="badge bg-success text-wrap ">Status: ${request.status}</div>
                            <%} else {%>
                            <div class="badge bg-warning text-wrap ">Status: ${request.status}</div>
                            <%}%>


                        </td>
                        <td>
                            <%if (status.equalsIgnoreCase("Pending")) {%>
                            <input list="roomList" value="" name="roomId" id="roomId">
                            <datalist id="roomList">
                                <c:forEach items="${AllListRoom}" var="room">
                                    <option id="${room.typeName}/${room.price}$/Person:${room.guests}" value="${room.id}"></option>
                                </c:forEach>
                            </datalist>


                            <button type="submit">Selects room</button>
                            <%}%>
                        </td>
                </form>

            </c:forEach>
        </table>
    </div>
</div>
</body>

