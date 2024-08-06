<%--
  Created by IntelliJ IDEA.
  User: 안ㅅㅏㅇㄱ
  Date: 2024-08-06
  Time: 오전 10:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Message_${detail.mno}</title>
</head>
<body>
    <form>
        <ul>
            <li>title: ${detail.title}</li>
            <li>title: ${detail.sender}</li>
            <li>title: ${detail.senddate}</li>
        </ul>
        <div>
            <textarea>${detail.content}</textarea>
        </div>
    </form>
</body>
</html>
