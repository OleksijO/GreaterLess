<%@ page import="training.game.greater.less.controller.config.Paths" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="training.game.greater.less.controller.config.Parameters" %>
<%@ page import="training.game.greater.less.controller.config.Attributes" %>
<html>
<head>
    <title>Greater-Less::Initialization</title>
</head>
<body>
<form method="post" action="${Paths.SETUP_PATH}">
Set up game range.
    <p style="color: red">${requestScope[Attributes.ERROR_MESSAGE]}</p>
<br/>
<br/>
<label for="min">Input a lower game range limit:</label><br>
<input id="min" type="number" name="${Parameters.SETUP_RANGE_LOWER_BOUND}"/>
<br/><br>
<label for="max">Input an upper game range limit:</label><br>
<input id="max" type="number" name="${Parameters.SETUP_RANGE_UPPER_BOUND}"/>
<br/>
<input type="submit">
</form>
</body>
</html>
