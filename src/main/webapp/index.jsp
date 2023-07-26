<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
    <body>
        <h2>OnlineStore</h2>
        <%
            if (request.getSession().getAttribute("auth") != null) { %>
                <a href="profile"><button>${sessionScope.auth.email}</button></a>
                <a href="catalog"><button>Каталог товаров</button></a>
            <% } else { %>
                <a href="reg"><button>Зарегистрироваться</button></a>
                <a href="auth"><button>Войти</button></a>
            <% } %>
    </body>
</html>
