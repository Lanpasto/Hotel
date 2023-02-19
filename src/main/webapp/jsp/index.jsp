<%@include file="header.jsp" %>
<%@include file="background.jsp" %>
<%@ taglib prefix="tf" tagdir="/WEB-INF/tags"%>
<tf:title titleName="Hotel"/>
<link href="${pageContext.request.contextPath}/css/homepage.css" rel="stylesheet">
<html>
<body >
<div class="container d-flex align-items-center justify-content-center position-relative flex-wrap">
    <div class="card d-flex position-relative flex-column">
        <div class='imgContainer'>
            <img src='${pageContext.request.contextPath}/images/hilton-port-moresby.jpg' alt="">
        </div>
        <div class="content">
            <h2><fmt:message key="index.family" bundle="${lang}"/></h2>
        </div>
    </div>
    <div class="card d-flex position-relative flex-column">
        <div class='imgContainer'>
            <img src='${pageContext.request.contextPath}/images/the-shady-rest-hotel.jpg' alt="">
        </div>
        <div class="content">
            <h2><fmt:message key="index.cheep" bundle="${lang}"/></h2>
        </div>
    </div>
    <div class="card d-flex position-relative flex-column">
        <div class='imgContainer'>
            <img src='${pageContext.request.contextPath}/images/lux.jpg' alt="">
        </div>
        <div class="content">
            <h2><fmt:message key="index.luxury" bundle="${lang}"/></h2>
        </div>
    </div>
</div>
</body>
</html>