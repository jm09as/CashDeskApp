<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
		<form action="delete" method="get">
			Add meg a törölni kívánt pénztár nevét:<input type="text" name="name" placeholder="${cdesk.cashDeskName}" required> <br>
			<input type="submit" value="tovább">
		</form>
	</div>
	<div>
		<form action="setup.jsp">
			<input type="submit" value="vissza">
		</form>
	</div>
</body>
</html>