<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Welcome to OnlineStore</title>
</head>
    <body>
        <h3> Welcome to OnlineStore ${firstName} ${lastName} </h3>
        <h3> Ваш email: ${email}</h3>

        <form action="Выйти из профиля" method="GET">
            <input type="submit" value="Logout">
        </form>
        <form action="index.jsp" method="GET">
                    <input type="submit" value="На главную страницу">
        </form>

    </body>
</html>