<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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

        .pfl-container {
            display: flex;
            width: 80%;
            height: 80%;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        .pfl-left {
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

        .pfl-left h2 {
            margin-bottom: 30px;
        }

        .pfl-button {
            display: inline-block;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            background-color: white;
            color: black;
            font-weight: bold;
            margin-bottom: 10px;
            text-align: center;
            text-decoration: none;
            width: 150px; /* 고정된 너비를 설정 */
        }

        .pfl-button-primary {
            background-color: white;
            color: black;
        }

        .pfl-button-secondary {
            background-color: white;
            color: black;
        }

        .pfl-button-danger {
            background-color: #dc3545;
            color: white;
        }

        .pfl-right {
            background-color: #ffffff;
            padding: 20px;
            width: 75%;
        }

        .pfl-tabs {
            display: flex;
            justify-content: space-around;
            margin-bottom: 20px;
        }

        .pfl-tabs div {
            padding: 10px 20px;
            cursor: pointer;
            border-radius: 5px;
        }

        .pfl-tabs .pfl-active {
            background-color: #28a745;
            color: white;
        }

        .pfl-tabs .pfl-inactive {
            background-color: #e0e0e0;
            color: #757575;
        }

        .pfl-tabs a {
            text-decoration: none;
        }

        .pfl-messages {
            list-style: none;
            padding: 0;
        }

        .pfl-message2 {
            display: flex;
            justify-content: space-between;
            padding: 10px;
            border-bottom: 1px solid #ddd;
        }

        .pfl-message2 .pfl-index {
            width: 10%;
        }

        .pfl-message2 .pfl-name {
            width: 30%;
        }

        .pfl-message2 .pfl-title {
            width: 40%;
        }

        .pfl-message2 .pfl-date {
            width: 15%;
            white-space: nowrap;
        }

        .pfl-message2 .pfl-status {
            width: 20%;
            text-align: right;
        }

        .pagination {
            display: flex;
            justify-content: center;
            margin-top: 20px;
        }

        .pagination .page-item {
            margin: 0 5px;
        }

        .pagination .page-link {
            padding: 5px 10px;
            border: 1px solid #ddd;
            border-radius: 5px;
            color: #007bff;
            text-decoration: none;
        }

        .pagination .page-link:hover {
            background-color: #007bff;
            color: white;
        }

        .pagination .active .page-link {
            background-color: #007bff;
            color: white;
            border-color: #007bff;
        }
    </style>
</head>
<body>

<%@include file="../include/header.jsp"%>

<div class="pfl-container">
    <div class="pfl-left">
        <h2>안녕하세요<br>${professorId} 교수님</h2>

        <a href="/professor/sendmsg" class="pfl-button pfl-button-primary">쪽지 쓰기</a>
        <a href="/professor/broadcastmsg" class="pfl-button pfl-button-secondary">전체쪽지쓰기</a>
        <form action="/proflogout" method="post">
            <button type="submit" class="pfl-button pfl-button-danger">로그아웃</button>
        </form>
    </div>
    <div class="pfl-right">
        <div class="pfl-tabs">
            <div class="pfl-active">받은 메일함</div>
            <a href="/proflist/sent"><div class="pfl-inactive">보낸 메일함</div></a>
        </div>
        <ul class="pfl-messages">
            <c:forEach var="message" items="${messages}" varStatus="status">
                <li class="pfl-message2">
                    <span class="pfl-index">${status.count}.</span>
                    <span class="pfl-name">${message.sender}</span>
                    <span class="pfl-title">
                        <a href="/detail?messageId=${message.mno}">${message.title}</a>
                    </span>
                    <span class="pfl-date">
                        <fmt:formatDate value="${message.senddate}" pattern="yyyy-MM-dd"/>
                    </span>
                    <span class="pfl-status">
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

        <nav aria-label="Page navigation">
            <ul class="pagination">
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
