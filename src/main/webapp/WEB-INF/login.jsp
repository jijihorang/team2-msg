<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>login</title>
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

        .container {
            display: flex;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        .left {
            background-color: #eef5ff;
            padding: 100px;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
        }

        .secure-section {
            text-align: center;
        }

        .secure-section .icon img {
            width: 100px;
            height: 100px;
        }

        .right {
            background-color: #0044cc;
            color: white;
            padding: 40px;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
        }

        h1 {
            margin-bottom: 20px;
        }

        form {
            width: 300px;
        }

        .input-group {
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

        .create-account {
            background-color: #ffcc00;
            color: black;
            margin-bottom: 10px;
        }

        .sign-in {
            background-color: transparent;
            border: 1px solid white;
            color: white;
        }
    </style>
    <link rel="icon" href="data:,">
</head>
<body>
<div class="container">
    <div class="left">
        <div class="secure-section">
            <div class="icon">
                <img src="/img/key-icon.png" alt="Key Icon">
            </div>
            <h2>Secure</h2>
        </div>
    </div>
    <div class="right">
        <h1>Welcome !</h1>
        <form action="/login" method="post">
            <div class="input-group">
                <label for="sid">ID</label>
                <input type="text" id="sid" name="sid" required>
            </div>
            <div class="input-group">
                <label for="password">PASSWORD</label>
                <input type="password" id="password" name="password" required >
            </div>
            <button type="submit" class="create-account">Login</button>
            <a href="/sign"><button type="button" class="sign-in">Sign Up</button></a>
        </form>
    </div>
</div>
</body>
</html>
