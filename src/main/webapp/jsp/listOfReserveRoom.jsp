<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@include file="header.jsp" %>
<%@include file="background.jsp" %>
<tf:title titleName="MyReserve"/>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
    <title></title>
</head>
<body>
<div class="container mt-3">
    <div style="background-color: rgba(72, 191, 128, 0.3); padding: 20px; border-radius: 10px;">
        <div class="navbar-nav">
            <div class="navbar-nav">
                <label class="text-white h1 text-center"> <fmt:message key="reserveUser.yourReserve" bundle="${lang}"/></label>
            </div>
            <div class="navbar-nav">
                <a class="text-white text-end" href="controller?action=ListOfRequest">
                    <h3 class="badge text-white text-wrap fs-5 p-3" style="background-color: rgba(40, 167, 69, 0.7); border-radius: 10px;">
                        <fmt:message key="reserveUser.myRequest" bundle="${lang}"/></h3>
                </a>
            </div>
        </div>

        <table class="table table-success">
            <c:forEach items="${ordersRequestListUser}" var="orders">
                <c:set var="status" value="${orders.status}"/>
                <%
                    String status = "";
                    status = status + pageContext.getAttribute("status");
                %>
    <%if(status.equalsIgnoreCase("Waiting for paid")){%>
                <table class="table table-warning">
                    <tr>
                        <th>Room Image</th>
                        <th>Details</th>
                        <th></th>
                    </tr>
                    <tr>
                        <td>
                            <img src="${pageContext.request.contextPath}${orders.image}"
                                 style="height: 200px; width: 400px" alt="#">
                        </td>
                        <td>
                            <div style="background-color: rgba(182,39,39,0.5); padding: 20px; border-radius: 10px;">
                                <p style="font-size: 20px; font-weight: bold; color: #0e0e0e;">
                                    <fmt:message key="reserveUser.price" bundle="${lang}"/>: ${orders.totalPrice}$
                                </p>
                                <p style="font-size: 16px; color: #343a40;">
                                    <fmt:message key="reserveUser.reservedate" bundle="${lang}"/>:
                                        ${f:formatLocalDateTime(orders.dateOfSettlement, " MM/dd/yyyy")} -
                                        ${f:formatLocalDateTime(orders.dateOfOut, " MM/dd/yyyy")}
                                </p>
                                <p style="font-size: 26px; color: #842029;">
                                        ${orders.status}
                                </p>
                            </div>
                        </td>
                        <td class="text-center">
                            <form method="post" action="controller?action=payForm">
                                <br><br>
                                <button style="width: 100px; border: 1px solid black; font-size: 16px;" class="btn btn-success" name="roomId" value="${orders.roomId}">
                                    <fmt:message key="payment.paid" bundle="${lang}"/>
                                </button>
                                <br><br>
                                <input name="orderIdForConfirm" value="${orders.id}" type="hidden">
                                <input name="statusPay" value="process" type="hidden">
                            </form>
                        </td>
                    </tr>
                </table>


                <%} if (status.equals("Paid")){%>

                <table class="table table-primary">
                    <tr>
                        <th>Room Image</th>
                        <th>Details</th>
                    </tr>
                    <tr>
                        <td>
                            <img src="${pageContext.request.contextPath}${orders.image}"
                                 style="height: 200px; width: 400px" alt="#">
                        </td>

                        <td>
                            <div style="background-color: rgb(87,229,248); padding: 20px; border-radius: 10px;">
                                <p style="font-size: 20px; font-weight: bold; color: #111111;">
                                    <fmt:message key="reserveUser.price" bundle="${lang}"/>: ${orders.totalPrice}$
                                </p>
                                <p style="font-size: 16px; color: #6c757d;">
                                    <fmt:message key="reserveUser.reservedate" bundle="${lang}"/>:
                                        ${f:formatLocalDateTime(orders.dateOfSettlement, " MM/dd/yyyy")} -
                                        ${f:formatLocalDateTime(orders.dateOfOut, " MM/dd/yyyy")}
                                </p>
                                <p style="font-size: 26px; color: #28a745;">
                                        ${orders.status}
                                </p>
                            </div>
                        </td>


                    </tr>
                </table>

                <%}%>

                </c:forEach>
    </table>
</div>
</div>

</body>