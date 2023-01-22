<%@include file="header.jsp" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%--
<tf:title titleName="Registration"/>
--%>
<html lang="eng">
<body class="text-center">
<div class="mask d-flex align-items-center h-100 gradient-custom-3">
    <div class="container h-100">
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col-12 col-md-9 col-lg-7 col-xl-6">
                <div class="card bg-dark" style="border-radius: 15px; margin-top: 100px; height: 930px">
                    <div class="card-body p-5">
                        <h2 class="text-uppercase text-center mb-5 text-white bg-dark"><fmt:message key="register.create"
                                                                                                    bundle="${lang}"/></h2>
                        <form method="post" action="controller?action=registerCommand" class="register-form">
                            <div class="form-outline mb-4">
                                <input type="firstName" placeholder="Danylo" id="firstName" name="firstName"
                                       class="form-control form-control-lg"/>
                                <label class="form-label text-white" for="password"><fmt:message key="register.fname"
                                                                                                 bundle="${lang}"/></label>
                            </div>
                            <div class="form-outline mb-4">
                                <input type="lastName" placeholder="Vincent" id="lastName" name="lastName"
                                       class="form-control form-control-lg"/>
                                <label class="form-label text-white" for="password"><fmt:message key="register.lname"
                                                                                                 bundle="${lang}"/></label>
                            </div>
                            <div class="form-outline mb-4">
                                <input type="email" id="email" placeholder="zelenskiy666@gmail.com" name="email"
                                       class="form-control form-control-lg"/>
                                <label class="form-label text-white" for="email"><fmt:message key="register.email"
                                                                                              bundle="${lang}"/></label>
                            </div>
                            <div class="form-outline mb-4">
                                <input type="password" id="password" placeholder="Password" name="password"
                                       class="form-control form-control-lg"/>
                                <label class="form-label text-white" for="password"><fmt:message key="register.password"
                                                                                                 bundle="${lang}"/></label>
                            </div>

                            <div class="form-outline mb-4">
                                <input type="password" id="repeatPassword" placeholder="Repeat password"
                                       name="repeatPassword"
                                       class="form-control form-control-lg"/>
                                <label class="form-label text-white" for="password"><fmt:message key="register.repeat"
                                                                                                 bundle="${lang}"/></label>
                            </div>
                            <div class="d-flex justify-content-center">
                                <button type="submit" name="signup" id="signup"
                                        class="btn btn-primary btn-block btn-lg gradient-custom-4 text-body"><fmt:message key="register.enter"
                                                                                                                          bundle="${lang}"/>
                                </button>
                            </div>
                            <p class="text-center text-muted mt-5 mb-0 bg-dark"><fmt:message key="register.haveAcc"
                                                                                             bundle="${lang}"/> <a href="controller?action=loginpage"
                                            class="fw-bold text-info"><u><fmt:message key="register.here"
                                                                                      bundle="${lang}"/></u></a></p>
                        </form>
                        <c:set var="message" value="${message}"/>
                        <p style="font-size: 16px;font-style: italic;color: red" id="message">${message}</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<style> body {
    background-size: 100%;
    background-image: url("${pageContext.request.contextPath}/images/wallbig.png");
}</style>
</body>
</html>
