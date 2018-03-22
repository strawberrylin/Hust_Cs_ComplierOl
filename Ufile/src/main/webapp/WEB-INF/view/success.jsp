<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: strawberrylin
  Date: 18-3-20
  Time: 下午12:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Successful</title>
    <link rel="stylesheet" href="< %=request.getContextPath() %>/statics/css/main.css" type="text/css" />
</head>
<body>
<div align="center">
    <h1>Successful!</h1>
    ${errorC}<br>
    ${inputC}<br>
    ${inputX}<br>
</div>
</body>
</html>
