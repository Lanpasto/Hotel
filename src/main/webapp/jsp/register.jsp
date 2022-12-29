<%@include file="header.jsp" %>
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
<link rel="canonical" href="https://getbootstrap.com/docs/5.2/examples/headers/">
<div class="mask d-flex align-items-center h-100 gradient-custom-3">
    <div class="container h-100">
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col-12 col-md-9 col-lg-7 col-xl-6">
                <div class="card bg-dark" style="border-radius: 15px; margin-top: 100px; height: 930px">
                    <div class="card-body p-5">
                        <h2 class="text-uppercase text-center mb-5 text-white bg-dark" >Create an account</h2>

                        <form>

                            <div class="form-outline mb-4 ">
                                <input type="text" id="form3Example1cg" class="form-control form-control-lg"/>
                                <label class="form-label text-white" for="form3Example1cg">Your First Name</label>
                            </div>
                            <div class="form-outline mb-4">
                                <input type="text" id="form3Example2cg" class="form-control form-control-lg"/>
                                <label class="form-label text-white" for="form3Example1cg">Your Last Name</label>
                            </div>

                            <div class="form-outline mb-4">
                                <input type="email" id="form3Example3cg" class="form-control form-control-lg"/>
                                <label class="form-label text-white" for="form3Example3cg">Your Email</label>
                            </div>

                            <div class="form-outline mb-4">
                                <input type="password" id="form3Example4cg" class="form-control form-control-lg"/>
                                <label class="form-label text-white" for="form3Example4cg">Password</label>
                            </div>
                            <style > body {
                                background-size: 100%;
                                background-image: url("${pageContext.request.contextPath}/images/wallbig.png");

                            }</style>
                            <div class="form-outline mb-4">
                                <input type="password" id="form3Example4cdg" class="form-control form-control-lg"/>
                                <label class="form-label text-white" for="form3Example4cdg">Repeat your password</label>
                            </div>

                            <div class="d-flex justify-content-center">
                                <button type="button"
                                        class="btn btn-primary btn-block btn-lg gradient-custom-4 text-body">Register
                                </button>
                            </div>

                            <p class="text-center text-muted mt-5 mb-0 bg-dark" >Have already an
                                account? <a href="controller?action=loginpage"
                                            class="fw-bold text-info"><u>Login here</u></a></p>

                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</section>