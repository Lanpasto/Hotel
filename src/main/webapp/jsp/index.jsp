<%@include file="header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>

</head>
<style > body {
    background-size: 100%;
    background-image: url("${pageContext.request.contextPath}/images/wallbig.png");

}</style>
<div>
    <div>
        <button style="background: transparent; border: transparent" href="controller?action=registerPage">
            <img src="${pageContext.request.contextPath}/images/the-shady-rest-hotel.jpg" alt="buttonpng"
                 style="width: 471px;height: 227px;border-radius: 15px;"/>
        </button>
    </div>
<div>
    <button style="background: transparent; border: transparent">

        <img src="${pageContext.request.contextPath}/images/hilton-port-moresby.jpg" alt="buttonpng"
             style="width: 472px;height: 227px;border-radius: 15px;"/>
    </button>
</div>
<div>
    <button style="background: transparent; border: transparent">
        <img src="${pageContext.request.contextPath}/images/aboutus.png"
             style="width: 472px;height: 227px;border-radius: 15px;"/>
    </button>
</div>
<body>

</body>
</html>