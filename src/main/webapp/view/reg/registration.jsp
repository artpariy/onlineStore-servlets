<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Регистрация</title>
</head>
    <body>
        <h2>Registration Page</h2>
        <form action="success-reg" method="POST">
            <p>Введите имя</p>
            Имя: <input type="text" name="firstName">
            <br><br>
            <p>Введите фамилию</p>
            Фамилия: <input type="text" name="lastName">
            <br><br>
            <p>Введите email</p>
            Email: <input type="text" name="email" />
            <br><br>
            <p>Придумайте пароль</p>
            Пароль: <input type="password" name="password"/ id="myInput">
            <br><br>
            <input type="checkbox" onclick="myFunction()"> Показать пароль
            <br><br>
            <input type="submit" value="Отправить данные">
        </form>

        <script>
            function myFunction() {
              var x = document.getElementById("myInput");
              if (x.type === "password") {
                x.type = "text";
              } else {
                x.type = "password";
              }
            }
        </script>
    </body>
</html>