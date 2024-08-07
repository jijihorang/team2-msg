<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Professor List Page</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <style>
        body {
            margin: 0;
            font-family: Arial, sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background-color: #f4f4f4;
        }

        a {
            text-decoration: none;
        }

        .pfls-container {
            display: flex;
            width: 80%;
            height: 80%;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        .pfls-left {
            background-color: #007bff;
            padding: 40px;
            width: 25%;
            color: white;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            text-align: center;
        }

        .pfls-left h2 {
            margin-bottom: 20px;
        }

        .pfls-left button {
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            background-color: white;
            color: black;
            font-weight: bold;
            margin-bottom: 10px;
        }

        .pfls-right {
            background-color: #ffffff;
            padding: 20px;
            width: 75%;
        }

        .pfls-tabs {
            display: flex;
            justify-content: space-around;
            margin-bottom: 20px;
        }

        .pfls-tabs div {
            padding: 10px 20px;
            cursor: pointer;
            border-radius: 5px;
        }

        .pfls-tabs .pfls-active {
            background-color: #28a745;
            color: white;
        }

        .pfls-tabs .pfls-inactive {
            background-color: #e0e0e0;
            color: #757575;
        }

        .pfls-messages {
            list-style: none;
            padding: 0;
        }

        .pfls-message2 {
            display: flex;
            justify-content: space-between;
            padding: 10px;
            border-bottom: 1px solid #ddd;
        }

        .pfls-message2 .pfls-index {
            width: 10%;
        }

        .pfls-message2 .pfls-name {
            width: 30%;
        }

        .pfls-message2 .pfls-title {
            width: 40%;
        }

        .pfls-message2 .pfls-status {
            width: 20%;
            text-align: right;
        }
    </style>
</head>

<body>
<%@include file="../include/header.jsp"%>
<div class="pfls-container">
    <div class="pfls-left">
        <h2>안녕하세요<br>${professorId} 교수님</h2>
        <h2><br>본인이메일: ${professorEmail}</h2>
        <a href="/professor/sendmsg"><button>쪽지 쓰기</button></a>
        <form action="/proflogout" method="post">
            <button>로그아웃</button>
        </form>
    </div>
    <div class="pfls-right">
        <div class="pfls-tabs">
            <a href="/proflist"><div class="pfls-inactive">받은 메일함</div></a>
            <a href="/proflist/sent"><div class="pfls-active">보낸 메일함</div></a>
        </div>
        <ul class="pfls-messages">
            <c:forEach var="message" items="${messages}" varStatus="status">
                <li class="pfls-message2">
                    <span class="pfls-index">${status.count}.</span>
                    <span class="pfls-name">${message.receiver}</span>
                    <span class="pfls-title">
                        <a href="/detail?messageId=${message.mno}">${message.title}</a>
                    </span>
                    <span class="pfls-status">
                        <c:choose>
                            <c:when test="${message.is_read}">
                                [ 읽음 ]
                            </c:when>
                            <c:otherwise>
                                [ 안읽음 ]
                            </c:otherwise>
                        </c:choose>
                    </span>
                </li>
            </c:forEach>
        </ul>
    </div>
</div>
</body>
</html>