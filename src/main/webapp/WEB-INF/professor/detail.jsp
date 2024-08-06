<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
