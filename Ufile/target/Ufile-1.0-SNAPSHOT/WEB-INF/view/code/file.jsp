<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: strawberrylin
  Date: 18-3-20
  Time: 下午12:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Upload</title>
    <link rel="stylesheet" href="< %=request.getContextPath()% >/statics/css/main.css" type="text/css"/>
</head>
<body>
<div align="center">
    <h1>Upload</h1>
    <form method="post" action="doUpload" enctype="multipart/form-data">
        <input type="file" name="file" />
        <input type="submit" />
    </form>
</div>
</body>
</html>
