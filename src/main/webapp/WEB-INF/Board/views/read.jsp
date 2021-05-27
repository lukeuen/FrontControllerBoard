<%--
  Created by IntelliJ IDEA.
  User: lukeuen
  Date: 2021/05/28
  Time: 6:58 오전
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<h2>내용</h2>
<c:if test="${listinfo != null}">
    ${listinfo.idx} ,
    ${listinfo.content},
    ${listinfo.writeDate},
    ${listinfo.writer},
</c:if>
</body>
</html>
