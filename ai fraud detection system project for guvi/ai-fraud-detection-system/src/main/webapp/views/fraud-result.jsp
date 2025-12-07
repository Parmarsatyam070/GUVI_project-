<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head><title>Fraud Result</title></head>
<body>

<h2>Fraud Detection Result</h2>

<p><b>WEKA Fraud Probability:</b> ${wekaScore}</p>
<p><b>DL4J Fraud Probability:</b> ${dl4jScore}</p>

<c:choose>
    <c:when test="${wekaScore > 0.5 || dl4jScore > 0.5}">
        <h3 style="color:red;">⚠ HIGH RISK – Fraud Detected!</h3>
    </c:when>
    <c:otherwise>
        <h3 style="color:green;">✔ LOW RISK – Transaction Safe</h3>
    </c:otherwise>
</c:choose>

</body>
</html>
