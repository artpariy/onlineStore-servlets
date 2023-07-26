<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Basket</title>
    <link rel="stylesheet" href="style/minimal-table.css">
    <head>
        <body>
                <h2> OnlineStore basket </h2>
                <h3> Корзина товаров </h3>
                <table id="basketTable">
                          <c:forEach var="product" items="${basketList}">
                          <c:if test="${product.brandId == 1}">
                                 <tr productId = "${product.id}">
                                    <td> Apple </td>
                                    <td> ${product.model} </td>
                                    <td> ${product.release} </td>
                                    <td> ${product.price} </td>
                                    <td> ${product.colorName} </td>
                                    <td> <input type ="button" value="-" class ="delete"/></td>
                                 </tr>
                          </c:if>
                          <c:if test="${product.brandId == 2}">
                                  <tr productId = "${product.id}">
                                    <td> ASUS </td>
                                    <td> ${product.model} </td>
                                    <td> ${product.release}</td>
                                    <td> ${product.price}</td>
                                    <td> ${product.colorName}</td>
                                    <td> <input type ="button" value="-" class ="delete"/> </td>
                                  </tr>
                          </c:if>
                          </c:forEach>
                        </table>

                        <a href="order"><button>Оформить заказ</button></a>
                                <br><br>

                                            <script>
                                                let allButtonsDelete = document.getElementsByClassName('delete');
                                                                   for (let button of allButtonsDelete) {
                                                                           button.addEventListener('click', () => {
                                                                                       var clickedElement = event.target;
                                                                                       var id = clickedElement.parentNode.parentNode.getAttribute("productId");
                                                                                       var tableRow = clickedElement.parentNode.parentNode;
                                                                                       let url = "basket?action=delete&id=" + id;
                                                                                       var xmlHttp = new XMLHttpRequest();
                                                                                       xmlHttp.open("GET", url, false);
                                                                                       xmlHttp.send(null);
                                                                                       console.log(xmlHttp.responseText);

                                                                                       if (xmlHttp.status != 200) {
                                                                                            alert("Ошибка удаления элемента");
                                                                                            return;
                                                                                       }
                                                                                      tableRow.remove();

                                                                           });
                                                                   }
                                 </script>



        </body>
    </head>