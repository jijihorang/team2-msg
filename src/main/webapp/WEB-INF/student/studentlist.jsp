<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <title>Received Messages</title>
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

    .stl-container {
      display: flex;
      width: 80%;
      height: 80%;
      box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }

    .stl-left {
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

    .stl-left h2 {
      margin-bottom: 20px;
    }

    .stl-left button {
      padding: 10px 20px;
      border: none;
      border-radius: 5px;
      cursor: pointer;
      background-color: white;
      color: black;
      font-weight: bold;
    }

    .stl-right {
      background-color: #ffffff;
      padding: 20px;
      width: 75%;
    }

    .stl-tabs {
      display: flex;
      justify-content: space-around;
      margin-bottom: 20px;
    }

    .stl-tabs div {
      padding: 10px 20px;
      cursor: pointer;
      border-radius: 5px;
    }

    .stl-tabs .stl-active {
      background-color: #28a745;
      color: white;
    }

    .stl-tabs .stl-inactive {
      background-color: #e0e0e0;
      color: #757575;
    }

    .stl-tabs a {
      text-decoration: none; /* 모든 a 태그의 밑줄 없앰 */
    }

    .stl-messages {
      list-style: none;
      padding: 0;
    }

    .stl-message2 {
      display: flex;
      justify-content: space-between;
      padding: 10px;
      border-bottom: 1px solid #ddd;
    }

    .stl-message2 .stl-index {
      width: 10%;
    }

    .stl-message2 .stl-name {
      width: 20%;
    }

    .stl-message2 .stl-title {
      width: 40%;
    }

    .stl-message2 .stl-status {
      width: 20%;
      text-align: right;
    }
  </style>
</head>

<body>
<%@include file="../include/header.jsp"%>
<div class="stl-container">
  <div class="stl-left">
    <h2>안녕하세요<br>학생 ${studentName} 님</h2>
    <a href="/student/sendmsg"><button>쪽지 쓰기</button></a>
  </div>
  <div class="stl-right">
    <div class="stl-tabs">
      <div class="stl-active">받은 메일함</div>
      <a href="/studentlist/sent"><div class="stl-inactive">보낸 메일함</div></a>
    </div>
    <ul class="stl-messages">
      <c:set var="count" value="1"/>
      <c:forEach var="message" items="${messages}">
        <li class="stl-message2">
          <span class="stl-index">${count}</span>
          <c:set var="count" value="${count + 1}"/>
          <span class="stl-name">${message.sender}</span>
          <span class="stl-title">${message.title}</span>
          <span class="stl-status">[ <c:choose>
            <c:when test="${message.is_read}">읽음</c:when>
            <c:otherwise>안읽음</c:otherwise>
          </c:choose> ]</span>
        </li>
      </c:forEach>
    </ul>
  </div>
</div>
</body>
</html>
