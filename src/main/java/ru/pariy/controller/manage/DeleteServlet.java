package ru.pariy.controller.manage;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.pariy.controller.common.LoggerServlet;
import ru.pariy.model.repository.ProductDAO;

import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/admin/manage/deleted")
public class DeleteServlet extends LoggerServlet {
    private final Logger deleteLogger = LogManager.getLogger(DeleteServlet.class);

    @Override
    public void performPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        int vendorCode = Integer.parseInt(req.getParameter("vendorCode"));
        int deleted = ProductDAO.deleteProductFromDb(vendorCode);
        if (deleted == 0) {
            out.println("<p> Товар не был удален </p>");
        } else {
            out.println("<p> Товар был успешно удален </p>");
        }
        out.close();
    }

    @Override
    public void performGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public Logger getLogger() {
        return deleteLogger;
    }
}
