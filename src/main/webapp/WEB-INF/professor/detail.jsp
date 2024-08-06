<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>쪽지 상세 정보</title>
    <style>
        .container {
            width: 80%;
            margin: auto;
            padding: 20px;
            border: 1px solid #ddd;
            border-radius: 5px;
        }
        .details {
            margin-bottom: 20px;
        }
        .buttons {
            text-align: right;
        }
        .buttons button {
            margin-left: 10px;
            padding: 10px 15px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        .reply-button {
            background-color: #4CAF50; /* Green */
            color: white;
        }
        .back-button {
            background-color: #f44336; /* Red */
            color: white;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>쪽지 상세 정보</h2>
    <div class="details">
        <p><strong>발신자:</strong> ${message.sender}</p>
        <p><strong>제목:</strong> ${message.title}</p>
        <p><strong>내용:</strong> ${message.content}</p>
        <p><strong>발송일:</strong> ${message.senddate}</p>
        <p><strong>읽음:</strong> ${message.is_read ? '읽음' : '안읽음'}</p>
        <p><strong>전체 공지:</strong> ${message.is_broadcast ? '예' : '아니오'}</p>
    </div>
    <div class="buttons">
        <a href="/reply?id=${message.mno}">
            <button class="reply-button">답장</button>
        </a>
        <a href="/proflist">
            <button class="back-button">나가기</button>
        </a>
    </div>
</div>
</body>
</html>