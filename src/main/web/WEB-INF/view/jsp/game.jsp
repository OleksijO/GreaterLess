<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="training.game.greater.less.controller.config.Parameters" %>
<%@ page import="training.game.greater.less.controller.config.Attributes" %>
<%@ page import="training.game.greater.less.controller.config.Paths" %>

<html>
<head>
    <title>Greater-Less::Game</title>
</head>
<body>

<br/>

<c:forEach var="record" items="${sessionScope[Attributes.HISTORY]}">
    ${record}<br/>
</c:forEach>
<br/>
<p style="color: red">${requestScope[Attributes.ERROR_MESSAGE]}</p>
<p style="color: green">${requestScope[Attributes.USUAL_MESSAGE]} </p>
<c:choose>
    <c:when test="${empty requestScope[Attributes.GAME_OVER]}">
        <form action="${Paths.GAME_PATH}" method="post">
            <label for="input_number">
                Enter your number in between ${requestScope[Attributes.LOWER_BOUND]}
                and ${requestScope[Attributes.UPPER_BOUND]}:</label><br>
            <input id="input_number" type="number" name="${Parameters.USER_INPUT_NUMBER}"> <input type="submit"
                                                                                                  value="Guess">
        </form>
    </c:when>
    <c:otherwise>
        <p style="color: green"> GAME OVER !</p><br>
        <a href="${Paths.SETUP_PATH}">Play again</a>
    </c:otherwise>
</c:choose>
</body>
</html>
