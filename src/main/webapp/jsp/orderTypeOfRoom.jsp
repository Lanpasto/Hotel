<%--@elvariable id="type_of_room" type="Class50"--%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@include file="header.jsp" %>
<%@include file="background.jsp" %>
<html>
<div class="container mt-md-2">
<table class="table table-secondary" text-muted>
<tbody s class="bg-primary bg-opacity-25">
<c:forEach items="${listCategory}" var="type_of_room">
    <%
        String resp = "";
        resp = resp + String.valueOf(pageContext.getAttribute("status"));
    %>
    <tr class>
        <td><img src="${pageContext.request.contextPath}${type_of_room.image}"
                 style="height: 200px; width: 400px" alt="#"></td>
        <td > <p class="" style="color: #06357a ;font-size: 20px">Class: ${type_of_room.type_of_room}
            <p class="" style="color: #101214 ;font-size: 20px">Number of quest: ${type_of_room.guests}</td>

 <td >
        <form method="post" id="reserveOne" action="controller?action=orderRoomPage">
            <button style="margin-top: 150px; margin-left: 190px" type="submit" name="room" class="btn btn-success"
                    value="${type_of_room.id}">Go to Reserve</button>
        </form>
    </td>
    </tr>
</c:forEach>
</table>
</div>
</body>
</tbody>
</html>
