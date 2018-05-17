<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<p><a href="http://localhost:8080/circulation-checking-rest/test/files/download">Download</a>
<h3>Upload a File</h3>
<form action="http://127.0.0.1:8080/yczj-app/service/material/upload" method="post" enctype="multipart/form-data">
<p>Select a file : <input type="file" name="file" /></p>
<input type="submit" value="Upload It" style="color: Fuchsia; "/>
</form>
</body>
</html>