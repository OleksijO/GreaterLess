<%@ page import="training.game.greater.less.controller.config.Paths" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="post" action="${Paths.SETUP_PATH}">
Set up game range.
<br/>
<br/>
<label for="min">Input a lower game range limit:</label>
<input id="min" type="number" name="${Parameters.RANGE_LOWER_BOUND}"/>
<br/>
<label for="max">Input an upper game range limit:</label>
<input id="max" type="number" name="${Parameters.RANGE_UPPER_BOUND}"/>
<br/>
<input type="submit">
</form>>
</body>
</html>
