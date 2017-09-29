<%@page import="car.tp4.entity.Book"%>
<%@page import="java.util.Collection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html> 
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Books list</title>
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
        <h2>Books list</h2>
        
        <table class="table table-hover">
		    <thead>
		      <tr>
		        <th>Author</th>
		        <th>Title</th>
		        <th>Year</th>
		        <th>Shopping cart</th>
		        <th>Stock</th>
		      </tr>
		    </thead>
		    
		    <tbody>
		      
		       <%
	        	@SuppressWarnings("unchecked")
	            Collection<Book> books = (Collection<Book>) request.getAttribute("books");
	            for (Book book: books) {
	                out.print("<tr>"
	                		+ "<td>" + book.getAuthor() + "</td>"
	                		+ "<td>" + book.getTitle() + "</td>"
	                        + "<td>" + book.getYear() + "</td>"
	                        + "<td><a href='/add_cart?id=" + book.getId() + "'>Add</a></td>"
	                		+ "<td>Current: " + book.getStock()
	                		+ " - Add: <form style='display: inline;' method='post' action='/add_stock'>"
	                		+ "<input type='hidden' name='id' value='"+book.getId()+"'/>"
	                		+ "<input type='number' name='nb' min='0' value='0' style='width: 50px;'/><input type='submit' value='Add stock'/>"
	                		+ "</form></td>"
	                		+ "</tr>");
	            }
	        %>
		      
		    </tbody>
		  </table>
         
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











