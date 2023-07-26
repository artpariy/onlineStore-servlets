package ru.pariy.controller.manage;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.pariy.controller.common.LoggerServlet;
import ru.pariy.model.product.Product;
import ru.pariy.model.repository.ProductDAO;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/admin/manage/updated")
public class UpdateServlet extends LoggerServlet {
    private final Logger updateLogger = LogManager.getLogger(UpdateServlet.class);

    @Override
    public void performPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int vendorCode = Integer.parseInt(request.getParameter("vendorCode"));
        int addedQuantity = Integer.parseInt(request.getParameter("count"));
        Product beforeUpdate = getDataFromProduct(vendorCode);
        int changedRows = setDataToProduct(beforeUpdate, vendorCode, addedQuantity);
        Product afterUpdate = getDataFromProduct(vendorCode);

        displayInfoProduct(response, beforeUpdate, afterUpdate);
    }

    @Override
    public void performGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public Logger getLogger() {
        return updateLogger;
    }

    private Product getDataFromProduct(int vendorCode) {
        return ProductDAO.readProduct(vendorCode);
    }

    private int setDataToProduct(Product product, int vendorCode, int addedQuantity) {
        return ProductDAO.updateProduct(vendorCode, product.getCountOnStock(), addedQuantity);
    }

    private void displayInfoProduct(HttpServletResponse response, Product beforeUpdate, Product afterUpdate) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<h3> Данные товара перед обновлением </h3>");
        out.println("<p> Model: " + beforeUpdate.getModel() +"</p>");
        out.println("<p> Count on Stock: " + beforeUpdate.getCountOnStock() +"</p>");
        out.println("<br><br>");
        out.println("<h3> Данные товара после обновления </h3>");
        out.println("<p> Model: " + afterUpdate.getModel() +"</p>");
        out.println("<p> Count on Stock: " + afterUpdate.getCountOnStock() +"</p>");
        out.close();
    }
}
