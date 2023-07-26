package ru.pariy.controller.resourceaccess;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.pariy.controller.common.LoggerServlet;
import ru.pariy.entity.Role;
import ru.pariy.model.auth.PasswordEncryptionService;
import ru.pariy.model.repository.UserDAO;
import ru.pariy.entity.User;

import java.io.IOException;
import java.io.PrintWriter;
/**
 * @brief Класс, отвечающий за регистрацию пользователей
 */
@WebServlet("/success-reg")
public class RegServlet extends LoggerServlet {
    private final Logger regLogger = LogManager.getLogger(RegServlet.class);

    @Override
    public void performPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String salt = PasswordEncryptionService.generateSalt();
        String password = PasswordEncryptionService.getHashPassword(request.getParameter("password"), salt);

        User user = new User(request.getParameter("email"), password, salt, request.getParameter("firstName"),
                request.getParameter("lastName"), Role.CLIENT.getRole());
        UserDAO.writeToDb(user);

        if (!UserDAO.exist(user.getEmail())) {
            out.println("<p> Произошла ошибка при регистрации, повторите попытку </p> ");
        } else {
            request.setAttribute("firstName", user.getFirstName());
            request.setAttribute("lastName", user.getLastName());
            request.setAttribute("email", user.getEmail());
            request.getRequestDispatcher("/view/reg/successRegistration.jsp").forward(request, response);
        }
        out.close();
    }

    @Override
    public void performGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public Logger getLogger() {
        return regLogger;
    }
}
