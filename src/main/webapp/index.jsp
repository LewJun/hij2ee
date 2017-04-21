<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
	<h2>Hello World!</h2>
	<a href="http://127.0.0.1:8080/hij2ee">http://127.0.0.1:8080/hij2ee/index.jsp</a>
	
	
<form name="serForm" action="http://127.0.0.1:8080/hij2ee/media/upload2" method="post"  enctype="multipart/form-data">
<h1>采用流的方式上传文件</h1>
<input type="file" name="file">
<input type="submit" value="upload"/>
</form>
 
<form name="Form2" action="http://127.0.0.1:8080/hij2ee/media/upload" method="post"  enctype="multipart/form-data">
<h1>使用spring mvc提供的类的方法上传文件</h1>
<input type="file" name="file">
<input type="submit" value="upload"/>
</form>
</body>
</html>
