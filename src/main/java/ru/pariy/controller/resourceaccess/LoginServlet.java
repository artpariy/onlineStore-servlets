package ru.pariy.controller.resourceaccess;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.pariy.config.Constant;
import ru.pariy.controller.common.LoggerServlet;
import ru.pariy.entity.AuthUser;
import ru.pariy.model.auth.PasswordEncryptionService;
import ru.pariy.model.repository.UserDAO;
import ru.pariy.entity.User;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * @brief Класс, отвечающий за авторизацию пользователей
 */
@WebServlet("/login")
public class LoginServlet extends LoggerServlet {
    private final Logger loginLogger = LogManager.getLogger(LoginServlet.class);

    @Override
    public void performPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        User user;
        if (!UserDAO.exist(email)) {
            out.println("<p> Указан неверный email </p>");
            req.getRequestDispatcher("/view/login/login.jsp").include(req, resp);
        } else {

            HttpSession session = req.getSession(true);
            if (session != null) {
                session.removeAttribute(Constant.AUTH_SESSION_ATR);
            }
            user = UserDAO.readFromDb(email);
            assert user != null;

            if (PasswordEncryptionService.checkPassword(password, user)) {
                session = req.getSession();
                AuthUser authUser = new AuthUser(user.getEmail(), user.getRole());
                session.setAttribute(Constant.AUTH_SESSION_ATR, authUser);

                req.setAttribute(Constant.FIRST_NAME_SESSION_ATR, user.getFirstName());
                req.setAttribute(Constant.LAST_NAME_SESSION_ATR, user.getLastName());
                req.setAttribute(Constant.EMAIL_SESSION_ATR, user.getEmail());
                getServletContext().getRequestDispatcher("/view/login/successLogin.jsp").forward(req, resp);

            } else {
                out.println("<p> Указан неверный пароль </p>");
                req.getRequestDispatcher("/view/login/login.jsp").include(req, resp);
            }
        }
        out.close();
    }

    @Override
    public void performGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public Logger getLogger() {
        return loginLogger;
    }

}
