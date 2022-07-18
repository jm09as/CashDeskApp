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
		<form action="load" method="get">
			<label>Add meg a pénztár nevét:</label> 
			<select name="name">
				<c:forEach var="cd" items="${cashDesks}">
					<option value="${cd.cashDeskName}">${cd.cashDeskName}
				</c:forEach>
			</select>
			<p>
				<input type="submit" value="tovább">
			</p>
		</form>
	</div>
</body>
</html>
<!-- <form action="/action_page.php" method="get"> -->
<!--   <label for="browser">Choose your browser from the list:</label> -->
<!--   <input list="browsers" name="browser" id="browser"> -->
<!--   <datalist id="browsers"> -->
<!--     <option value="Edge"> -->
<!--     <option value="Firefox"> -->
<!--     <option value="Chrome"> -->
<!--     <option value="Opera"> -->
<!--     <option value="Safari"> -->
<!--   </datalist> -->
<!--   <input type="submit"> -->
<!-- </form> -->