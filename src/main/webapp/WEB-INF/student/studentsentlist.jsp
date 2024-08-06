<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Student List Page</title>
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
            width: 80%;
            height: 80%;
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
            background-color: #e0e0e0;
            color: #757575;
        }

        .stls-tabs .stls-inactive {
            background-color: #28a745;
            color: white;
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
            width: 10%;
        }

        .stls-message2 .stls-name {
            width: 30%;
        }

        .stls-message2 .stls-title {
            width: 40%;
        }

        .stls-message2 .stls-status {
            width: 20%;
            text-align: right;
        }
    </style>
</head>

<body>
<div class="stls-container">
    <div class="stls-left">
        <h2>안녕하세요<br>학생 JIHO 님</h2>
        <button>쪽지 쓰기</button>
    </div>
    <div class="stls-right">
        <div class="stls-tabs">
            <a href="/studentlist"><div class="stls-active">받은 메일함</div></a>
            <div class="stls-inactive">보낸 메일함</div>
        </div>
        <ul class="stls-messages">
            <li class="stls-message2">
                <span class="stls-index">10.</span>
                <span class="stls-name">받는 사람 : 주희님</span>
                <span class="stls-title">오늘은 운동 쉽니다</span>
                <span class="stls-status">[ 안읽음 ] </span>
            </li>
        </ul>
    </div>
</div>
</body>
</html>
