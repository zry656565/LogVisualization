<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>testForFileUpload</title>
	</head>
	<body>
		<form action="SourceUpload" enctype="multipart/form-data"  method="post">
			数据源名：<input type="text" name="sourcename" /><br/>
			添加文件：<input type="file" name="file1" /><br/>
			<input type="submit" value="submit"/><br/>
		</form>
	</body>
</html>