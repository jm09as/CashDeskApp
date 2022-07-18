<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>${cdesk.cashDeskName} pénztár Menü</h2>
	<div>
		<form action="selectoption" method="get">
			<select name="nameid">
				<c:forEach var="registryOption" items="${regmenu}">
					<option value="${registryOption.id}">${registryOption.name}</option>
				</c:forEach>
			</select>
			<p>
				<input type="submit" value="tovább">
			</p>
		</form>
	</div>
	<div>
		<form action="setup">
			<input type="submit" value="vissza">
		</form>
	</div>
</body>
</html>