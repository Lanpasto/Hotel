<%@include file="header.jsp" %>
<%@include file="background.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="${pageContext.request.contextPath}/css/payform.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://use.fontawesome.com/releases/v5.7.2/css/all.css"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css"></script>
<body class="text-center">
<div class="container-fluid ">
    <div class="row">
        <div class="col-sm-6 text-black " style="margin-left: 34%">
            <div class=" h-custom-2 px-5 ms-xl-4 mt-5 pt-5 pt-xl-0 mt-xl-n5"
                 style="width: 450px; height: 460px;border-radius: 15px;">
                <form method="post" action="controller?action=payment" class="register-form">
                        <div class="card">
                            <div class="top">
                                <h3>Payment</h3>
                                <hr>
                            </div>
                            <div class="card-details"> <input type="text" placeholder="0000 0000 0000 0000" data-slots="0" data-accept="\d" size="19"> <span>Card Number</span> <i class="fa fa-credit-card"></i> </div>
                            <div class="exp-cvv">
                                <div class="card-details"> <input type="text" placeholder="mm/yyyy" data-slots="my"> <span>Expiry date</span> <i class="fa fa-calendar"></i> </div>
                                <div class="card-details"> <input type="text" placeholder="000" data-slots="0" data-accept="\d" size="3"> <span>CVV</span> <i class="fa fa-info-circle"></i> </div>
                            </div>
                            <div style="margin-bottom: 20px" class="card-details mt-25"> <input type="text" placeholder="Enter cardholder's full name"> <span>Card Holder Name</span> </div>

                            <div class="button"> <button>Pay</button> </div>
                        </div>
                </form></div>
        </div>
    </div>
</div>
</body>


<script>var tick=document.querySelector(".tick span");
var tick_mark=document.querySelector(".tick span i");
tick.addEventListener('click',function(){

    tick.classList.toggle('p-blue');
    tick_mark.classList.toggle('d-none');
});


document.addEventListener('DOMContentLoaded', () => {
    for (const el of document.querySelectorAll("[placeholder][data-slots]")) {
        const pattern = el.getAttribute("placeholder"),
            slots = new Set(el.dataset.slots || "_"),
            prev = (j => Array.from(pattern, (c,i) => slots.has(c)? j=i+1: j))(0),
            first = [...pattern].findIndex(c => slots.has(c)),
            accept = new RegExp(el.dataset.accept || "\\d", "g"),
            clean = input => {
                input = input.match(accept) || [];
                return Array.from(pattern, c =>
                    input[0] === c || slots.has(c) ? input.shift() || c : c
                );
            },
            format = () => {
                const [i, j] = [el.selectionStart, el.selectionEnd].map(i => {
                    i = clean(el.value.slice(0, i)).findIndex(c => slots.has(c));
                    return i<0? prev[prev.length-1]: back? prev[i-1] || first: i; }); el.value=clean(el.value).join``; el.setSelectionRange(i, j); back=false; }; let back=false; el.addEventListener("keydown", (e)=> back = e.key === "Backspace");
        el.addEventListener("input", format);
        el.addEventListener("focus", format);
        el.addEventListener("blur", () => el.value === pattern && (el.value=""));
    }
});</script>