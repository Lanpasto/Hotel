<%@include file="header.jsp" %>
<div class="card align-content-center" style="width: 300px;margin-top: 100px;margin-left: 39%;border: transparent">
    <div class="card-header h5 text-white bg-primary">Password Reset</div>
    <div class="card-body px-5 bg-dark" style="">
        <p class="card-text py-2 text-white">
            Enter your email address and we'll send you an email with instructions to reset your password.
        </p>
        <div class="form-outline">
            <input type="email" id="typeEmail" class="form-control my-3 text"/>
            <label class="form-label text-white" for="typeEmail">Email input</label>
        </div>
        <style > body {
            background-size: 100%;
            background-image: url("${pageContext.request.contextPath}/images/wallbig.png");

        }</style>
        <a href="#" class="btn btn-primary w-100">Reset password</a>
        <div class="d-flex justify-content-between mt-4 text-info">
            <a class="text-info" href="controller?action=loginpage">Login</a>
            <a class="text-info" href="controller?action=registerPage">Register</a>
        </div>
    </div>
</div>