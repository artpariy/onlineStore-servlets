package ru.pariy.controller.manage;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.pariy.model.product.Product;
import ru.pariy.model.repository.ProductDAO;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/admin/manage/gotten")
public class GetServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        int vendorCode = Integer.parseInt(req.getParameter("vendorCode"));
        ProductDAO productDAO = new ProductDAO();
        if (!productDAO.exist(vendorCode)) {
            out.println("<p> Товар не найден, проверьте правильность артикула товара </p>");
        } else {
                Product product = ProductDAO.readProduct(vendorCode);
            printData(resp, product);
        }
    }

    private void printData(HttpServletResponse response, Product product) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<h3> Данные полученного товара </h3> ");
        out.println("<p> Type ID: " + product.getTypeId() + "</p>");
        out.println("<p> Brand ID: " + product.getBrandId() + "</p>");
        out.println("<p> Model: " + product.getModel()+ "</p>");
        out.println("<p> Release: " + product.getRelease() + "</p>");
        out.println("<p> Price: " + product.getPrice() + "</p>");
        out.println("<p> Color ID: " + product.getColorId() + "</p>");
        out.println("<p> Count on stock: " + product.getCountOnStock() + "</p>");
        out.close();
    }
}
