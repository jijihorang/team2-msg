<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Message inbox</title>
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

        .std-container {
            display: flex;
            width: 80%;
            height: 80%;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        .std-left {
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

        .std-left h2 {
            margin-bottom: 20px;
        }

        .std-left button {
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            background-color: white;
            color: black;
            font-weight: bold;
        }

        .std-right {
            background-color: #ffffff;
            padding: 20px;
            width: 75%;
        }

        .std-tabs {
            display: flex;
            justify-content: space-around;
            margin-bottom: 20px;
        }

        .std-tabs div {
            padding: 10px 20px;
            cursor: pointer;
            border-radius: 5px;
        }

        .std-tabs .std-active {
            background-color: #28a745;
            color: white;
        }

        .std-tabs .std-inactive {
            background-color: #e0e0e0;
            color: #757575;
        }

        .std-messages {
            list-style: none;
            padding: 0;
        }

        .std-message2 {
            display: flex;
            justify-content: space-between;
            padding: 10px;
            border-bottom: 1px solid #ddd;
        }

        .std-message2 {
            width: 10%;
        }

        .std-message2 {
            width: 30%;
        }

        .std-message2 {
            width: 40%;
        }

        .std-message2 {
            width: 20%;
            text-align: right;
        }

        .std-textbox {

        }
    </style>
</head>

<body>
<div class="std-container">
    <div class="std-left">
        <h2>안녕하세요<br>학생 ${member.sid} 님</h2>
        <button>쪽지 쓰기</button>
    </div>
    <div class="std-right">
        <div class="std-tabs">
            <div class="std-active">받은 메일함</div>
            <div class="std-inactive">보낸 메일함</div>
        </div>
        <form>
            <ul class="std-messages">
                <li class="std-message2">title: ${detail.title}</li>
                <li class="std-message2">send: ${detail.sender}</li>
                <li class="std-message2">date: ${detail.senddate}</li>
                <textarea class="std-textbox">${detail.content}</textarea>
            </ul>
        </form>
    </div>
</div>
</body>
</html>
