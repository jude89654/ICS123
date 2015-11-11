<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update Employee Records</title>
</head>
<body>
<form action="itemupdate.html" method="post">
<p>productCode: ${param.product_code} </p>
<p>Item:  <input type="text" size="25" name="item" value="${productrecord.item}"/> </p>
<p>Price:  <input type="number" size="25" name="product_price" value="${productrecord.product_price}"/> </p>
<p>qty: <input type="number" size="25" name="quantity" value="${productrecord.quantity}"/></p>
<p>manufacturer: <input type="text" size="25" name="manufacturer" value="${productrecord.manufacturer}"/></p>
<p>date delivered: <input type="date" size="25" name="date_delivered" value="${today}"/></p>
<input type="hidden" name="product_code" value ="${param.product_code}"/>
<input type="submit" value="UpdateRecord"/>
</form>
</body>
</html>