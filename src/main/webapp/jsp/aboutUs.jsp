<%@ taglib prefix="tf" tagdir="/WEB-INF/tags" %>
<%@include file="header.jsp" %>
<%@include file="background.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tf" tagdir="/WEB-INF/tags"%>
<tf:title titleName="AboutUs"/>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
<body>
<html>
<head>

    <div class="p-3 bg-info bg-opacity-10 border border-info border-start-0 rounded-end text-white bg-dark">
       <fmt:message key="aboutUs.text"
                    bundle="${lang}"/>
    </div>
    <div class="container mt-3">
        <button type="button" class="btn btn-dark col-3" style="margin-left: 37%;width: 420px" data-bs-toggle="modal"
                data-bs-target="#myModal">
            <h3><fmt:message key="aboutUs.contact"
                             bundle="${lang}"/></h3>
        </button>
    </div>
    <div class="modal" id="myModal">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="container d-flex justify-content-center mt-5">
                    <div class="card p-4">
                        <div class="form" id="form">
                            <h5 class="mb-0">Send Message</h5>
                            <div class="form mt-3"><input type="text" class="form-control input-text" name=""
                                                          placeholder="Name"> <textarea
                                    class="form-control mt-2 text-area" rows="4"
                                    placeholder="about this invoice"></textarea></div>
                        </div>
                        <div class="mt-2">
                            <button class="btn btn-primary btn-block invoice-btn" onclick="myForm();">Send Invoice
                            </button>
                        </div>
                    </div>
                    <div class="text-center display-none py-5" id="sent"><span class="dots"> <i class="fa fa-check"></i> </span>
                    </div>
                </div>
            </div>

        </div>
    </div>
    </div>

</head>
</html>
</body>
