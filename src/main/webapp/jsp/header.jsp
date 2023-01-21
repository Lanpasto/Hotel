<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="locale" var="lang"/>

<html lang="en">
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
<%
    Integer userSession = null;
    if (session.getAttribute("role") != null)
        userSession = Integer.valueOf(session.getAttribute("role").toString());
%>
<nav class="navbar navbar-expand-sm bg-dark bg-opacity-75 " style="">
    <div class="collapse navbar-collapse navbar-nav">
        <img src="${pageContext.request.contextPath}/images/hotel1.png"
             alt="Logo image" style="height:50px;width: 90px ">
        <div>
            <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
                <li><a href="controller?action=changeLang&lang=en" class="nav-link px-2 text-white" >en</a></li>
                <li><a href="controller?action=changeLang&lang=ua" class="nav-link px-2 text-white" >ua</a></li>
            </ul>
        </div>
    </div>
    <div class="collapse navbar-collapse">
    <div class="navbar-nav" style="font-size: 24px">
        <a class="nav-link text-white" href="controller?action=indexPage">Hotel</a>
    </div>
    <div class="navbar-nav" style="font-size: 16px">
        <a class="nav-link text-white" href="controller?action=aboutUsPage">About us</a>
    </div>
    <div class="navbar-nav" style="font-size: 16px">
        <a class="nav-link text-white" href="controller?action=orderRoomPage">Choose room</a></div>
    <%if (userSession != null) {%>
    <div class="navbar-nav" style="font-size: 16px">
        <a class="nav-link text-white" href="controller?action=requestPage">Make request</a></div>
    <%}%>
</div>
    <div class="navbar-nav ">
        <%if (userSession == null) {%>
        <a href="controller?action=loginpage">
            <button type="button" class="btn btn-outline-light me-2">Login</button>
        </a>
        <a href="controller?action=registerPage">
            <button type="button" class="btn btn-outline-light me-2">Sign-up</button>
        </a>
        <%} else if (userSession == 2) {%>
        <a href="controller?action=adminReservePage">
            <button type="button" class="btn btn-outline-light me-2">Admin Reserve</button>
        </a>
        <a href="controller?action=logout">
            <button type="button" class="btn btn-outline-light me-2">Logout</button>
        </a>
        <%} else {%>
        <a href="controller?action=myReservePage">
            <button type="button" class="btn btn-outline-light me-2">Reserve</button>
        </a>
        <a href="controller?action=logout">
            <button type="button" class="btn btn-outline-light me-2">Logout</button>
        </a>
        <%}%>

    </div>
</nav>
</html>