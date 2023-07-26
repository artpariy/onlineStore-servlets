<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Catalog</title>
    <link rel="stylesheet" href="style/minimal-table.css">
</head>
    <body>
        <h2> Оформление заказа </h2>
        <form action="order" method="POST">
                        <p>Введите номер телефона:<br>
                        <input type="text" name="phoneNumber"/>
                        <br>
                        <p>Введите адрес для получения заказа:</p>
                        <input type="text" name="address"/>
                        <br>

        <h2> Товары в заказе </h2>
        <table>
         <tr>
                    <td></td>
                    <td>Бренд</td>
                    <td>Модель</td>
                    <td>Цвет</td>
                    <td>Цена</td>
         </tr>
        <c:forEach var="product" items="${basketList}" varStatus="counter" >
            <tr>
                <td>${counter.count}</td>
                <td>${product.brandName}</td>
                <td>${product.model}</td>
                <td>${product.colorName}</td>
                <td>${product.price}</td>
            </tr>
        </c:forEach>
        </table>
        <p> Общая сумма заказа: ${orderCost} рублей <p>
        <br>
        <input type="submit" value="Сделать заказ" />
        </form>
    </body>
</html>