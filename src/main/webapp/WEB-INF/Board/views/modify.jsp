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
    <!-- Editor's Dependecy Style -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/codemirror/5.48.4/codemirror.min.css" />
    <!-- Editor's Style -->
    <link rel="stylesheet" href="https://uicdn.toast.com/editor/latest/toastui-editor.min.css" />
    <style>
        #title{
            width: 100%;
        }
    </style>
</head>
<body>
<p>글쓴이 : ${listinfo.writer}</p>
<p>작성 날짜 : ${listinfo.writeDate}</p>
<form method="post">
    <input type="hidden" name="idx" value="${listinfo.idx}">
    <input type="text" name="title" placeholder="제목" value="${listinfo.title}">

    <!-- jQuery 불러오기 -->
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <!-- ToastUI-editor 불러오기 -->
    <script src="https://uicdn.toast.com/editor/latest/toastui-jquery-editor.min.js"></script>
    <!-- editor 선언 -->
    <div id="editor" name="editor">
        ${listinfo.content}
    </div>
    <!-- editor의 속성 설정 -->
    <script>
        $('#editor').toastuiEditor({
            height: '500px',
            initialEditType: 'markdown',
            previewStyle: 'vertical',
        });
    </script>

    <button><a href="${pageContext.request.contextPath}/read?idx=${listinfo.idx}">취소</a></button>
    <button onclick="save()">저장</button>
</form>
</body>
</html>
<script>
    function save() {
        event.preventDefault();
        // 현재 입력된 editor의 값 불러오기
        const title     = $('input[name=title]').val();
        const content   = $('#editor').toastuiEditor('getHtml');

        var writeForm = $('<form></form>');
        writeForm.attr("name","writeForm");
        writeForm.attr("method","post");
        writeForm.append($('<input/>', {type: 'hidden', name: 'content', value: content }));
        writeForm.append($('<input/>', {type: 'hidden', name: 'title', value: title }));
        writeForm.appendTo('body');
        writeForm.submit();
    }
</script>
