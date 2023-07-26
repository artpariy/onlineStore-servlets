<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Catalog</title>
    <link rel="stylesheet" href="style/minimal-table.css">
</head>
    <body>
        <h2> OnlineStore catalog </h2>
        <h3> Каталог товаров </h3>

        <a href="basket"><button>Корзина товаров</button></a>
        <br><br>

        <div id="countElements"> </div>

        <table id="table">
          <tr>
            <td>Brand</td>
            <td>Model</td>
            <td>Release</td>
            <td>Price</td>
            <td>Color</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
          <c:forEach var="product" items="${products}">
          <c:if test="${product.brandId == 1}">
                 <tr id = "${product.id}">
                    <td> Apple </td>
                    <td> ${product.model} </td>
                    <td> ${product.release} </td>
                    <td> ${product.price} </td>
                    <td> ${product.colorName} </td>
                    <td> <input type ="button" value="+" class ="add"/> </td>
                    <td> <input type ="button" value="-" class ="delete"/> </td>
                    <td> &nbsp; </td>
                 </tr>
          </c:if>
          <c:if test="${product.brandId == 2}">
                  <tr id = "${product.id}">
                    <td> ASUS </td>
                    <td> ${product.model} </td>
                    <td> ${product.release}</td>
                    <td> ${product.price}</td>
                    <td> ${product.colorName}</td>
                    <td> <input type ="button" value="+" class ="add"/> </td>
                    <td> <input type ="button" value="-" class ="delete"/> </td>
                    <td> &nbsp; </td>
                  </tr>
          </c:if>
          </c:forEach>
        </table>

            <script>
                    let allButtonsAdd = document.getElementsByClassName('add');
                    for (let button of allButtonsAdd) {
                        button.addEventListener('click', () => {
                            var clickedElement = event.target;
                            var id = clickedElement.parentNode.parentNode.id;
                            let url = "dynamic-basket?action=add&id=" + id;
                            var xmlHttp = new XMLHttpRequest();
                            xmlHttp.open("GET", url, false); // false for synchronous request
                            console.log(url);
                            xmlHttp.send(null);
                            console.log(xmlHttp.responseText);
                            var div = document.getElementById('countElements');

                            let basketList = JSON.parse(xmlHttp.responseText);
                            console.log(basketList);
                            div.innerHTML = basketList.length;
                            updateTableData(basketList);
                            });
                        }

                    let allButtonsDelete = document.getElementsByClassName('delete');
                    for (let button of allButtonsDelete) {
                            button.addEventListener('click', () => {
                                        var clickedElement = event.target;
                                        var id = clickedElement.parentNode.parentNode.id;
                                        let url = "dynamic-basket?action=delete&id=" + id;
                                        var xmlHttp = new XMLHttpRequest();
                                        xmlHttp.open("GET", url, false);
                                        xmlHttp.send(null);
                                        console.log(xmlHttp.responseText);
                                        var div = document.getElementById('countElements');
                                       let basketList = JSON.parse(xmlHttp.responseText);
                                       console.log(basketList);
                                       div.innerHTML = basketList.length;
                                       updateTableData(basketList);
                            });
                    }

                    function countContains(productId, basketProducts) {
                        counter = 0;
                        basketProducts.forEach(element => {
                            console.log(element);
                            if (element.id == productId) {
                                counter++;
                            }
                        });
                        return counter;
                    }


                    function updateTableData(basketList) {
                      var table = document.getElementById("table")
                      for (var i = 1, row; row = table.rows[i]; i++) {
                            var productId = row.id;

                            var count = countContains(productId, basketList);

                            col = row.cells[row.cells.length - 1];
                            col.innerHTML = count;
                      }
                    }

               </script>
    </body>
</html>