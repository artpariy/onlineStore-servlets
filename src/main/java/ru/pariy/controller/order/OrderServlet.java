package ru.pariy.controller.order;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ru.pariy.config.Constant;
import ru.pariy.controller.common.LoggerServlet;
import ru.pariy.controller.common.SessionNotFoundException;
import ru.pariy.entity.AuthUser;
import ru.pariy.entity.Order;
import ru.pariy.model.product.Product;
import ru.pariy.model.repository.OrderDAO;
import ru.pariy.model.repository.ProductDAO;

import java.io.IOException;
import java.io.PrintWriter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

@WebServlet("/order")
public class OrderServlet extends LoggerServlet {
    private final Logger logger = LogManager.getLogger(OrderServlet.class);

    @Override
    public void performPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter printWriter = response.getWriter();
        if (!quantityCompare(getBasketSession(request, response))) {
            try (PrintWriter out = response.getWriter()) {
                out.println("<p> На складе недостаточно товара, указанного в заказе, измените заказ</p>");
                getServletContext().getRequestDispatcher("/view/catalog/productSelect.jsp").include(request, response);
            }
        } else {
            OrderDAO.addToOrders(formationOrder(request, response)); // добавляем заказ в таблицу orders и получаем id заказа
            List<Long> orderIdList = OrderDAO.readIdFromOrders(); // получаем список id сформированных заказов
            long orderId = findLastAddedId(orderIdList); // находим последний сформированный заказ

            List<Product> basketList = (List<Product>) getBasketSession(request, response).getAttribute(Constant.BASKET_SESSION_ATR);
            for (Product product : basketList) {
                OrderDAO.addToOrdersInfo(orderId, product); // добавляем информацию о товаре в заказе в таблицу orders_info
                ProductDAO.updateCountOnStock(1, product.getVendorCode());
            }
            printWriter.println("<p> Заказ успешно оформлен </p>");
            getServletContext().getRequestDispatcher("/index.jsp").include(request, response);
        }
    }

    @Override
    public void performGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> basketList = (List<Product>) getBasketSession(request, response).getAttribute(Constant.BASKET_SESSION_ATR);

        if (basketList == null || basketList.size() == 0) {
            try (PrintWriter out = response.getWriter()) {
                out.println("<p> В корзине нет товаров, добавьте товар в корзину и повторите попытку");
                getServletContext().getRequestDispatcher("/view/catalog/productSelect.jsp").include(request, response);
            }
        } else {
            request.setAttribute("basketList", basketList);
            request.setAttribute("orderCost", orderCost(basketList));
            getServletContext().getRequestDispatcher("/view/order/order.jsp").forward(request, response);
        }
    }


    private double orderCost(List<Product> basketList) {
        double orderCost = 0;
        for (Product product : basketList) {
            orderCost += product.getPrice();
        }
        return orderCost;
    }

    private boolean quantityCompare(HttpSession session) {
        List<Product> basketList = (List<Product>) session.getAttribute(Constant.BASKET_SESSION_ATR);
        for (Product product : basketList) {
            if (ProductDAO.readProduct(product.getVendorCode()).getCountOnStock() < basketList.size()) {
                return false;
            }
        }
        return true;
    }

    private HttpSession getBasketSession(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            try (PrintWriter out = response.getWriter()) {
                out.println("<p> Товар не найден в корзине, добавьте товар и повторите оформление заказа </p>");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return session;
    }

    private Order formationOrder(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            String phoneNumber = request.getParameter("phoneNumber");
            String address = request.getParameter("address");
            AuthUser authUser = (AuthUser) session.getAttribute(Constant.AUTH_SESSION_ATR);
            String userLogin = authUser.getEmail();
            logger.info("Пользователь " + userLogin + " сделал заказ");
            return new Order(phoneNumber, address, userLogin);
        }
        throw new SessionNotFoundException("Session not found");
    }

    private long findLastAddedId(List<Long> orderIdList) {
        return Collections.max(orderIdList);
    }

    @Override
    public Logger getLogger() {
        return logger;
    }
}
