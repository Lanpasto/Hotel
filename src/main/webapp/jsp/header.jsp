<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="tf" tagdir="/WEB-INF/tags" %>
<fmt:requestEncoding value="UTF-8"/>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="locale" var="lang"/>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/TLDs/message.tld" prefix="customtag" %>  <%--custom tag--%>
<%@taglib uri="/WEB-INF/TLDs/functions.tld" prefix="f" %>         <%--custom tag--%>

<html lang="en">
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Open+Sans&display=swap" rel="stylesheet">
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
            <customtag:message/>
        </div>
    </div>
    <div class="collapse navbar-collapse">
    <div class="navbar-nav" style="font-size: 24px">
        <a class="nav-link text-white" href="controller?action=indexPage"><fmt:message key="header.hotel"
                                                                                       bundle="${lang}"/></a>
    </div>
    <div class="navbar-nav" style="font-size: 16px">
        <a class="nav-link text-white" href="controller?action=aboutUsPage"><fmt:message key="header.aboutUs"
                                                                                         bundle="${lang}"/></a>
    </div>
    <div class="navbar-nav" style="font-size: 16px">
        <a class="nav-link text-white" href="controller?action=orderRoomPage"><fmt:message key="header.chooseRoom"
                                                                                           bundle="${lang}"/></a></div>
    <%if (userSession != null) {%>
    <div class="navbar-nav" style="font-size: 16px">
        <a class="nav-link text-white" href="controller?action=requestPage"><fmt:message key="header.makeRequest"
                                                                                         bundle="${lang}"/></a></div>
    <%}%>
</div>
    <div class="navbar-nav ">
        <%if (userSession == null) {%>
        <a href="controller?action=loginpage">
            <button type="button" class="btn btn-outline-light me-2"><fmt:message key="header.login"
                                                                                  bundle="${lang}"/></button>
        </a>
        <a href="controller?action=registerPage">
            <button type="button" class="btn btn-outline-light me-2"><fmt:message key="header.signIn"
                                                                                  bundle="${lang}"/></button>
        </a>
        <%} else if (userSession == 2) {%>
        <a href="controller?action=adminReservePage">
            <button type="button" class="btn btn-outline-light me-2"><fmt:message key="header.adminReserve"
                                                                                  bundle="${lang}"/></button>
        </a>
        <a href="controller?action=logout">
            <button type="button" class="btn btn-outline-light me-2"><fmt:message key="header.logout"
                                                                                  bundle="${lang}"/></button>
        </a>
        <%} else {%>
        <a href="controller?action=myReservePage">
            <button type="button" class="btn btn-outline-light me-2"><fmt:message key="header.myReserve"
                                                                                  bundle="${lang}"/></button>
        </a>
        <a href="controller?action=logout">
            <button type="button" class="btn btn-outline-light me-2"><fmt:message key="header.logout"
                                                                                  bundle="${lang}"/></button>
        </a>
        <%}%>

    </div>
</nav>
</html>