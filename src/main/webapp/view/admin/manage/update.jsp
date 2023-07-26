<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Create</title>
</head>
    <body>
        <h2> Manage OnlineStore </h2>
        <h3> Обновление данных товара </h3>
        <form action="updated" method="POST">
           Введите артикул товара: <input type="text" name="vendorCode">
          <br><br>
          Введите количество добавляемого товара: <input type="text" name="count">
          <br><br>
          <input type="submit" value="Submit" />
       </form>
    </body>
</html>