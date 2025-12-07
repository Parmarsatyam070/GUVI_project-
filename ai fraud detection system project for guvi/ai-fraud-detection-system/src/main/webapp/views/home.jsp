<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <link rel="stylesheet" href="../assets/css/style.css">
    <title>Home - Fraud Detection</title>
</head>

<body>

<div class="nav">
    <a href="../views/login.jsp">Login</a>
    <a href="../views/dashboard.jsp">Dashboard</a>
</div>

<div class="container">
    <h2>Fraud Detection System</h2>

    <form action="../transaction" method="post" onsubmit="return validateTransactionForm()">
        <input type="number" id="amount" name="amount" placeholder="Enter Transaction Amount" required>

        <input type="text" id="location" name="location" placeholder="Enter Transaction Location" required>

        <button type="submit">Submit Transaction</button>
    </form>

    <script src="../assets/js/form-validate.js"></script>

</div>
</body>
</html>
