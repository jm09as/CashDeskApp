<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
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
			<!-- 			<select name="nameid"> -->
			<c:forEach var="MenuOption" items="${menu}">
				<button type="submit" name="nameid" value="${MenuOption}">${MenuOption.name}</button>
			</c:forEach>
			<!-- 			</select> -->
			<p>
				<!-- 				<input type="submit" value="tovább"> -->
			</p>
		</form>
	</div>
	<div>
		<p>A betöltött pénztár neve: ${cdesk.cashDeskName}</p>
		<p>A betöltött pénztár pénzkorlátja: <fmt:formatNumber type="number" 
         maxIntegerDigits = "10" value = "${cdesk.limit}" /> Ft</p>
		<p>A betöltött pénztár utolsó módosítása: <fmt:formatDate type = "both" 
         dateStyle = "medium" timeStyle = "medium" value = "${cdesk.entryDate}" /></p>
	</div>
</body>
</html>