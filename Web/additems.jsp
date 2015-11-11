<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="addItems.html" method="post">

<p>item<input type="text" name="item" required="required"/></p>
<p>price<input type="number" name="product_price" required="required"/>
<p>quantity<input type="number" name="quantity" required="required"/></p>
<p>Product Code<input type="number" name="product_code" required="required"/></p>
<p>Supplier<input type="text" name="manufacturer" required="required"/></p>
<p>date Delivered<input type="date" name="date_delivered" required="required"/></p>
<p><input type="submit" value="add"/>
</form>
<a href="index.html">back to index</a>
</body>
</html>