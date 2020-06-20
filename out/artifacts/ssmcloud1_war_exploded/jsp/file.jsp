<%--
  Created by IntelliJ IDEA.
  User: HelloZeiKan
  Date: 2020/6/18
  Time: 23:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form action="${pageContext.request.contextPath}/file/up1" method="post"
      enctype="multipart/form-data">
    <input type="file" name="file" /><br />
    <input type="submit" value="上 传" />
</form>
</form>
</body>
</html>
