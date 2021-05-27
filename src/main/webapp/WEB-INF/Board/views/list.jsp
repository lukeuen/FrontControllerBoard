<%--
  Created by IntelliJ IDEA.
  User: lukeuen
  Date: 2021/05/28
  Time: 6:58 오전
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
    <table style="width:100%">
        <tr>
            <th>번호</th>
            <th>제목</th>
            <th>생성일</th>
        </tr>
        <c:forEach var="item" items="${listitem}">
            <tr>
                <td>${item.idx}</td>
                <td><a href="read.jsp?idx=${item.idx}">${item.title}</a></td>
                <td>${item.writeDate}</td>
            </tr>
        </c:forEach>

    </table>
</body>
</html>
