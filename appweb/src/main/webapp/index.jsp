<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
   pageEncoding="ISO-8859-1"%>
<html>
<head>
<title>Hello World</title>
</head>
<body>
   <h1>Ajouter un utilisateur</h1>
   <form action="user">
      <label for="login">Login  :  &nbsp;&nbsp;</label> <input type="text" name="login"/><br>
      <label for="email">e-mail : &nbsp;&nbsp;</label> <input type="text" name="email"/><br>
      <label for="passwd">passwd : </label> <input type="password" name="passwd"/><br>
      <p>
      <input type="submit" value="ajouter"/>
      <input type="reset" value="annuler"/>
   </form>
</body>
</html>