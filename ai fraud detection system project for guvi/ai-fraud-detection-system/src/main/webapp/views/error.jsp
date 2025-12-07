<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Error</title>
    <link rel="stylesheet" href="../assets/css/style.css">
</head>

<body>
<div class="container">
    <h2>Error Occurred</h2>

    <p style="color:red;">
        <%= request.getAttribute("errorMessage") != null ? request.getAttribute("errorMessage") : "Unexpected error occurred." %>
    </p>

    <a href="home.jsp"><button>Go Back</button></a>
</div>
</body>
</html>
