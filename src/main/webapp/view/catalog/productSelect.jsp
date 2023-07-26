<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Create</title>
</head>
    <body>
        <h2> OnlineStore catalog </h2>
        <h3> Просмотр товаров онлайн магазина </h3>
                <form action="catalog" method="POST">
                Выберите тип товара: <input type="radio" name="productType" value="phone" /> Phone
                <input type="radio" name="productType" value="laptop"/> Laptop
                <br><br>
                <input type="submit" value="Submit" />
                </form>
    </body>
</html>