<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h4>A pénztár adatai</h4>
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
</body>
</html>