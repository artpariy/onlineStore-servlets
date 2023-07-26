<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>User profile</title>
</head>
<body>
       <h3>  Hello, ${name} Welcome to profile on OnlineStore</h3>

       <form action="index.jsp" method="GET">
                    <input type="submit" value="На главную страницу">
       </form>

       <form action="basket" method="GET">
                           <input type="submit" value="Корзина">
       </form>

       <form action="logout" method="GET">
            <input type="submit" value="Выйти">
       </form>

</body>
</html>