<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@include file="header.jsp" %>
<%@include file="background.jsp" %>
<link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css"/>
<script type="text/javascript" src="https://cdn.jsdelivr.net/jquery/latest/jquery.min.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script>
<link href="${pageContext.request.contextPath}/css/bootstrapBetter.css" rel="stylesheet">
<form method="post" id="reserve" action="controller?action=makeRequest">
<div class="container mt-sm-auto bg-secondary bg-opacity-25">
    <div class="card bg-dark text-white">
        <div class="payment-details">
            <h3>Make your request</h3>

            <div class="billing">
                <span>Select Class</span>
                <select name="classOfRoom">
                    <option>Choose your class</option>
                    <c:forEach items="${listCategory}" var="type_of_room">
                        <option value="${type_of_room.id}">${type_of_room.type_of_room}</option>
                    </c:forEach>
                </select>
            </div>

        </div>

        <div class="billing" >
            <select name="numberOfPerson">
                <option>Guest</option>
                <option value="1">1</option>
                <option value="2">2</option>
                <option value="3">3</option>
                <option value="4">4</option>
            </select>
            <span>Number of guests</span>
        </div>

        <div class="input-text">
            <input type="text" name="datefilter" value="" placeholder="DD/MM/YY - DD/MM/YY">
            <span>Length of stay</span>
        </div>
            <button type="submit" name="room" style="margin-top: 40px" class="btn btn-warning">Claim</button>
    </div>
</div>
</form>
<script type="text/javascript">
    $(function () {

        $('input[name="datefilter"]').daterangepicker({
            minDate: new Date(),
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

