<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <title>User Profile</title>
    <link rel="stylesheet" href="../assets/css/style.css">
</head>

<body>

<div class="container">
    <h2>Your Profile</h2>

    <p><strong>Username:</strong> ${user.username}</p>
    <p><strong>Email:</strong> ${user.email}</p>

    <button onclick="window.location.href='dashboard.jsp'">Back to Dashboard</button>
</div>

</body>
</html>
