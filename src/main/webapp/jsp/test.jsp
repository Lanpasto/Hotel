<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width,
                   initial-scale=1.0">
    <link href=
                  "https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css"
          rel="stylesheet" type="text/css" />
    <link rel="stylesheet" href=
            "https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/css/bootstrap-datepicker.min.css">
    <title>Bootstrap Datepicker</title>
    <style>
        label{margin-left: 20px;}
        #datepicker{width:180px;}
        #datepicker > span:hover{cursor: pointer;}
    </style>
</head>

<body>
<div class="container">
    <h1 class="text-success font-weight-bold">
        GeeksforGeeks
    </h1>
    <h3>
        setting up bootstrap datepicker
    </h3>
    <label>Select Date: </label>
    <div id="datepicker"
         class="input-group date"
         data-date-format="mm-dd-yyyy">
        <input class="form-control"
               type="text" readonly />
        <span class="input-group-addon">
                <i class="glyphicon glyphicon-calendar"></i>
            </span>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.6.1.min.js"
        integrity=
                "sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ="
        crossorigin="anonymous">
</script>
<script>
    $(function () {
        $("#datepicker").datepicker({
            autoclose: true,
            todayHighlight: true,
        }).datepicker('update', new Date());
    });
</script>
<script src=
                "https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"
        integrity=
                "sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous">
</script>
<script src=
                "https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"
        integrity=
                "sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous">
</script>
<script src=
                "https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js">
</script>
</body>

</html>

</body>
</html>
