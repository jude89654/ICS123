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
<h1>SUCCESSFUL UPDATE</h1>
<p>Product Code:${productrecord.product_code}</p>
<p>item:${productrecord.item}</p>
<p>manufacturer:${productrecord.manufacturer}</p>
<p>quantity:${productrecord.quantity}</p>
<p>date delivered:${productrecord.date_delivered }</p>


<%}else{%>
<h1>add item FAILED</h1>
<%}%>
<a href="index.jsp">go back to index</a>
</body>
</html>