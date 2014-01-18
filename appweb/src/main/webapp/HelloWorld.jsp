<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<title>Hello World</title>
</head>
<body>
   L'utilisateur "<s:property value="login"/>" est créé avec succés!
   <p>
   <a href="${pageContext.request.contextPath}/index.jsp"><-- retour</a>
</body>
</html>