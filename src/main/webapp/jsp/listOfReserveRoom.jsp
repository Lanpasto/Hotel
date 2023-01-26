<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@include file="header.jsp" %>
<%@include file="background.jsp" %>
<%--<tf:title titleName="MyReserve"/>--%>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
    <title></title>
</head>
<body>
<div class="container mt-3">
        <div class="bg-warning">
            <div class="navbar-nav">
                <div class="navbar-nav">
                    <label class="text-dark border-0 h1 text-center"> <fmt:message key="reserveUser.yourReserve"
                                                                                   bundle="${lang}"/></label></div>
                <div class="navbar-nav"><a class="text-white text-end" href="controller?action=ListOfRequest">
                    <h3 class="badge text-white  bg-danger text-wrap fs-5"><fmt:message key="reserveUser.myRequest"
                                                                                        bundle="${lang}"/></h3>
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

                            <%}if(status.equalsIgnoreCase("Waiting for paid")){%>
                    <tr class="table table-warning">
                    <td><img src="${pageContext.request.contextPath}${orders.image}"
                             style="height: 200px; width: 400px" alt="#"></td>
                    <td class="text-bg-primary">
                        <div><fmt:message key="reserveUser.price"
                                          bundle="${lang}"/>${orders.totalPrice}$
                        </div>
                        <div><fmt:message key="reserveUser.reservedate"
                                          bundle="${lang}"/><fmt:formatDate
                                value="${orders.dateOfSettlement}" pattern=" MM/dd/yyyy"/> -
                            <fmt:formatDate value="${orders.dateOfOut}" pattern="MM/dd/yyyy"/></div>
                        <div>${orders.status}</div>
                    </td>

                    <td class="text-end">
                        <form method="post" action="controller?action=payForm">
                            <button style="width: 100px" class="bg-success" name="roomId" value="${orders.roomId}"><fmt:message key="payment.paid" bundle="${lang}"/></button>
                            <label>
                                <input name="orderIdForConfirm" value="${orders.id}" hidden="hidden">
                            </label>
                            <label>
                                <input name="statusPay" value="process" hidden="hidden">
                            </label>
                        </form>
                    </td>
                    </tr>
                        <%} if (status.equals("Paid")){%>

                <tr class="table table-warning">
                    <td><img src="${pageContext.request.contextPath}${orders.image}"
                             style="height: 200px; width: 400px" alt="#"></td>
                    <td class="text-bg-danger">
                        <div><fmt:message key="reserveUser.price"
                                          bundle="${lang}"/>${orders.totalPrice}$
                        </div>
                        <div><fmt:message key="reserveUser.reservedate"
                                          bundle="${lang}"/><fmt:formatDate
                                value="${orders.dateOfSettlement}" pattern=" MM/dd/yyyy"/> -
                            <fmt:formatDate value="${orders.dateOfOut}" pattern="MM/dd/yyyy"/></div>
                        <div>${orders.status}</div>
                    </td>

                    <td>
                    </td>
                </tr>
                        <%}%>

                </c:forEach>
</div>
</div>
</body>