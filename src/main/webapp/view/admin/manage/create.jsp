<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Create</title>
</head>
    <body>
        <h2> Manage OnlineStore </h2>
        <h3> Добавление товара </h3>
                <form action="created" method="POST">
                Тип товара: <input type="radio" name="productType" value="phone" /> Phone
                <input type="radio" name="productType" value="laptop" /> Laptop
                <br><br>
                Бренд товара: <select name="brand">
                    <option> Apple </option>
                    <option> Asus </option>
                </select>
                <br><br>
                Модель товара: <input type="text" name="model">
                <br><br>
                Дата выпуска (yyyy-mm-d): <input type="text" name="release">
                <br><br>
                Цена: <input type="text" name="price">
                <br><br>
                Цвет: <select name="color">
                                    <option>Чёрный</option>
                                    <option>Белый</option>
                                    <option>Серый</option>
                                    <option>Синий</option>
                                    <option>Золотой</option>
                                    <option>Красный</option>
                                    </select>
                <br><br>
                Артикул товара: <input type="text" name="vendorCode">
                <br><br>
                Количество добавляемого товара: <input type="text" name="count">
                <br><br>

                <input type="submit" value="Добавить товар" />
                </form>
    </body>
</html>