<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
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

        .sem-container {
            display: flex;
            width: 80%;
            height: 80%;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        .sem-left {
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

        .sem-left h2 {
            margin-bottom: 20px;
        }

        .sem-left .button-container {
            display: flex;
            flex-direction: column;
            width: 100%;
            gap: 10px;
        }

        .sem-left button,
        .sem-left a {
            text-decoration: none;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-weight: bold;
            text-align: center;
            width: 100%;
            box-sizing: border-box;
        }

        .sem-left button.primary {
            background-color: #28a745;
            color: white;
        }

        .sem-left a {
            display: flex;
            justify-content: center;
            align-items: center;
            background-color: white;
            color: black;
        }

        .sem-right {
            background-color: #ffffff;
            padding: 20px;
            width: 75%;
        }

        .sem-input-group {
            margin-bottom: 15px;
        }

        .sem-input-group label {
            display: block;
            margin-bottom: 5px;
        }

        .sem-input-group input,
        .sem-input-group textarea,
        .sem-input-group select {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
            margin-bottom: 20px;
            box-sizing: border-box;
        }

        .error-message {
            color: red;
            text-align: center;
            margin-bottom: 20px;
        }
    </style>

    <script>
        function setReceiver(value) {
            document.getElementById('receiver').value = value;
        }
    </script>

</head>
<body>

<%@include file="../include/header.jsp"%>

<div class="sem-container">
    <div class="sem-left">
        <h2>안녕하세요<br>학생 ${student.sid} 님</h2>

        <form action="/student/sendmsg" method="post">
            <div class="button-container">
                <button type="submit" class="primary">Send</button>
                <a href="/studentlist"><button type="button" class="secondary">List</button></a>
            </div>
    </div>

    <div class="sem-right">
        <div class="sem-input-group">
            <label for="title">TITLE</label>
            <input type="text" id="title" name="title" required>
        </div>

        <div class="sem-input-group">
            <label for="receiver">RECEIVER</label>
            <select id="receiver" name="receiver" onchange="setReceiver(this.value)" required>
                <option value="" selected disabled></option>
                <optgroup label="Students">
                    <c:forEach var="student" items="${studentList}">
                        <option value="${student}">${student}</option>
                    </c:forEach>
                </optgroup>
                <optgroup label="Professors">
                    <c:forEach var="professor" items="${professorList}">
                        <option value="${professor}">${professor}</option>
                    </c:forEach>
                </optgroup>
            </select>
        </div>

        <div class="sem-input-group">
            <label for="content">CONTENT</label>
            <textarea id="content" name="content" rows="10" required></textarea>
        </div>

        <c:if test="${not empty param.error}">
            <div class="error-message">${param.error}</div>
        </c:if>

        </form>
    </div>
</div>
</body>
</html>