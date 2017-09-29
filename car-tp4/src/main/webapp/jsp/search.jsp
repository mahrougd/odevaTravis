<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	 <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search books</title>
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
        <h2>Search books</h2>
        <br>
        <form action="/search" method="post">
        	<input name="search_request" placeholder="Search request"> <br/> <br>
        	Filter by : <br>
        	<input type="radio" name="search_mode" value="title" checked="checked"> Title
        	 <br>
        	<input type="radio" name="search_mode" value="author"> Author<br/>
        	 <br>Sort by:<br> <INPUT type="checkbox" name="sort" checked="checked"> Date
        	<br><br>
        	<input type="submit" value="Search">
		</form>
		<%	
			if (request.getAttribute("error") != null) {
				out.print("<p style='color:red;'>");
				out.print((String)request.getAttribute("error"));
				out.print("</p>");
			}
		%>  
  
        <br><br>
        
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
