package org.example.ServletAPI;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;

@WebServlet("/second")
public class SecondServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");

        var paramName = req.getParameter("name");

        var write = resp.getWriter();
        write.println("<h3>" + paramName + "</h3>");


        var mapParam = req.getParameterMap();
        mapParam.entrySet().forEach(entry -> write
                .println("<h3>  name: " + entry.getKey() + " Value:" + Arrays.toString(entry.getValue()) + "</h3>"));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //так можно перенаправить этот вызов на другой сервлет
        //resp.sendRedirect("/first");
        resp.setContentType("text/html;charset=utf-8");
        var write = resp.getWriter();

        write.println(req.getParameter("curs"));
        write.println(req.getParameter("ucp"));
    }
}
