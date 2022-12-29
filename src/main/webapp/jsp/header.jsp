<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
<link rel="canonical" href="https://getbootstrap.com/docs/5.2/examples/headers/">
<style>
    .bd-placeholder-img {
        font-size: 1.125rem;
        text-anchor: middle;
        -webkit-user-select: none;
        -moz-user-select: none;
        user-select: none;
    }

    @media (min-width: 768px) {
        .bd-placeholder-img-lg {
            font-size: 3.5rem;
        }
    }

    .b-example-divider {
        height: 3rem;
        background-color: rgba(0, 0, 0, .1);
        border: solid rgba(0, 0, 0, .15);
        border-width: 1px 0;
        box-shadow: inset 0 .5em 1.5em rgba(0, 0, 0, .1), inset 0 .125em .5em rgba(0, 0, 0, .15);
    }

    .b-example-vr {
        flex-shrink: 0;
        width: 1.5rem;
        height: 100vh;
    }

    .bi {
        vertical-align: -.125em;
        fill: currentColor;
    }

    .nav-scroller {
        position: relative;
        z-index: 2;
        height: 2.75rem;
        overflow-y: hidden;
    }

    .nav-scroller .nav {
        display: flex;
        flex-wrap: nowrap;
        padding-bottom: 1rem;
        margin-top: -1px;
        overflow-x: auto;
        text-align: center;
        white-space: nowrap;
        -webkit-overflow-scrolling: touch;
    }

    html,
    body {
        height: 100%;
    }

    body .text-center {
        display: flex;
        align-items: center;
        padding-top: 40px;
        padding-bottom: 40px;
        background-color: #f5f5f5;
    }

    .form-signin {
        max-width: 500px;
        padding: 50px;
    }

    .form-signin .form-floating:focus-within {
        z-index: 2;
    }

    .form-signin input[type="email"] {
        margin-bottom: -1px;
        border-bottom-right-radius: 0;
        border-bottom-left-radius: 0;
    }

    .form-signin input[type="password"] {
        margin-bottom: -1px;
        border-top-left-radius: 0;
        border-top-right-radius: 0;
    }

    .signup-image-link {
        font-size: 12px;
        color: #222;
        display: block;
        text-align: center;
    }

    .form-submit {
        display: inline-block;
        background: #6dabe4;
        color: #fff;
        border-bottom: none;
        width: auto;
        padding: 15px 39px;
        border-radius: 5px;
        -moz-border-radius: 5px;
        -webkit-border-radius: 5px;
        -o-border-radius: 5px;
        -ms-border-radius: 5px;
        margin-top: 25px;
        cursor: pointer;
    }

    .form-submit:hover {
        background: #4292dc;
    }

</style>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar navbar-expand-sm navbar-dark bg-dark">
    <div class="container-fluid">
        <div class="">
            <img src="${pageContext.request.contextPath}/images/hotel1.png"
                 alt="Logo image" class="col-md-2" style="height:56px;width: 90px ">
        </div>
        <a class="navbar-brand align-baseline" style="margin-left: 490px" href="controller?action=indexPage">Hotel</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#mynavbar">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="mynavbar">
            <ul class="navbar-nav me-auto">
                <li class="nav-item" style="margin-top: 7px">
                    <a class="nav-link" href="controller?action=orderRoomPage">Choose room</a>
                </li>
                <li class="nav-item" style="margin-top: 7px ">
                    <a class="nav-link" href="controller?action=aboutUsPage">About us</a>
                </li>
                <li class="btn btn-outline-secondary " style="margin-left: 400px">
                    <a class="nav-link" href="controller?action=loginpage">Login</a>
                </li>
                <li class="btn btn-outline-secondary " style="margin-right: 0px">
                    <a class="nav-link" href="controller?action=registerPage">Sign in</a>
                </li>
            </ul>
        </div>

    </div>
</nav>