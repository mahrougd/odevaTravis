<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Formulaire</title>
    </head>
    <body>

        <br>
        <h2>New entered book :</h2>
        
        <%
		String titre = request.getParameter("titre");
        String nom = request.getParameter("nom");
        String annee = request.getParameter("annee");
        %>
        
        Titre : <%= titre %>  <br>
        Auteur: <%= nom %>  <br>
        Anneé: <%= annee %>  <br>
        
        
       <h2>  Formulaire pré-rempli avec les informations saisies</h2> 
       
       <form action="formulaire.jsp">
	   <input type="text"  value=<%= titre %> name="titre"> <br>
	   <input type="text"  value=<%= nom %> name="nom"> <br>
	   <input type="text"  value=<%= annee %> name="annee"> <br>
	   <input type="submit" value="Envoyer">     
  </form>   
        
            
    </body>
</html>