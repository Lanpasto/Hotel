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
</head>
<body>
<div class="container mt-md-0">
    <form method="post" id="search" action="controller?action=orderRoomPage" class="order">
    <table class="table table-secondary">
            <thead class="table-dark ">
            <tr>
                <th class="input-text ">
                    <span> <fmt:message key="classSelect.numberOfPerson"
                                        bundle="${lang}"/></span>
                    <input list="guestForDataList" style="width: 30px" value="" name="guest" id="guest">
                    <datalist id="guestForDataList">
                        <option>1</option>
                        <option>2</option>
                        <option>3</option>
                        <option>4</option>
                    </datalist>
                    <span> <fmt:message key="classSelect.price"
                                        bundle="${lang}"/></span>
                    <input type="text" name="fromPrice" style="width: 60px" placeholder="0$">
                    <input type="text" name="priceTo" style="width: 60px" placeholder="3000$">
                <th class="input-text">
                    <span> <fmt:message key="classSelect.class"
                                        bundle="${lang}"/></span>
                    <input list="classOfRoomAb" value="" name="classOfRoom" id="classOfRoom" placeholder="Class">
                    <datalist id="classOfRoomAb">
                        <c:forEach items="${listCategoryForSorting}" var="type_of_room">
                            <option id="${type_of_room.id}" value="${type_of_room.type_of_room}"></option>
                        </c:forEach>
                    </datalist>

                </th>
                <th class="text-end">
                    <button type="submit" name="searchRoom" class="btn btn-outline-primary text-end">
                        <fmt:message key="classSelect.search"
                                     bundle="${lang}"/>
                    </button>

        </th>

        </tr>
        </thead>
        <tbody class="bg-opacity-25 bg-white ">
        <c:forEach items="${listOrders}" var="room">


        <tr>
            <td class="col-md-6"><img src="${pageContext.request.contextPath}${room.image}"
                                      style="height: 200px; width: 400px" alt="#"></td>
            <td class="col-md-5"><p class="bg-gradient" style="color: #06357a ">
                <p style="margin-top: 20px; color: #06357a"> Class: ${room.typeName }</p>
                <p class="bg-gradient" style="color: #06357a ">Number of quests: ${room.guests }</p>
                <p class="bg-gradient" style="color: #06357a ">Price: ${room.price}</p></td>
            <td class="col-md-1">
                <div class="input-text">
                    <form method="post" id="reserve" action="controller?action=makeOrder" class="order">
                        <span><fmt:message key="classSelect.stay"
                                           bundle="${lang}"/></span>
                        <input type="text" id="datefilter" name="datefilter" value=""
                               placeholder="DD/MM/YY - DD/MM/YY">

                        <%if (userSession != null) {%>
                        <button type="submit" name="room" style="margin-top: 150px" class="btn btn-success"

                                value="${room.id}">Reserve
                            <%}%>
                        </button>
                    </form>
                </div>


        </tbody>
        </tr>


        </c:forEach>

        </tbody>

    </table>
    <nav aria-label="Page navigation example">
        <ul class="pagination justify-content-center">
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
