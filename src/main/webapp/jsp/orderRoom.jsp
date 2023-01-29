<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@include file="header.jsp" %>
<%@include file="background.jsp" %>
<%--
<tf:title titleName="RoomList"/>
--%>
<head>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://use.fontawesome.com/releases/v5.7.2/css/all.css"></script>
    <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css"/>
    <script type="text/javascript" src="https://cdn.jsdelivr.net/jquery/latest/jquery.min.js"></script>
    <script type="text/javascript" src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
    <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script>
    <title></title>
</head>
<body>
<%
    int fromPrice;
    int priceTo;

        fromPrice = (int) session.getAttribute("fromPrice");
        priceTo = (int) session.getAttribute("priceTo");

%>

<div class="container mt-md-0">
    <form method="post" id="search" action="controller?action=orderRoomPage&fromPrice=${fromPrice}&priceTo=${priceTo}" class="order">
        <table class="table table-secondary">
            <c:set var="message" value="${message}"/>
            <p style="font-size: 16px;font-style: italic;color: red" id="message">${message}</p>
            <thead class="table-dark ">
            <tr>
                <th class="input-text ">
                    <span> <fmt:message key="classSelect.numberOfPerson" bundle="${lang}"/></span>
                    <label for="guest"></label><input list="guestForDataList" style="width: 30px" value="" name="guest" id="guest">
                    <datalist id="guestForDataList">
                        <option>1</option>
                        <option>2</option>
                        <option>3</option>
                        <option>4</option>
                    </datalist>
                    <span> <fmt:message key="classSelect.price" bundle="${lang}"/></span>


                    <label>
                        <%if (fromPrice == 0) {%>
                        <input type="text" name="fromPrice" id="fromPrice" style="width: 60px" placeholder="0$" value="">
                        <%} else {%>
                        <input type="text" name="fromPrice" id="fromPrice" style="width: 60px" placeholder="0$" value="${fromPrice}">
                        <%}%>


                        <%if (Objects.equals(priceTo, "0")) {%>
                        <input type="text" name="priceTo" id="priceTo" style="width: 60px" placeholder="3000$" value="">
                        <%} else {%>
                        <input type="text" name="priceTo" id="priceTo" style="width: 60px" placeholder="3000$" value="${priceTo}">
                        <%}%>




                <th class="input-text">
                    <span> <fmt:message key="classSelect.class" bundle="${lang}"/></span>
                <label for="classOfRoom"></label><input list="classOfRoomAb" value="" name="classOfRoom" id="classOfRoom" placeholder="Class">
                    <datalist id="classOfRoomAb">
                        <%--@elvariable id="listCategoryForSorting" type="java.util.List"--%>
                        <c:forEach items="${listCategoryForSorting}" var="type_of_room">
                            <option id="${type_of_room.id}" value="${type_of_room.type_of_room}"></option>
                        </c:forEach>
                    </datalist>
                </th>
                <th class="text-end">
                    <button type="submit" name="searchRoom" class="btn btn-outline-primary text-end">
                        <fmt:message key="classSelect.search" bundle="${lang}"/>
                    </button>
                </th>
            </tr>
            </thead>
            <tbody class="bg-opacity-25 bg-white ">
            <%--@elvariable id="listOrders" type="java.util.List"--%>
            <c:forEach items="${listOrders}" var="room">
            <tr>
                <td class="col-md-6"><img src="${pageContext.request.contextPath}${room.image}" style="height: 200px; width: 400px" alt="#"></td>
                <td class="col-md-5"><p class="bg-gradient" style="color: #06357a ">
                    <p style="margin-top: 20px; color: #06357a"> Class: ${room.typeName }</p>
                    <p class="bg-gradient" style="color: #06357a ">Number of quests: ${room.guests }</p>
                    <p class="bg-gradient" style="color: #06357a ">Price: ${room.price}</p></td>


                    <div class="input-text">
                        <td class="col-md-1">
                            <form method="post" id="reserve1" action="controller?action=makeOrder" class="order">
                        <span><fmt:message key="classSelect.stay" bundle="${lang}"/></span>
                            <input type="text" id="datefilter" name="datefilter" value=""
                                   placeholder="DD/MM/YY - DD/MM/YY">
                                ${room.id}
                            <%if (userSession != null) {%>
                            <button type="submit" name="room" style="margin-top: 150px" class="btn btn-success" value="${room.id}"><fmt:message key="classSelect.reserve" bundle="${lang}"/>
                            </button>
                            <%--@elvariable id="message" type=""--%>
                            <c:set var="message" value="${message}"/>
                            <p style="font-size: 16px;font-style: italic;color: red" id="message">${message}</p>
                            <%}%>
                            </form>
                        </td>
                    </div>

            </tr>
            </tbody>
            </c:forEach>
        </table>
        <nav aria-label="Page navigation example">
            <ul class="pagination justify-content-center">
                <%--@elvariable id="currentPage" type=""--%>
                <c:if test="${currentPage != 1}">
                    <li class="page-item">
                        <button type="submit" name="action1" value="${currentPage - 1}" style=" color: black;"
                                formaction="controller?action=orderRoomPage&page=${currentPage - 1}" class="page-link">
                            <fmt:message key="classSelect.previous"
                                         bundle="${lang}"/>
                        </button>
                    </li>
                </c:if>

                <%--For displaying Page numbers. The when condition does not display
                            a link for the current page--%>
                <table>
                    <tr>
                        <%--@elvariable id="noOfPages" type="Class50"--%>
                        <c:forEach begin="1" end="${noOfPages}" var="i">
                            <c:choose>
                                <c:when test="${currentPage eq i}">
                                    <li class="page-item">

                                        <button type="submit" name="action1" value="${i}"
                                                class="page-link">${i}</button>
                                    </li>
                                </c:when>
                                <c:otherwise>
                                    <li class="page-item">

                                        <button type="submit" name="action1" value="${i}" style=" color: black;"
                                                formaction="controller?action=orderRoomPage&page=${i}"
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
                                formaction="controller?action=orderRoomPage&page=${currentPage + 1}"
                                class="page-link"><fmt:message key="classSelect.next" bundle="${lang}"/>
                        </button>
                    </li>
                </c:if>
            </ul>
        </nav>
    </form>
</div>
</body>

<script type="text/javascript">
    $(function () {

        $('input[name="datefilter"]').daterangepicker({
            minDate: new Date(),
            autoUpdateInput: false,
            locale: {
                cancelLabel: 'Clear'
            }
        });
        $('input[name="datefilter"]').on('apply.daterangepicker', function (ev, picker) {
            $(this).val(picker.startDate.format('MM/DD/YYYY') + ' - ' + picker.endDate.format('MM/DD/YYYY'));
        });

        $('input[name="datefilter"]').on('cancel.daterangepicker', function (ev, picker) {

            $(this).val('');
        });

    });
</script>
