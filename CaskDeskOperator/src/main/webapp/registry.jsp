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
					Add meg a bejegyzés nevét:<input type="text" name="name" required> <br> Add meg a
					bejegyzés összegét:<input type="number" name="sum" required> <br> <input
						type="submit" value="tovább">
				</form>
			</div>
		</c:if>
	</div>
	<div>
		<c:if test="${registryid == 1}">
			<div>
				<form action="delregistry" method="get">
					<table style="width: 1000px">
						<tr>
							<th style="width: 100px">Pénztár ID</th>
							<th style="width: 80px">Bejegyzés név</th>
							<th style="width: 80px">Bejegyzés összege</th>
							<th style="width: 100px">Bejegyzés ideje</th>
							<th style="width: 40px">Bejegyzés ID</th>
						</tr>
						<c:forEach var="el" items="${entrylist}">
							<tr>
								<td style="width: 90px">${el.cashDeskId}</td>
								<td style="width: 70px">${el.nameEntry}</td>
								<td style="width: 70px">${el.sum}</td>
								<td style="width: 90px">${el.timeEntry}</td>
								<td style="width: 30px">${el.id}</td>
								<td>
									<button type="submit" name="delid" value="${el.id}">delete</button>
								</td>

							</tr>
						</c:forEach>
					</table>
				</form>
			</div>
		</c:if>
	</div>
	<div>
		<c:if test="${registryid == 2}">
			<div>
				<table style="width: 1000px">
					<tr>
						<th style="width: 100px">Pénztár ID</th>
						<th style="width: 80px">Bejegyzés név</th>
						<th style="width: 80px">Bejegyzés összege</th>
						<th style="width: 100px">Bejegyzés ideje</th>
						<th style="width: 40px">Bejegyzés ID</th>
					</tr>
					<c:forEach var="el" items="${entrylist}">
						<tr>
							<td style="width: 90px">${el.cashDeskId}</td>
							<td style="width: 70px">${el.nameEntry}</td>
							<td style="width: 70px">${el.sum}</td>
							<td style="width: 90px">${el.timeEntry}</td>
							<td style="width: 30px">${el.id}</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</c:if>
	</div>
</body>
</html>