<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="setup.css">
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>Menü</h2>
	<div>
		<form action="cashdesk" method="get">
			<c:forEach var="enums" items="${menuenum}">
				<button type="submit" name="moenum" value="${enums}">${enums.name}</button>
			</c:forEach>
		</form>
	</div>
	<div>
		<p>A betöltött pénztár neve: ${cdesk.cashDeskName}</p>
		<p>
			A betöltött pénztár pénzkorlátja:
			<fmt:formatNumber type="number" maxIntegerDigits="10" value="${cdesk.limit}" />
			Ft
		</p>
		<p>
			A betöltött pénztár utolsó módosítása:
			<fmt:formatDate type="both" dateStyle="medium" timeStyle="medium" value="${cdesk.entryDate}" />
		</p>
	</div>
	<c:set var="ordinal" scope="session" value="${ordinal}" />
	<c:if test="${ordinal == 0}">
		<div>
			<div>
		<form action="result" method="get">	
			Add meg a pénztár nevét:<input type="text" name="name" placeholder="pl: name" autocomplete="off" required> <br>	
			Add meg a pénztár méretét:<input type="number" name="limit" min="0" max="2147483647" placeholder="pl: 6.000.000" required> <br>
			Add meg a pénztár évét:<input type="text" name="year" placeholder="pl: 2022" required> <br>
			Add meg a pénztár hónapját:<input type="number" name="month" min="1" max="12" placeholder="pl: 3 = Március" required> <br> 
			Add meg a pénztár napját:<input type="text" name="day" placeholder="pl: 12" required> <br>
			<input type="submit" value="tovább">
		</form>
	</div>
		</div>
	</c:if>
	<c:if test="${ordinal == 1}">
		<div>
			<form action="load" method="get">
				<table style="width: 1000px">
					<tr>
						<th style="width: 40px">Azonosító</th>
						<th style="width: 80px">Pénztár név</th>
						<th style="width: 80px">Pénztár mérete</th>
						<th style="width: 100px">Pénztár bejegyzés ideje</th>
					</tr>
					<c:forEach var="cd" items="${allcashdesk}">
						<tr>
							<td style="width: 30px">${cd.idNumber}</td>
							<td style="width: 70px">${cd.cashDeskName}</td>
							<td style="width: 70px">${cd.limit}</td>
							<td style="width: 90px">${cd.entryDate}</td>
							<td style="width: 40px">
								<button type="submit" name="name" value="${cd.cashDeskName}">load</button>
							</td>
						</tr>
					</c:forEach>
				</table>
			</form>
		</div>
	</c:if>
	<c:if test="${ordinal == 2}">
		<div>
			<form action="delete" method="get">
				<table style="width: 1000px">
					<tr>
						<th style="width: 40px">Azonosító</th>
						<th style="width: 80px">Pénztár név</th>
						<th style="width: 80px">Pénztár mérete</th>
						<th style="width: 100px">Pénztár bejegyzés ideje</th>
					</tr>
					<c:forEach var="cd" items="${allcashdesk}">
						<tr>
							<td style="width: 30px">${cd.idNumber}</td>
							<td style="width: 70px">${cd.cashDeskName}</td>
							<td style="width: 70px">${cd.limit}</td>
							<td style="width: 90px">${cd.entryDate}</td>
							<td style="width: 40px">
								<button type="submit" name="name" value="${cd.cashDeskName}">delete</button>
							</td>
						</tr>
					</c:forEach>
				</table>
			</form>
		</div>
	</c:if>
</body>
</html>