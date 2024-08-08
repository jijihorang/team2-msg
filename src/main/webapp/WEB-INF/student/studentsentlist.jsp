<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Sent Messages</title>
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

        .stls-container {
            display: flex;
            width: 85%;
            height: 85%;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        .stls-left {
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

        .stls-left h2 {
            margin-bottom: 20px;
        }

        .stls-left button {
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            background-color: white;
            color: black;
            font-weight: bold;
            margin-bottom: 10px;
            width: 100%;
        }

        .stls-button-danger {
            background-color: #dc3545;
            color: white;
        }

        .stls-right {
            background-color: #ffffff;
            padding: 20px;
            width: 75%;
        }

        .stls-tabs {
            display: flex;
            justify-content: space-around;
            margin-bottom: 20px;
        }

        .stls-tabs div {
            padding: 10px 20px;
            cursor: pointer;
            border-radius: 5px;
        }

        .stls-tabs .stls-active {
            background-color: #28a745;
            color: white;
        }

        .stls-tabs .stls-inactive {
            background-color: #e0e0e0;
            color: #757575;
        }

        .stls-tabs a {
            text-decoration: none; /* 모든 a 태그의 밑줄 없앰 */
        }

        .stls-messages {
            list-style: none;
            padding: 0;
        }

        .stls-message2 {
            display: flex;
            justify-content: space-between;
            padding: 10px;
            border-bottom: 1px solid #ddd;
        }

        .stls-message2 .stls-index {
            width: 5%;
        }

        .stls-message2 .stls-name {
            width: 20%;
        }

        .stls-message2 .stls-title {
            width: 20%;
        }

        .stls-message2 .stls-date {
            width: 15%;
            white-space: nowrap; /* 날짜를 한 줄로 출력 */
        }

        .stls-title a {
            text-decoration: none;
            color: black;
        }

        .stls-pagination {
            display: flex;
            justify-content: center;
            margin-top: 10px;
        }

        .stls-pagination .page-item {
            margin: 0 5px;
        }

        .stls-pagination .page-link {
            padding: 5px 10px;
            border: 1px solid #ddd;
            border-radius: 5px;
            color: #007bff;
            text-decoration: none;
        }

        .stls-pagination .page-link:hover {
            background-color: #007bff;
            color: white;
        }

        .stls-pagination .active .page-link {
            background-color: #007bff;
            color: white;
            border-color: #007bff;
        }
    </style>
</head>

<body>

<%@include file="../include/header.jsp"%>

<div class="stls-container">
    <div class="stls-left">
        <h2>안녕하세요<br>학생 ${studentName} 님</h2>

        <a href="/student/sendmsg"><button>쪽지 쓰기</button> </a>
        <form action="/studlogout" method="post">
            <button class="stls-button stls-button-danger">로그아웃</button>
        </form>
    </div>

    <div class="stls-right">
        <div class="stls-tabs">
            <a href="/studentlist"><div class="stls-inactive">받은 메일함</div></a>
            <div class="stls-active">보낸 메일함</div>
        </div>

        <ul class="stls-messages">
            <c:set var="count" value="1"/>
            <c:forEach var="message" items="${messages}" varStatus="status">
                <li class="stls-message2">
                    <span class="stls-index">${total - (page - 1)*size - status.index}</span>
                    <c:set var="count" value="${count + 1}"/>
                    <span class="stls-name">${message.receiver}</span>
                    <span class="stls-title">
                        <a href="/studetail?messageId=${message.mno}">${message.title}</a>
                    </span>
                    <span class="stls-date">
                        <fmt:formatDate value="${message.senddate}" pattern="yyyy-MM-dd"/>
                    </span>
                </li>
            </c:forEach>
        </ul>

        <nav aria-label="Page navigation">
            <ul class="stls-pagination pagination">
                <c:if test="${pageInfo.prev}">
                    <li class="page-item">
                        <a class="page-link" href="?page=${pageInfo.start - 1}" aria-label="Previous">
                            <span aria-hidden="true">prev</span>
                        </a>
                    </li>
                </c:if>
                <c:forEach begin="${pageInfo.start}" end="${pageInfo.end}" var="i">
                    <li class="page-item ${i == pageInfo.page ? 'active' : ''}">
                        <a class="page-link" href="?page=${i}">${i}</a>
                    </li>
                </c:forEach>
                <c:if test="${pageInfo.next}">
                    <li class="page-item">
                        <a class="page-link" href="?page=${pageInfo.end + 1}" aria-label="Next">
                            <span aria-hidden="true">next</span>
                        </a>
                    </li>
                </c:if>
            </ul>
        </nav>
    </div>
</div>
</body>
</html>
