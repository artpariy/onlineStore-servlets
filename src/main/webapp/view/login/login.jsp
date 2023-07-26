<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Welcome page</title>
</head>
<body>
    <h2>Авторизация</h2>
        <form action="login" method="POST">
            <p>Введите электронную почту</p>
            email: <input type="text" name="email"/>
            <br><br>
            <p>Введите пароль</p>
            Пароль: <input type="text" name="password"/>
            <br><br>
            <input type="submit" value="Login">
            </form>
    </body>
</html>