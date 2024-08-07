<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Send Message</title>
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

        .pem-container {
            display: flex;
            width: 80%;
            height: 80%;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        .pem-left {
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

        .pem-left h2 {
            margin-bottom: 20px;
        }

        .pem-left button {
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            background-color: white;
            color: black;
            font-weight: bold;
            margin-bottom: 10px;
        }

        .pem-right {
            background-color: #ffffff;
            padding: 20px;
            width: 75%;
        }

        .pem-input-group {
            margin-top: 10px;
            margin-bottom: 15px;
        }

        .pem-input-group label {
            display: block;
            margin-bottom: 5px;
        }

        .pem-input-group input,
        .pem-input-group textarea {
            width: 95%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
            margin-bottom: 20px;
        }

        .pem-button-group {
            display: flex;
            justify-content: center;
            margin-top: 20px;
        }

        .pem-button-group button {
            margin-top: 15px;
            margin-left: 10px;
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        .pem-button-group button.primary {
            background-color: #4CAF50;
            color: white;
        }

        .pem-button-group button.secondary {
            background-color: #ccc;
            color: black;
        }
    </style>
</head>
<body>
<%@include file="../include/header.jsp"%>
<div class="pem-container">
    <div class="pem-left">
        <h2>안녕하세요<br>교수 ${professorId}님</h2>
        <form action="/proflogout" method="post">
            <button>로그아웃</button>
        </form>
    </div>
    <div class="pem-right">
        <form action="/professor/sendmsg" method="post">
            <div class="pem-input-group">
                <label for="title">TITLE</label>
                <input type="text" id="title" name="title" required>
            </div>
            <div class="pem-input-group">
                <label>RECEIVER</label>
                <div id="receiverList">
                    <c:forEach var="student" items="${studentList}">
                        <input type="checkbox" class="student-checkbox" value="${student}" id="student_${student}" name="receivers">
                        <label for="student_${student}">${student}</label><br>
                    </c:forEach>
                </div>
            </div>
            <div class="pem-input-group">
                <label for="content">CONTENT</label>
                <textarea id="content" name="content" rows="10" required></textarea>
            </div>
            <div class="pem-button-group">
                <button type="submit" class="primary">Send</button>
                <a href="/proflist"><button type="button" class="secondary">List</button></a>
            </div>
        </form>
    </div>
</div>
</body>
</html>
