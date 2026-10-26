package org.example.JDBS.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.JDBS.service.CustomerService;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet("/customers")
public class CustomersServlet extends HttpServlet {

    private final CustomerService customerService = CustomerService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        req.setAttribute("customers", customerService.findAll());
        resp.addCookie(new Cookie("user","3"));
        req.getRequestDispatcher("customers.jsp").forward(req,resp);
    }
}

