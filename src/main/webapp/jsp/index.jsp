<%@include file="header.jsp" %>
<%@include file="background.jsp" %>
<%--<tf:title titleName="Home"/>--%>
<link href="${pageContext.request.contextPath}/css/homepage.css" rel="stylesheet">
<html>
<body >
<div class="container d-flex align-items-center justify-content-center position-relative flex-wrap">
    <div class="card d-flex position-relative flex-column">
        <div class='imgContainer'>
            <img src='${pageContext.request.contextPath}/images/hilton-port-moresby.jpg'>
        </div>
        <div class="content">
            <h2>Family Rooms</h2>
        </div>
    </div>
    <div class="card d-flex position-relative flex-column">
        <div class='imgContainer'>
            <img src='${pageContext.request.contextPath}/images/the-shady-rest-hotel.jpg'>
        </div>
        <div class="content">
            <h2>Cheep Rooms</h2>
        </div>
    </div>
    <div class="card d-flex position-relative flex-column">
        <div class='imgContainer'>
            <img src='${pageContext.request.contextPath}/images/lux.jpg'>
        </div>
        <div class="content">
            <h2>Luxury Rooms</h2>
        </div>
    </div>
</div>
</body>
</html>