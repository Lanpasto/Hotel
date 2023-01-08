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
<div class="container mt-md-0">
    <table class="table table-secondary">
        <thead class="table-primary ">
        <tr>
            <th class="input-text">
                <span>Number of persons</span>
                <input type="text" placeholder="Guests"></th>
            </th>
            <th class="input-text"><span>Price</span>
                <input type="text" placeholder="Price"></th>
            <th class="billing">
                <span>Select Class</span>
                <select>
                    <option>Class</option>
                    <option>Luxury</option>
                    <option>Basics</option>
                    <option>Economy</option>
                </select>


        </tr>
        </thead>
        <tbody class="bg-opacity-25 bg-white ">
        <c:forEach items="${listCategory}" var="room">
            <c:set var="status" value="${room.status}"/>
            <tr>
                <td class="col-md-6"><img src="${pageContext.request.contextPath}${room.image}"
                                          style="height: 200px; width: 400px" alt="#"></td>
                <td class="col-md-5"><p class="bg-gradient" style="color: #06357a ">
                    <p style="margin-top: 20px; color: #06357a"> Class: ${room.typeName }</p>
                    <p class="bg-gradient" style="color: #06357a ">Available room now: ${room.count} </p>
                    <p class="bg-gradient" style="color: #06357a ">Number of quests: ${room.guests }</p>
                    <p class="bg-gradient" style="color: #06357a ">Price: ${room.price}</p>
                <td class="col-md-1">
                    <form method="post" id="reserveOne" action="controller?action=reserve">
                        <button type="submit" name="room" style="margin-top: 150px" class="btn btn-success"
                                value="${room.id}">Reserve
                        </button>
                    </form>
                </td>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>