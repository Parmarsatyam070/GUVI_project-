<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Fraud Check Transaction</title>
</head>
<body>

<h2>Enter Transaction Details</h2>

<form action="${pageContext.request.contextPath}/fraud/check" method="post">

    <label>Amount:</label>
    <input type="number" name="amount" required><br><br>

    <label>Age:</label>
    <input type="number" name="age" required><br><br>

    <label>Payment Type:</label>
    <select name="paymentType" required>
        <option value="credit">Credit Card</option>
        <option value="debit">Debit Card</option>
        <option value="upi">UPI</option>
        <option value="netbanking">Net Banking</option>
    </select><br><br>

    <button type="submit">Check Fraud</button>

</form>

</body>
</html>
