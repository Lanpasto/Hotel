<%@include file="header.jsp" %>
<%@include file="background.jsp" %>
<tf:title titleName="Order"/>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="post" action="controller?action=confirmCommand" class="register-form">
<table class="table table-secondary">
<c:forEach items="${AllListRoomForSelect}" var="room">

            <tbody class="bg-opacity-25 bg-white">
            <tr>
                <td class="col-md-6"><img src="${pageContext.request.contextPath}${room.image}"
                                          style="height: 200px; width: 400px" alt="#"></td>
                <td>
                    <div class=" badge text-dark text-bg-success"><fmt:message key="classSelect.class"
                                                                               bundle="${lang}"/>:${room.typeId}</div>
                    <div class="badge text-dark bg-info text-wrap "><fmt:message
                            key="classSelect.numberOfPerson" bundle="${lang}"/>: ${room.guests}</div>

                </td>
                <td>
                        <button type="submit" name="room" style="margin-top: 150px" class="btn btn-success "
                                value="${room.id}"><fmt:message key="admin.submit"
                                                                bundle="${lang}"/>
                        </button>
                    </td>


   </c:forEach>
</table>
</form>
