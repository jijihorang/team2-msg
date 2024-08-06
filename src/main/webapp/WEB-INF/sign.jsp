<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>sign</title>
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

        .sign-container {
            display: flex;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 80%; /* 화면의 80%를 차지하도록 설정 */
            height: 80%; /* 화면의 80%를 차지하도록 설정 */
        }

        .sign-left {
            background-color: #eef5ff;
            padding: 100px;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            flex: 1; /* 화면의 절반을 차지하도록 설정 */
        }

        .sign-secure-section {
            text-align: center;
        }

        .sign-secure-section .sign-icon img {
            width: 150px; /* 아이콘 크기 조정 */
            height: 150px; /* 아이콘 크기 조정 */
        }

        .sign-right {
            background-color: #0044cc;
            color: white;
            padding: 60px; /* 패딩을 좀 더 넓게 설정 */
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            flex: 1; /* 화면의 절반을 차지하도록 설정 */
        }

        h1 {
            margin-bottom: 20px;
        }

        form {
            width: 100%; /* 폼의 너비를 100%로 설정 */
            max-width: 400px; /* 최대 너비를 400px로 설정 */
        }

        .sign-input-group {
            margin-bottom: 20px;
        }

        label {
            display: block;
            margin-bottom: 5px;
        }

        input {
            width: 100%;
            padding: 10px;
            border: none;
            border-radius: 5px;
        }

        button {
            width: 100%;
            padding: 10px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            margin-top: 10px;
        }

        .sign-sign-in {
            background-color: #ffcc00;
            color: black;
            margin-bottom: 10px;
        }

    </style>
</head>
<body>
<div class="sign-container">
    <div class="sign-left">
        <div class="sign-secure-section">
            <div class="sign-icon">
                <img src="/img/signuser.png" alt="User Icon" >
            </div>
            <h2>Sign Up</h2>
        </div>
    </div>
    <div class="sign-right">
        <h1>Welcome !</h1>
        <form action="/sign" method="post">
            <div class="sign-input-group">
                <label for="id">ID</label>
                <input type="text" id="id" name="id" required>
            </div>
            <div class="sign-input-group">
                <label for="email">E-MAIL</label>
                <input type="email" id="email" name="email" required>
            </div>
            <div class="sign-input-group">
                <label for="password">Password</label>
                <input type="password" id="password" name="password" required>
            </div>
            <button type="submit" class="sign-sign-in">Sign Up</button>
        </form>
    </div>
</div>
</body>
</html>
