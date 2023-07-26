package ru.pariy.controller.basket;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.pariy.config.Constant;
import ru.pariy.controller.common.LoggerServlet;
import ru.pariy.model.product.Product;
import ru.pariy.model.repository.ProductDAO;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


@WebServlet("/dynamic-basket")
public class DynamicBasketServlet extends LoggerServlet {

    private final Logger basketLogger = LogManager.getLogger(DynamicBasketServlet.class);

    @Override
    public void performPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void performGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String productsOnBasket;
        List<Product> productList;
        if (req.getParameter("action").equals("add")) {
            productList = addProduct(req);
            productsOnBasket = getJson(productList);
            writeJson(productsOnBasket, resp);
        } else if (req.getParameter("action").equals("delete")) {
            productList = deleteProduct(req);
            productsOnBasket = getJson(productList);
            writeJson(productsOnBasket, resp);
        }
    }

    @Override
    public Logger getLogger() {
        return basketLogger;
    }

    /**
     * Метод для добавления товаров в корзину
     */
    private List<Product> addProduct(HttpServletRequest request) throws ServletException {
        HttpSession session = request.getSession(false);
        if (session == null) {
            throw new ServletException("User not authorization");
        }
        List<Product> productList = (List<Product>) session.getAttribute(Constant.BASKET_SESSION_ATR);
        Long id = Long.valueOf(request.getParameter("id"));
        String sessionId = session.getId().intern();
        synchronized (sessionId) {
            if (productList == null) {
                productList = new ArrayList<>();
                session.setAttribute(Constant.BASKET_SESSION_ATR, productList);
                Product product = ProductDAO.getById(id);
                productList.add(product);
            } else {
                Product product = ProductDAO.getById(id);
                productList.add(product);
            }
            return productList;
        }
    }

    /**
     * Метод для удаления товаров из корзины
     */
    private List<Product> deleteProduct(HttpServletRequest request) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null) {
            throw new ServletException("User not authorization");
        }
        List<Product> productList = (List<Product>) session.getAttribute(Constant.BASKET_SESSION_ATR);
        int id = Integer.parseInt(request.getParameter("id"));
        if (productList == null || productList.size() == 0) {
            return productList;
        } else {
            Product forRemoving = null;
            for (Product product : productList) {
                if (product.getId() == id) {
                    forRemoving = product;
                    break;
                }
            }
            productList.remove(forRemoving);
        }
        return productList;
    }

    private String getJson(List<Product> list) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(list);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private void writeJson(String json, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        PrintWriter printWriter = response.getWriter();
        printWriter.write(json);
        printWriter.close();
    }
}