<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Create</title>
</head>
    <body>
        <h2> Manage OnlineStore </h2>
        <h3> Удаление товара </h3>
        <form action="deleted" method="POST">
           Введите артикул удаляемого товара: <input type="text" name="vendorCode">
          <br><br>
          <input type="submit" value="Удалить" />
       </form>
    </body>
</html>