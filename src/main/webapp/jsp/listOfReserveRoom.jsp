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

    <form>
        <div class="bg-warning">
            <div class="navbar-nav">
                <div class="navbar-nav">
                    <label class="text-dark border-0 h1 text-center">Your Reserve</label></div>
                <div class="navbar-nav"><a class="text-white text-end" href="controller?action=ListOfRequest">
                    <h3 class="badge text-white  bg-danger text-wrap fs-5">My Request</h3>
                </a>
                </div>
            </div>
            <tbody class="">
            <table class="table">

                <c:forEach items="${ordersRequestListUser}" var="orders">
                    <c:set var="status" value="${orders.status}"/>
                        <%
                            String status = "";
                            status = status + pageContext.getAttribute("status");
                        %>
                        <%if (status.equals("Waiting for confirmation")) {%>

                        <%}if (status.equals("Pending")) {%>

                        <%} if (status.equals("Successful")){%>

                <tr class="table table-warning">
                    <td><img src="${pageContext.request.contextPath}${orders.image}"
                             style="height: 200px; width: 400px" alt="#"></td>
                    <td>
                        <div>Price:${orders.totalPrice}$</div>
                        <div>Reserve date:<fmt:formatDate
                                value="${orders.dateOfSettlement}" pattern=" MM/dd/yyyy"/> -
                            <fmt:formatDate value="${orders.dateOfOut}" pattern="MM/dd/yyyy"/></div>
                        <div>${orders.status}</div>
                    </td>

                    <td>
                    </td>
                </tr>
                        <%}%>

                </c:forEach>
    </form>
    </tbody>
    </table>
</div>
</body>