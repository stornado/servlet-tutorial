<%--
  Created by IntelliJ IDEA.
  User: ryan
  Date: 2016/12/23
  Time: 21:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>文件上传</title>
</head>
<body>
<h1>文件上传示例</h1>
<form action="/HelloWorld/UploadServlet" method="post" enctype="multipart/form-data">
    选择一个文件：
    <input type="file" name="uploadFile"><br><br>
    <input type="submit" value="上传">
</form>
</body>
</html>
