<%@page import="entidades.Personaje"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8">
	    <meta charset="utf-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1">
	    
		<meta name="description" content="">
	    <meta name="author" content="">
	    <link rel="icon" href="http://getbootstrap.com/favicon.ico">
	
	    <title>A Luchar!</title>
	
	    <!-- Bootstrap core CSS -->
	    <link href="style/bootstrap.css" rel="stylesheet">
	
	    <!-- Custom styles for this template -->
	    <link href="style/start.css" rel="stylesheet">

	</head>
	<body>
		
		<% 
			Personaje p1= ((Personaje)session.getAttribute("P1"));
			Personaje p2= ((Personaje)session.getAttribute("P2"));
		%>
		<div class="container">
		<div class="row">
			<div class="col-xs-6">
				<h1>Guerra!!</h1>
			</div>
		</div>
        	<div class="row">
	          <form name="partida" action="round" method="post">
	            <div class="col-xs-6">
	              <div class="form-group">
	                <label for="jugador-1">Jugador 1:</label>
	                <input type="text" class="form-control" name="jugador-1" value="<%= p1.getNombre() %>" readonly="readonly" disabled>
	              </div>
	              <div class="form-group">
	                <label for="vida-1">Vida</label>
	                <input type="text" class="form-control"  name="vida-1" value="<%= p1.getVida() %>" readonly="readonly" disabled>
	              </div>
	              <div class="form-group">
	                <label for="energia-1">Energia</label>
	                <input type="text" class="form-control" name="energia-1" value="<%= p1.getEnergia() %>" readonly="readonly" disabled>
	              </div>
	              <div class="form-group">
	                <label for="defensa-1">Defensa</label>
	                <input type="text" class="form-control" name="defensa-1" value="<%= p1.getDefensa() %>" readonly="readonly" disabled>
	              </div>
	              <div class="form-group">
	                <label for="evasion-1">Evasion</label>
	                <input type="text" class="form-control" name="evasion-1" value="<%= p1.getEvasion() %>" readonly="readonly" disabled>
	              </div>
	              <div class="form-group">
	                <div class="row">
	                  <div class="col-xs-6">
	                    <button class="btn btn-default" name="atacar-1">Atacar</button>
	                  </div>
	                  <div class="col-xs-6">
	                    <input type="text" class="form-control" name="accion-1-value" value="">
	                  </div>
	                </div>
	              </div>
	              <button class="btn btn-default" name="defender-1">Defender</button>
	            </div>
	            <div class="col-xs-6">
	              <div class="form-group">
	                <label for="jugador-2">Jugador 2</label>
	                <input type="text" class="form-control" name="jugador-2" value="<%= p2.getNombre() %>" readonly="readonly" disabled>
	              </div>
	              <div class="form-group">
	                <label for="vida-2">Vida</label>
	                <input type="text" class="form-control"  name="vida-2" value="<%= p2.getVida() %>" readonly="readonly" disabled>
	              </div>
	              <div class="form-group">
	                <label for="energia-2">Energia</label>
	                <input type="text" class="form-control" name="energia-2" value="<%= p2.getEnergia() %>" readonly="readonly" disabled>
	              </div>
	              <div class="form-group">
	                <label for="defensa-2">Defensa</label>
	                <input type="text" class="form-control" name="defensa-2" value="<%= p2.getDefensa() %>" readonly="readonly" disabled>
	              </div>
	              <div class="form-group">
	                <label for="evasion-2">Evasion</label>
	                <input type="text" class="form-control" name="evasion-2" value="<%= p2.getEvasion() %>" readonly="readonly" disabled>
	              </div>
	              <div class="form-group">
	                <div class="row">
	                  <div class="col-xs-6">
	                    <button class="btn btn-default" name="atacar-2">Atacar</button>
	                  </div>
	                  <div class="col-xs-6">
	                    <input type="text" class="form-control" name="accion-2-value" value="">
	                  </div>
	                </div>
	              </div>
	              <button class="btn btn-default" name="defender-2">Defender</button>
	            </div>
	          </form>
        	</div>
      	</div>
    	<script src="style/ie10-viewport-bug-workaround.js"></script>	
	</body>
</html>
