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
    <!-- Editor's Dependecy Style -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/codemirror/5.48.4/codemirror.min.css" />
    <!-- Viewer css 불러오기 -->
    <link rel="stylesheet" href="https://uicdn.toast.com/editor/latest/toastui-editor-viewer.min.css" />
</head>
<body>

<h2>읽기</h2>
<button><a href="${pageContext.request.contextPath}/modify?idx=${listinfo.idx}">수정</a></button>
<button><a href="${pageContext.request.contextPath}/list">목록</a></button>
<button><a href="${pageContext.request.contextPath}/delete?idx=${listinfo.idx}">삭제</a></button>
<c:if test="${listinfo != null}">
    <p>글번호 : ${listinfo.idx} </p>
    <p>글쓴이 : ${listinfo.writer}</p>
    <p>제목 : ${listinfo.title}</p>
    <p>날짜 : ${listinfo.writeDate}</p>
    <hr>
    <!-- jQuery 불러오기 -->
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <!-- viewer js 불러오기 -->
    <script src="https://uicdn.toast.com/editor/latest/toastui-jquery-editor-viewer.min.js"></script>
    <div id="viewer">
            ${listinfo.content}
    </div>
    <!-- viewer의 속성 설정 -->
    <script>
        $('#viewer').toastuiEditor({
            height: '500px',
        });
    </script>
</c:if>


</body>
</html>
