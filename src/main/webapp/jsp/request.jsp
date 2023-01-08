<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@include file="header.jsp" %>
<%@include file="background.jsp" %>
<link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css"/>
<script type="text/javascript" src="https://cdn.jsdelivr.net/jquery/latest/jquery.min.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script>
<link href="${pageContext.request.contextPath}/css/bootstrapBetter.css" rel="stylesheet">
<div class="container bg-secondary bg-opacity-25">
    <div class="card bg-dark text-white">
        <div class="payment-details">
            <h3>Make your request</h3>
            <div class="billing">
                <span>Select Class</span>
                <select>
                    <option>Class</option>
                    <option>Luxury</option>
                    <option>Basics</option>
                    <option>Economy</option>
                </select>
            </div>
        </div>
        <div class="input-text">
            <input type="text" placeholder="Guests">
            <span>Number of guests</span>
        </div>
        <div class="input-text">
            <input type="text" name="datefilter" value="" placeholder="DD/MM/YY - DD/MM/YY">
            <span>Length of stay</span>
        </div>
        <div class="summary">
            <div class="text-data">
                <h5>Total</h5>
            </div>
            <div class="numerical-data">
                <h5>$16.80</h5>
            </div>
        </div>
        <div class="pay">
            <button>Enter</button>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(function () {

        $('input[name="datefilter"]').daterangepicker({
            autoUpdateInput: false,
            locale: {
                cancelLabel: 'Clear'
            }
        });

        $('input[name="datefilter"]').on('apply.daterangepicker', function (ev, picker) {
            $(this).val(picker.startDate.format('MM/DD/YYYY') + ' - ' + picker.endDate.format('MM/DD/YYYY'));
        });

        $('input[name="datefilter"]').on('cancel.daterangepicker', function (ev, picker) {
            $(this).val('');
        });

    });
</script>

