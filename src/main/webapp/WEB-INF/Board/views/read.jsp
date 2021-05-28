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

<h2>읽기</h2>
<button><a href="${pageContext.request.contextPath}/modify?idx=${listinfo.idx}">수정</a></button>
<button><a href="${pageContext.request.contextPath}/list">목록</a></button>
<c:if test="${listinfo != null}">
    <p>글번호 : ${listinfo.idx} </p>
    <p>글쓴이 : ${listinfo.writer}</p>
    <p>제목 : ${listinfo.title}</p>
    <p>날짜 : ${listinfo.writeDate}</p>
    <p>내용 : ${listinfo.content}</p>
</c:if>


</body>
</html>
