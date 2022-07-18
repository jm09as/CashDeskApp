<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>Menü</h2>
	<div>
		<form action="cashdesk" method="get">
			<select name="nameid">
				<c:forEach var="MenuOption" items="${menu}">
					<option value="${MenuOption}">${MenuOption.name}</option>
				</c:forEach>
			</select>
			<p>
				<input type="submit" value="tovább">
			</p>
		</form>
	</div>
	<label>A betöltött pénztár neve:</label>
	<h4>${cdesk.cashDeskName}</h4>
</body>
</html>