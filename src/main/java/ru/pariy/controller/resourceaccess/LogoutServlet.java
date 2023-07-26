package ru.pariy.controller.resourceaccess;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * @brief Класс, отвечающий за выход из учетной записи
 */
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        req.getRequestDispatcher("/").include(req, resp);
        HttpSession session = req.getSession(true);
        if (session != null) {
            session.invalidate();
        }

        out.print("<p>You are successfully logged out!</p>");
        out.close();

    }
}
