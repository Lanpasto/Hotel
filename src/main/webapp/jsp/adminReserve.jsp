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
    <table class="table">
        <form >
            <thead class="table-primary">
            <tr>
                <th></th>
                <th></th>
                <th></th>
            </tr>
            </thead><c:forEach items="${listCategory}" var="room">
            <%
                String resp = "";
                resp = resp + pageContext.getAttribute("status");
            %>
            <% if ((resp).equals("no_available")) {%>
            <tbody class="bg-opacity-25 bg-white">
            <%--@elvariable id="listCategory" type="java.util.List"--%>

            <tr>

                <td><img src="${pageContext.request.contextPath}/images/hilton-port-moresby.jpg"
                         style="height: 250px; width: 600px" alt="#"></td>
                <td > <p class="text-bg-secondary">${room.classRoom }</td>

                <c:set var="status" value="${room.status}"/>
              <td>
                <form method="post" id="reserveOne" action="controller?action=reserve">
                    <button type="submit" name="room" class="btn btn-success"
                            value="${room.idRoom}">${room.status}</button>
                </form>
            </td>
                <%}else{%>


                <%}%>
            </tr>
            </c:forEach>
        </form>
        </tbody>
    </table>
</div>
</body>

