<%@include file="header.jsp" %>
<section class="vh-100">
    <div class="container-fluid ">
        <div class="row">
            <div class="col-sm-6 text-black " style="margin-left: 34%">

                <div class="px-5 ms-xl-4 ">
                    <i class="fas fa-crow fa-2x me-3 pt-5 mt-xl-4" style="color: #709085;"></i>
                </div>

                <div class=" h-custom-2 px-5 ms-xl-4 mt-5 pt-5 pt-xl-0 mt-xl-n5 bg-dark "
                     style="width: 450px; height: 460px;border-radius: 15px;">

                    <form method="post" action="controller?action=login" class="register-form" style="width: 23rem"
                          id="login-form">

                        <h3 class="fw-normal mb-3 pb-3 text-white" style="margin-top: 100px; letter-spacing: 1px;">Log
                            in</h3>

                        <div class="form-outline mb-4">
                            <input type="email" id="form2Example18" class="form-control form-control-lg"/>
                            <label class="form-label text-white" for="form2Example18">Login</label>
                        </div>

                        <div class="form-outline mb-4">
                            <input type="password" id="form2Example28" class="form-control form-control-lg"/>
                            <label class="form-label text-white" for="form2Example28">Password</label>
                        </div>
                        <div class="pt-1 mb-4">
                            <button class="btn btn-primary btn-lg btn-block " type="button">Enter</button>
                        </div>

                        <p class="small mb-5 pb-lg-2 "><a class="text-info" href="controller?action=forgotPage">Forgot
                            password?</a></p>
                        <p class="text-white">Don't have an account? <a href="controller?action=registerPage"
                                                                        class="link-info">Register here</a></p>
                    </form>
                    <style> body {
                        background-size: 100%;
                        background-image: url("${pageContext.request.contextPath}/images/wallbig.png");

                    }</style>
                </div>
            </div>
        </div>
    </div>
</section>