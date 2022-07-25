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
			<c:set var="num" scope="session" value="-1"></c:set>
			<c:forEach var="registryOption" items="${regmenu}">
				<button type="submit" name="registryid" value="${num = num + 1}">${registryOption.name}</button>
			</c:forEach>
		</form>
	</div>
	<div>
		<c:if test="${registryid == 0}">
		<div>
		<form action="newregistry" method="get">	
			Add meg a bejegyzés nevét:<input type="text" name="name" required> <br>	
			Add meg a bejegyzés összegét:<input type="number" name="sum" required> <br>
			<input type="submit" value="tovább">
		</form>
	</div>
		</c:if>
	</div>
	<div>
		<c:if test="${registryid == 1}">
		<div>
		<form action="newregistry" method="get">	
			Add meg a bejegyzés nevét:<input type="text" name="name" required> <br>	
			Add meg a bejegyzés összegét:<input type="number" name="sum" required> <br>
			<input type="submit" value="tovább">
		</form>
	</div>
		</c:if>
	</div>
</body>
</html>