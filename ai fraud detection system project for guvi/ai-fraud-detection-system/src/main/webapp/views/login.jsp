<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="../assets/css/style.css">
</head>

<body>

<div class="container">
    <h2>User Login</h2>

    <% if (request.getParameter("error") != null) { %>
    <p style="color:red;">Invalid Username or Password</p>
    <% } %>

    <form action="../login" method="post">
        <input type="text" name="username" placeholder="Username" required>

        <input type="password" name="password" placeholder="Password" required>

        <button type="submit">Login</button>
    </form>

</div>

</body>
</html>
