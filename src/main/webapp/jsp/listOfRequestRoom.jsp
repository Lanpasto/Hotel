<%@ taglib prefix="c" uri="jakarta.tags.core" %>
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


    <div class="bg-warning">
        <div class="navbar-nav">
            <div class="navbar-nav">
                <label class="text-dark border-0 h1 text-center"><fmt:message key="requestUser.AllRequest"
                                                                              bundle="${lang}"/></label></div>
            <div class="navbar-nav"><a class="text-white text-start" href="controller?action=myReservePage">
                <h3 class="badge text-white  bg-danger text-wrap fs-5"><fmt:message key="requestUser.myreserve"
                                                                                    bundle="${lang}"/></h3>
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
            <tr class="table table-warning">

                <%if (status.equalsIgnoreCase("Waiting for confirmation")) {%>
                <td class="table-info">
                    <div> CreateRequest <fmt:formatDate value="${orders.dateOfCreateOrder}"
                                                        pattern="MM/dd/yyyy"/></div>
                    <div> dateOfSettlement <fmt:formatDate value="${orders.dateOfSettlement}"
                                                           pattern="MM/dd/yyyy"/></div>
                    <div> dateOfOut <fmt:formatDate value="${orders.dateOfOut}" pattern="MM/dd/yyyy"/></div>

                    <div>Price:${orders.totalPrice}</div>
                </td>
                <td>
                    <div class="badge bg-warning text-wrap ">Status: ${orders.status}</div>
                </td>
                <td>
                    <div class="btn-group">
                        <form method="post" action="controller?action=confirmRequestUser" class="register-form">
                            <div>
                                <button class="bg-success" name="roomId" value="${orders.roomId}"><fmt:message key="requestUser.confirm"
                                                                                                               bundle="${lang}"/></button>
                                <label>
                                    <input name="orderIdForConfirm" value="${orders.id}" hidden="hidden">
                                </label>
                            </div>
                        </form>
                        <form method="post" action="controller?action=rejectRequestUser" class="register-form">
                            <div>

                                <button class="bg-danger" name="orderId" value="${orders.id}"><fmt:message key="requestUser.reject"
                                                                                                           bundle="${lang}"/></button>
                            </div>

                        </form>
                    </div>
                </td>
            </tr>
            </tbody>
                    <%}if(status.equalsIgnoreCase("Waiting for paid request")){%>
            <td class="table-info">
                <div> CreateRequest <fmt:formatDate value="${orders.dateOfCreateOrder}"
                                                    pattern="MM/dd/yyyy"/></div>
                <div> dateOfSettlement <fmt:formatDate value="${orders.dateOfSettlement}"
                                                       pattern="MM/dd/yyyy"/></div>
                <div> dateOfOut <fmt:formatDate value="${orders.dateOfOut}" pattern="MM/dd/yyyy"/></div>

                <div>Price:${orders.totalPrice}</div>
            </td>
            <td>
                <div class="badge bg-warning text-wrap "><fmt:message key="statusOrder.status"
                                                                      bundle="${lang}"/>: ${orders.status}</div>
            </td>
            <td>
                <form method="post" action="controller?action=payForm" class="register-form">
                    <button class="bg-success" name="roomId" value="${orders.roomId}"><fmt:message key="requestUser.confirm"
                                                                                                   bundle="${lang}"/></button>
                    <label>
                        <input name="orderIdForConfirm" value="${orders.id}" hidden="hidden">
                    </label>
                    <label>
                        <input name="statusPay" value="process" hidden="hidden">
                    </label>
                </form>
            </td>
    </div>
</div>
<%}%>
</c:forEach>
</body>
