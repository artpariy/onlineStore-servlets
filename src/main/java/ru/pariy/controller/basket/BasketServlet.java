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

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.util.List;

@WebServlet("/basket")
public class BasketServlet extends LoggerServlet {
    private final Logger logger = LogManager.getLogger(BasketServlet.class);

    @Override
    public void performPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void performGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("html/text");
        PrintWriter out = response.getWriter();
        List<Product> basketList = getListFromSession(request);
        if (request.getParameter("action") != null) {

            basketList = deleteProduct(request);
            out.println(basketList);
        }
        request.setAttribute("basketList", basketList);
        request.getRequestDispatcher("/view/basket/basket.jsp").forward(request, response);
    }

    @Override
    public Logger getLogger() {
        return logger;
    }

    /**
     * Метод для получения списка товаров в корзине
     */
    private List<Product> getListFromSession(HttpServletRequest request) throws ServletException {
        HttpSession session = request.getSession(false);
        if (session == null) {
            throw new ServletException("User not authorization");
        } else {
            return (List<Product>) session.getAttribute(Constant.BASKET_SESSION_ATR);
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

//    private String getJson(List<Product> list) {
//        ObjectMapper objectMapper = new ObjectMapper();
//        try {
//            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(list);
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
//    }

//    private void writeJson(String json, HttpServletResponse response) throws IOException {
//        response.setContentType("application/json");
//        PrintWriter printWriter = response.getWriter();
//        printWriter.write(json);
//        printWriter.close();
//    }
}
