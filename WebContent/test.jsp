<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>testForConsole</title>
	</head>
	<body>
		<form action="ConsoleServlet" method="get">
			<input type="text" name="command" placeholder="Type Console Command." />
			<input type="submit" value="submit"/>
		</form>
		可以输入“search index=testindex”来测试效果。
	</body>
</html>