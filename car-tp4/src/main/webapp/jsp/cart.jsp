<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="car.tp4.entity.Book"%>
<%@page import="java.util.Collection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Books in shopping cart</title>
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
        <h2>Books in shopping cart</h2>
        <table class="table table-hover">
         <thead>
        	<tr>
        		<th>Author</th>
        		<th>Title</th>
        		<th>Year</th>
        		<th>Quantity</th>
        	</tr>
         </thead>
	        <%
		        @SuppressWarnings("unchecked")
		        List<Book> books = (List<Book>) request.getAttribute("books");
		        @SuppressWarnings("unchecked")
		        Map<Long, Integer> cartQuantities = (Map<Long, Integer>) request.getAttribute("cart_quantities");
	
				if (books.isEmpty()) {
					out.print("<tr><td colspan='4'>Shopping cart empty</td></tr>");
				}
	            for (Book book: books) {
	                out.print("<tr>"
	                		+ "<td>" + book.getAuthor() + "</td>"
	                		+ "<td>" + book.getTitle() + "</td>"
	                        + "<td>" + book.getYear() + "</td>"
	                		+ "<td>" + cartQuantities.get(book.getId()) + "</td>"
	                		+ "</tr>");
	            }
	        %>
        </table>
        
        <p><a href="/validate_cart">Validate shopping cart</a></p>
        
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
