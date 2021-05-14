<%@page import="com.Fund"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Fund Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/funds.js"></script>
</head>
<body> 
<div class="container"><div class="row"><div class="col-6"> 
<h1>Fund Management</h1>
<form id="formFund" name="formFund" method="post" action="funds.jsp">

 Name: 
 <input id="name" name="name" type="text" 
 class="form-control form-control-sm">
 <br> Address: 
 <input id="address" name="address" type="text" 
 class="form-control form-control-sm">
 <br> Amount: 
 <input id="amount" name="amount" type="text" 
 class="form-control form-control-sm">
 <br> Email: 
 <input id="email" name="email" type="text" 
 class="form-control form-control-sm">
 <br> Phone: 
 <input id="phone" name="phone" type="text" 
 class="form-control form-control-sm">
 <br> NIC: 
 <input id="nic" name="nic" type="text" 
 class="form-control form-control-sm">
 <br>
 <input id="btnSave" name="btnSave" type="button" value="Save" 
 class="btn btn-primary">
 <input type="hidden" id="hidFundIDSave" 
 name="hidFundIDSave" value="">
</form>
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
<br>
<div id="divFundGrid">
	<%
				Fund FundObj = new Fund();
		 		out.print(FundObj.readFunds());
	%>
</div>
</div> </div> </div> 
</body>
</html>