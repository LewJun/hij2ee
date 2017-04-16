<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>greet</title>
</head>
<body>
hello world
<p>${username }</p>
<p><c:forEach items="${username }" var="u" varStatus="status">${u}</c:forEach></p>
<script type="text/javascript" src="../assets/hello/greet.js"></script>
</body>
</html>