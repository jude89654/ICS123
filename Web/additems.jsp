<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="addItems.html">
<p>item<input type="text" name="itemName" required="required"/></p>

<p>quantity<input type="number" name="itemQuantity" required="required"/></p>

<p>Product Code<input type="text" name="productCode" required="required"/></p>

<p>Supplier<input type="text" name="supplier" required="required"/></p>

<p>date Delivered<input type="date" name="dateDelivered" required="required"/></p>
<p><input type="submit" value="add"/>
</form>
</body>
</html>