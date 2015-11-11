<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<jsp:useBean id="productrecords" type="java.sql.ResultSet" scope="request"/>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>PRODUCTS</h1>
	<div style="height:200px;overflow:auto;">
	<table border="1" width="100%" cellpadding="3" cellspacing="3">
		<tr>
			<th align="center">product code</th>
			<th align="center">Item</th>
			<th align="center">qty</th>
			<th align="center">date sold</th>
			<th align="center">manufacturer</th>
			<th align="center">price per product</th>
			<th align="center">total price</th>
	
		</tr>
		
			<% 
				while(productrecords.next()) {	
			%>
			
				<tr>
					<td><%=productrecords.getInt("product_code")%></td>
					<td><%=productrecords.getString("item")%></td>
					<td><%=productrecords.getString("quantity")%></td>
					<td><%=productrecords.getString("date_sold")%></td>
					<td><%=productrecords.getString("manufacturer")%></td>
					<td><%=productrecords.getString("product_price")%></td>
					<td><%=productrecords.getString("total_price")%></td>
					
				</tr>		
	<% } %>
		
	</table></div>

	
	<a href="index.jsp">back to index</a>
</body>
</html>