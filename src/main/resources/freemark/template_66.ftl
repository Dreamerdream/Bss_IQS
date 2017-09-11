<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>Getting Started: Serving Web Content</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
</head>
<body>
<h4 th:text="${msg}"></h4>
<form th:action="@{/user/login}" method ="post">
    <div><input name="username"  type="text"/></div>
    <div><input name="password"  type="password"/></div>
    <input type="submit" value="提交"/>
</form>

</body>
</html>