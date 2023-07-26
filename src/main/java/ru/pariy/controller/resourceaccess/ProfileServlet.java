package ru.pariy.controller.resourceaccess;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import ru.pariy.config.Constant;
import ru.pariy.entity.AuthUser;

import java.io.IOException;
import java.io.PrintWriter;


/**
 * @brief Класс, отвечающий за доступ к профилю пользователя
 */
@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        // Session
        HttpSession session = req.getSession(false);
        if (session != null) {
            AuthUser authUser = (AuthUser) session.getAttribute(Constant.AUTH_SESSION_ATR);
            req.setAttribute(Constant.EMAIL_SESSION_ATR, authUser.getEmail());
            getServletContext().getRequestDispatcher("/view/profile.jsp").forward(req, resp);
        }
        else{
            out.print("Please login first");
            req.getRequestDispatcher("/authorization").include(req, resp);
        }
        out.close();
    }
}
