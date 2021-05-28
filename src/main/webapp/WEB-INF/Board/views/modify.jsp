<%--
  Created by IntelliJ IDEA.
  User: lukeuen
  Date: 2021/05/28
  Time: 9:04 오전
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<p>글쓴이 : ${listinfo.writer}</p>
<p>작성 날짜 : ${listinfo.writeDate}</p>
<form method="post">
    <input type="hidden" name="idx" value="${listinfo.idx}">
    <input type="text" name="title" placeholder="제목" value="${listinfo.title}">
    <input type="text" name="content" placeholder="내용" value="${listinfo.content}">
    <input type="submit" value="수정">
</form>
</body>
</html>
