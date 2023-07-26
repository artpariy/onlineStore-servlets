package ru.pariy.controller.common;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public abstract class LoggerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            performGet(request, response);
        } catch (Exception exception) {
            if (exception instanceof UserException) {
                getLogger().error(exception);
                getServletContext().getRequestDispatcher("/view/error/sessionErr.jsp").forward(request, response);
                throw exception;
            } else {
                getLogger().error(exception);
                throw exception;
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            performPost(request, response);
        } catch (Exception exception) {
            if (exception instanceof UserException) {
                getLogger().error(exception);
                getServletContext().getRequestDispatcher("/view/error/sessionErr.jsp").forward(request, response);
                throw exception;
            } else {
                getLogger().error(exception);
                throw exception;
            }
        }
    }

    public abstract void performPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
    public abstract void performGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
    public abstract Logger getLogger();
}
