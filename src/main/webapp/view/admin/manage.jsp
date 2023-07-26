<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Manage page</title>
</head>
    <body>
        <h2> Manage OnlineStore </h2>
        <h3> Учетная запись ${requestScope.login} </h3>
            <form action="admin/manage/create" method="GET">
                    <input type="submit" value="Добавить товар в базу данных">
            </form>

            <form action="admin/manage/get" method="GET">
                    <input type="submit" value="Просмотреть товары из базы данных">
            </form>

           <form action="admin/manage/update" method="GET">
                 <input type="submit" value="Обновить данные о товаре в базе данных">
           </form>

           <form action="admin/manage/delete" method="GET">
                 <input type="submit" value="Удалить товар из базы данных">
           </form>

    </body>
</html>