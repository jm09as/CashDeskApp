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
		<form action="newregistry" method="get">	
			Add meg a bejegyzés nevét:<input type="text" name="name" required> <br>	
			Add meg a bejegyzés összegét:<input type="number" name="sum" required> <br>
			<input type="submit" value="tovább">
		</form>
	</div>
</body>
</html>