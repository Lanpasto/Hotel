<%@include file="header.jsp" %>
<%@include file="background.jsp" %>
<tf:title titleName="MyRequest"/>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
    <title></title>
</head>
<body>
<div class="container mt-3">

        <div class="container mt-3">
            <div style="background-color: rgba(72, 191, 128, 0.3); padding: 20px; border-radius: 10px;">
                <div class="navbar-nav">
                    <div class="navbar-nav">
                        <label class="text-white h1 text-center"> <fmt:message key="requestUser.AllRequest" bundle="${lang}"/></label>
                    </div>
                    <div class="navbar-nav">
                        <a class="text-white text-start" href="controller?action=myReservePage">
                            <h3 class="badge text-white text-wrap fs-5 p-3" style="background-color: rgba(40, 167, 69, 0.7); border-radius: 10px;">
                                <fmt:message key="requestUser.myreserve" bundle="${lang}"/></h3>
                        </a>
                    </div>
                </div>


        <table class="table ">
            <tbody class="">
            <%--@elvariable id="ordersList" type="java.util.List"--%>
            <c:forEach items="${ordersList}" var="orders">
            <c:set var="status" value="${orders.status}"/>
            <%
                String status = "";
                status = status + pageContext.getAttribute("status");
            %>
            <tr style="background: #ced4da; border-top-right-radius: 12px">
                <%if (status.equalsIgnoreCase("Waiting for confirmation")) {%>
                <td class="table-info">
                    <div>  <fmt:message key="listRequest.create" bundle="${lang}"/>:  ${f:formatLocalDateTime(orders.dateOfCreateOrder, " MM/dd/yyyy")}</div>
                    <div>  <fmt:message key="listRequest.settle" bundle="${lang}"/>:  ${f:formatLocalDateTime(orders.dateOfSettlement, " MM/dd/yyyy")}</div>
                    <div>  <fmt:message key="listRequest.out" bundle="${lang}"/>:  ${f:formatLocalDateTime(orders.dateOfOut, " MM/dd/yyyy")}</div>

                    <div><fmt:message key="classSelect.price" bundle="${lang}"/>:${orders.totalPrice}$</div>
                </td>
                <td class="text-center">
                    <div class="badge bg-warning text-wrap "><fmt:message key="statusOrder.status" bundle="${lang}"/> : ${orders.status}</div>
                </td>
                <td class="text-center">
                    <div class="btn-group">
                        <form method="post" action="controller?action=confirmRequestUser" class="register-form">
                            <div>
                                <label>
                                    <input name="orderIdForConfirm" value="${orders.id}" hidden="hidden">
                                </label>
                                <button class="btn btn-success" name="roomId" value="${orders.roomId}"><fmt:message key="requestUser.confirm"
                                                                                                                    bundle="${lang}"/></button>
                            </div>
                        </form>
                        <form method="post" action="controller?action=rejectRequestUser" class="register-form">
                            <div>
                                <button class="btn btn-danger" name="orderId" value="${orders.id}"><fmt:message key="requestUser.reject"
                                                                                                                bundle="${lang}"/></button>
                            </div>

                        </form>
                    </div>
                </td>
            </tr>
            </tbody>

        <%}%>
</c:forEach>
</body>
