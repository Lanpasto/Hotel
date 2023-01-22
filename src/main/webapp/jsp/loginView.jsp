<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@include file="header.jsp" %>
<%@include file="background.jsp" %>
<%--
<tf:title titleName="Login"/>
--%>
<section class="vh-100">
    <body class="text-center">
    <div class="container-fluid ">
        <div class="row">
            <div class="col-sm-6 text-black " style="margin-left: 34%">
                <div class=" h-custom-2 px-5 ms-xl-4 mt-5 pt-5 pt-xl-0 mt-xl-n5 bg-dark "
                     style="width: 450px; height: 460px;border-radius: 15px;">

                    <form method="post" action="controller?action=loginCommand" class="register-form"
                          style="width: 23rem"
                          id="login-form">
                        <h3 class="fw-normal mb-3 pb-3 text-white" style="margin-top: 100px; letter-spacing: 1px;"><fmt:message key="login.Login"
                                                                                                                                bundle="${lang}"/></h3>
                        <div class="form-outline mb-4">
                            <input type="email"  id="email" name="email" placeholder="Login"  class="form-control form-control-lg"/>
                            <label class="form-label text-white" for="email"><fmt:message key="login.Login" bundle="${lang}"/></label>
                        </div>
                        <div class="form-outline mb-4">
                            <input type="password"  id="password" placeholder="Password" name="password" class="form-control form-control-lg"/>
                            <label class="form-label text-white" for="password"><fmt:message key="login.password"
                                bundle="${lang}"/></label>
                        </div>

                        <div class="pt-1 mb-4">
                            <button class="btn btn-primary btn-lg btn-block " type="submit"><fmt:message key="login.enter"
                                                                                                         bundle="${lang}"/></button>
                        </div>
                        <p class="text-white"><fmt:message key="login.haveAcc"
                                                           bundle="${lang}"/> <a href="controller?action=registerPage"
                                                                        class="link-info"><fmt:message key="login.here"
                                                                                                       bundle="${lang}"/></a></p>
                        <c:set var="message" value="${message}"/>
                        <p style="font-size: 16px;font-style: italic;color: red" id="message">${message}</p>
                    </form>

                </div>
            </div>
        </div>
    </div>
    </body>
</section>