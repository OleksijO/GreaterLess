<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<p style="color: red">${requestScope[Attributes.ERROR_MESSAGE]}
<p style="color: green">${requestScope[Attributes.USUAL_MESSAGE]}
    <br/>
Input history:<br>
<c:forEach var="record" items="${sessionScope[Attributes.HISTORY]}">
    ${record}<br/>
</c:forEach>
<br/>
<label for="input_number">Enter your number:</label><br>
<input  id="input_number" type="number" name="${Parameters.USER_INPUT_NUMBER}"> <input type="submit">
</body>
</html>
