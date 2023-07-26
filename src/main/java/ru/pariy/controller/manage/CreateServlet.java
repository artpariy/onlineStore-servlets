package ru.pariy.controller.manage;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.pariy.controller.common.LoggerServlet;
import ru.pariy.model.product.*;
import ru.pariy.model.repository.ProductDAO;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/admin/manage/created")
public class CreateServlet extends LoggerServlet {
    private final Logger logger = LogManager.getLogger(CreateServlet.class);

    @Override
    public void performPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        if (getProductData(request) == null) {
            out.println("<p> Не выбран тип продукта </p>");
            request.getRequestDispatcher("/view/admin/manage/create").include(request, response);
        } else {
            Product product = getProductData(request);
            ProductDAO.writeProduct(product);
            if (ProductDAO.exist(product.getVendorCode())) {
                out.println("<h3> Товар успешно добавлен в базу данных </h3>");
            }
        }
        out.close();
    }

    @Override
    public void performGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    public Logger getLogger() {
        return logger;
    }

    private Product getProductData(HttpServletRequest request) {
        String typeName = request.getParameter("productType");
        String brandName = request.getParameter("brand");
        String colorName = request.getParameter("color");


        String model = request.getParameter("model");
        String release = request.getParameter("release");
        int price = Integer.parseInt(request.getParameter("price"));
        int vendorCode = Integer.parseInt(request.getParameter("vendorCode"));
        int count = Integer.parseInt(request.getParameter("count"));

        return new Product(0, TypeDAO.getTypeId(typeName), BrandDAO.getBrandId(brandName), model,
                release, price, ColorDAO.getColorId(colorName), vendorCode, count);
    }

}
