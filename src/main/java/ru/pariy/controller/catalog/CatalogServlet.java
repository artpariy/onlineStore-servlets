package ru.pariy.controller.catalog;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.pariy.controller.common.LoggerServlet;
import ru.pariy.model.product.Product;
import ru.pariy.model.product.Type;
import ru.pariy.model.product.TypeDAO;
import ru.pariy.model.repository.Catalog;

import java.io.IOException;
import java.util.List;

@WebServlet("/catalog")
public class CatalogServlet extends LoggerServlet {

    private final Logger catalogLogger = LogManager.getLogger(CatalogServlet.class);

    @Override
    public void performPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int typeId;
        String typeName = request.getParameter("productType");
        List<Product> productList = getDataFromProduct(TypeDAO.getTypeId(typeName));
        setDataToJsp(productList, request, response);
    }

    @Override
    public void performGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/view/catalog/productSelect.jsp").forward(request, response);
    }

    @Override
    public Logger getLogger() {
        return catalogLogger;
    }

    private List<Product> getDataFromProduct(int typeId) {
        return Catalog.readAllModelFromProduct(typeId);
    }

    private void setDataToJsp(List<Product> productList, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("products", productList);
        request.getRequestDispatcher("/view/catalog/catalog.jsp").forward(request, response);
    }
}
