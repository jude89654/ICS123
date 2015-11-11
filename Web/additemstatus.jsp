<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%if(request.getParameter("status").equals("true")){ %>
<h1>SUCCESSFUL INSERT</h1>
<p>Product Code: ${productbean.product_code}</p>
<p>item:${productbean.item}</p>
<p>Price:${productbean.product_price }</p>
<p>quantity:${productbean.quantity}</p>
<p>total price:${productbean.total_price }</p>
<p>manufacturer:${productbean.manufacturer}</p>
<p>date delivered:${productbean.date_delivered }</p>


<%}else{%>
<h1>add item FAILED</h1>
<%}%>
<a href="index.jsp">go back to index</a>
</body>
</html>