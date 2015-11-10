<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<jsp:useBean id="productrecords" type="java.sql.ResultSet" scope="request"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>UST 3CSC Employee Listing</title>
</head>
<body>
	<h1>PRODUCTS</h1>
	
	<table border="1" width="100%" cellpadding="3" cellspacing="3">
		<tr>
			<th align="center">product code</th>
			<th align="center">Item</th>
			<th align="center">qty</th>
			<th align="center">date delivered</th>
			<th align="center">manufacturer</th>
			<th align="center">Action-Edit</th>
			<th align="center">Action-Delete</th>
		</tr>
		
			<% 
				while(productrecords.next()) {	
			%>
				<tr>
					<td><%=productrecords.getInt("product_code")%></td>
					<td><%=productrecords.getString("item")%></td>
					<td><%=productrecords.getString("quantity")%></td>
					<td><%=productrecords.getString("date_delivered")%></td>
					<td><%=productrecords.getString("manufacturer")%></td>
					<td align="center">
					  <a href="productmaintenance.html?product_code=<%=productrecords.getInt("product_code")%>&action=edit">
					  	edit
					  </a>
					</td>
					<td align="center">
					  <a href="productmaintenance.html?product_code=<%=productrecords.getInt("product_code")%>&action=delete">
					  	delete
					  </a>
					</td>
				</tr>			
	<% } %>
		
	</table>
</body>
</html>
</body>
</html>