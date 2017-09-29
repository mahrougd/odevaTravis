<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="car.tp4.entity.Book"%>
<%@page import="java.util.Collection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>    
     <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping cart validation</title>
           	  <meta charset="utf-8">
			  <meta name="viewport" content="width=device-width, initial-scale=1">
			  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
			  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
			  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
			  
	    <style type="text/css">
	        body {
	            margin-left: 5%;
	            margin-top: 5%;
	            background-image: linear-gradient(#e6e8ed, #ffffff);
	            background-size: cover;
	            margin-bottom: 55%;
	        }
	    </style>
    </head>
    <body>
    <div class="container">
        <h2>Shopping cart validation</h2>
        <p>The shopping cart #<%=request.getAttribute("cart_id") %> was validated successfully.</p>
        
        
        <br> <br>
        
           <h2>Other options</h2>
         <div class="list-group">
		    <a href="/" class="list-group-item">Index</a>
		    <a href="/list" class="list-group-item">Full list</a>
		    <a href="/search" class="list-group-item">Search</a>
		    <a href="/add" class="list-group-item">Add book</a>
		    <a href="/cart" class="list-group-item">Shopping cart</a>
		  </div>
		</div>
		
    </body>
</html>
