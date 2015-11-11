<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="searchitem.html">
	<p>SEARCH<input type="text" name="query"></p>
	<p>Sort by: </p>
	product code<input type="checkbox" name="sortby" value="product_code">
	item<input type="checkbox" name="sortby" value="item">
	qty<input type="checkbox" name="sortby" value="quantity">
	manufacturers<input type="checkbox" name="sortby" value="manufacturer"><input type="submit" value="submit"/>
</form>
</body>
</html>