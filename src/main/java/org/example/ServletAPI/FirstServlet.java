package org.example.ServletAPI;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/// Описание url сервлета (http://localhost:8081/first)
@WebServlet("/first")
public class FirstServlet extends HttpServlet {
    ///Работает только 1 раз при создании сервлета
    @Override
    public void init() throws ServletException {
        System.out.println("FirstServlet init");
    }

    /// Используется для получения данных или отгрузки страницы
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Пробрасываем ошибку
        //resp.sendError(HttpServletResponse.SC_NOT_FOUND);

        //req.getRequestDispatcher("/first.jsp").forward(req, resp);
        // Установили синтаксис с которым работаем

        resp.setContentType("text/html");
        var write = resp.getWriter();
        //Задали заголовок ответа
        resp.setHeader("Hello", "World");
        write.println("<h1>Hello World NIKITA!</h1>");

        //получаем заголовок запроса
        var header = req.getHeader("user-agent");
        var headers = req.getHeaderNames();
        while (headers.hasMoreElements()) {
            String headerName = headers.nextElement();
            write.println("<h3>" + headerName + ":" + req.getHeader(headerName) + "</h3>");
        }

        write.println("<h1>User-Agent: " + header + "</h1>");
    }
    /// Используется для добавления данных в бд
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        var write = resp.getWriter();
        //получаем значения параметра передаваемого в запросе
        write.println("<h1>" + req.getParameter("login") + "</h1>");
        write.println("<h1>" + req.getParameter("pwd") + "</h1>");

        write.println("<form action=\"/second\" method=\"post\">");
        write.println("<p>Курс:</p> <input type=\"text\" name=\"curs\" />");
        write.println("<p>Успеваемость:</p> <input type=\"text\" name=\"ucp\" />");
        write.println("<input type=\"submit\" value=\"Отправить\" />");
        write.println("</form></body></html>");
    }
    /// Вызывается при закрытии сервлета
    @Override
    public void destroy() {
        System.out.println("FirstServlet destroy");
    }
}
