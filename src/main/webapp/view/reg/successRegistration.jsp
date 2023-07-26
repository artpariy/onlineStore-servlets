<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Welcome to OnlineStore</title>
</head>
    <body>
        <h3> Вы успешно зарегистрировались, ${firstName} ${lastName} </h3>
        <h3> Ваш email: ${email}</h3>

        <form action="index.jsp" method="GET">
                    <input type="submit" value="To main page">
        </form>

    </body>
</html>