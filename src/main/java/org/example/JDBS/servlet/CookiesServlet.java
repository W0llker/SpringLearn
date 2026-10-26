package org.example.JDBS.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.example.JDBS.dto.CustomersDto;

import java.io.IOException;
import java.util.Arrays;

@WebServlet("/cookie")
public class CookiesServlet extends HttpServlet {
    private static final String USER_ID = "userId";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //work cookies
        var cookies = req.getCookies();
        if (cookies == null ||
                Arrays.stream(cookies).filter(cookie -> USER_ID.equals(cookie.getName())).findFirst().isEmpty()) {
            var cks = new Cookie(USER_ID, "1");
            cks.setPath("/cookie");
            cks.setMaxAge(60 * 60);
            resp.addCookie(cks);
        }
        //work session
        HttpSession session = req.getSession();
        //add attribute
        session.setAttribute("user", new CustomersDto(1L, "пупупу"));

        //getAttribute
        var userAttribute = (CustomersDto) session.getAttribute("user");
        //Info session
        String id = session.getId();
        //create time
        long created =  session.getCreationTime();
        //последний доступ
        long accessed =  session.getLastAccessedTime();
        int timeout = session.getMaxInactiveInterval();
        //new session or no
        boolean isNew = session.isNew();

        //перенаправляем на другой ресурс
        resp.sendRedirect(req.getContextPath() + "/cookie");
    }
}
