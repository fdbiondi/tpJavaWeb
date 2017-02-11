<%@page import="java.util.ArrayList"%>
<%@page import="entidades.Personaje"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
	    <meta charset="utf-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1">
	    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
	    <meta name="description" content="">
	    <meta name="author" content="">
	    <link rel="icon" href="http://getbootstrap.com/favicon.ico">
	
	    <title>Elegir personajes</title>
	
	    <!-- Bootstrap core CSS -->
	    <link href="style/bootstrap.css" rel="stylesheet">
	
	    <!-- Custom styles for this template -->
	    <link href="style/start.css" rel="stylesheet">

</head>
<body>
  <div class="container" style="padding-top:50px">
  <% ArrayList<Personaje> pjs = (ArrayList<Personaje>)(request.getAttribute("pjs")); 
  	 boolean error = (Boolean)request.getAttribute("error");
	 boolean finish = (Boolean)request.getAttribute("finish");
  	 String message ="";
  	 
  	 if(error) { message = (String)request.getAttribute("message"); }
  	 if(finish) { message = (String)request.getAttribute("message"); }
 	%>
  	   
  	<div class="alert alert-danger" role="alert" style="<% if(!error) { %> display:none <% } %>">
  		<%= message %>
  	</div>
  
  <div class="alert alert-success" role="alert" style="<% if(!finish) { %> display:none <% } %>">
  		<%= message %>
  	</div>
  
    <form name="jugar" action="start" method="post">
	    <div class="row">
	      <div class="col-sm-4 col-sm-offset-1">
	        <div class="form-group">
			  <label for="player1">Jugador 1:</label>
			  <select class="form-control" name="player1" id="player1">
			  <% 
				  	for(Personaje p : pjs) { 
				  	%>
				      <option value="<%=p.getId()%>">
				      	<%=p.getNombre() %>
			      	  </option>
				  <% } %>
			  </select>
			</div>
	      </div>
	
	      <div class="col-sm-4 col-sm-offset-2">
	        <div class="form-group">
			  <label for="player2">Jugador 2:</label>
			  <select class="form-control" name="player2" id="player2">
			     <% 
				  	for(Personaje p : pjs) { 
				  	%>
				      <option value="<%=p.getId()%>">
				      	<%=p.getNombre() %>
			      	  </option>
				  <% } %>
			  </select>
			</div>
	      </div>
	    </div>
	    <br/>	
	    <div class="row">
	      <div class="col-md-4 col-md-offset-4">
		      <div class="form-group">
	          	<button class="btn btn-lg btn-primary btn-block" name="action" value="start" type="submit">Jugar!</button>
		      </div>
    	  </div>
	    </div>
    </form>
  </div> 

</body>
</html>