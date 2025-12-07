/*package com.frauddetection.controller;

import com.frauddetection.dao.UserDAO;
import com.frauddetection.model.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/user")
public class UserServlet extends HttpServlet {

    private UserDAO dao = new UserDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String action = req.getParameter("action");

        if ("login".equals(action)) {
            login(req, resp);
        } else if ("register".equals(action)) {
            register(req, resp);
        }
    }

    private void login(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {

        String email = req.getParameter("email");
        String password = req.getParameter("password");

        User user = dao.login(email, password);

        if (user != null) {
            req.getSession().setAttribute("user", user);
            resp.sendRedirect("dashboard");
        } else {
            req.setAttribute("error", "Invalid email or password");
            req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
        }
    }

    private void register(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {

        User u = new User();
        u.setName(req.getParameter("name"));
        u.setEmail(req.getParameter("email"));
        u.setPassword(req.getParameter("password"));

        boolean created = dao.register(u);
        if (created) {
            resp.sendRedirect("login.jsp");
        } else {
            req.setAttribute("error", "User already exists");
            req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
        }
    }
}
*/


package com.frauddetection.controller;

import com.frauddetection.model.User;
import com.frauddetection.service.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/user")
public class UserServlet extends HttpServlet {

    private final UserService userService = new UserService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String action = req.getParameter("action");

        if ("login".equalsIgnoreCase(action)) {
            handleLogin(req, resp);

        } else if ("register".equalsIgnoreCase(action)) {
            handleRegister(req, resp);

        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Unknown action");
        }
    }

    // =============================
    // LOGIN
    // =============================
    private void handleLogin(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {

        String email = req.getParameter("email");
        String password = req.getParameter("password");

        User user = userService.authenticate(email, password);

        if (user != null) {
            req.getSession().setAttribute("loggedUser", user);
            resp.sendRedirect("dashboard");       // goes to HomeServlet or dashboard.jsp
        } else {
            req.setAttribute("error", "Invalid email or password");
            req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
        }
    }

    // =============================
    // REGISTER
    // =============================
    private void handleRegister(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {

        User u = new User();
        u.setName(req.getParameter("name"));
        u.setEmail(req.getParameter("email"));
        u.setPassword(req.getParameter("password"));

        boolean created = userService.register(u);

        if (created) {
            resp.sendRedirect("login.jsp");
        } else {
            req.setAttribute("error", "User already exists");
            req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
        }
    }

    // =============================
    // GET request → Forward to login page
    // =============================
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
    }
}
