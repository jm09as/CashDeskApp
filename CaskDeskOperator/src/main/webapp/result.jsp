<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h4>Kérdőív</h4>
	<div>
		<c:forEach var="result" items="${sessionScope.result}">
			<li>${result}</li>
		</c:forEach>
	</div>
	<div>
		<form action="setup">
			<input type="submit" value="vissza">
		</form>
	</div>
</body>
</html>