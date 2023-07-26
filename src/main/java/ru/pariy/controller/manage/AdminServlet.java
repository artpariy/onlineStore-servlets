package ru.pariy.controller.manage;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ru.pariy.entity.AuthUser;
import ru.pariy.entity.Role;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        HttpSession session = request.getSession(false);
        PrintWriter out = response.getWriter();
        if (session == null) {
            getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
        } else {
            if (session.getAttribute("auth") == null) {
                getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
            } else {
                AuthUser authUser = (AuthUser) session.getAttribute("auth");
                if (authUser.getRole().equals(Role.ADMIN.getRole())) {
                    request.setAttribute("login", authUser.getEmail());
                    getServletContext().getRequestDispatcher("/view/admin/manage.jsp").forward(request, response);
                } else {
                    out.println("<p> У вас нет прав для перехода на эту страницу </p>");
                    request.getRequestDispatcher("/index.jsp").include(request,response);
                }
            }
        }
    }
}
