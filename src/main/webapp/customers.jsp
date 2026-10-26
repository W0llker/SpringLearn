<%@ page import="org.example.JDBS.service.CustomerService" %>
<%@ page import="org.example.JDBS.dto.CustomersDto" %>
<%@ page isELIgnored="false" %>
<%--
  Created by IntelliJ IDEA.
  User: reklo
  Date: 23.10.2026
  Time: 16:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%! public void outWrite(String a) {
    System.out.println(a);
}
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Привет</h1>
<ul>
    <%
        String header = "Васька";
        CustomerService customerService = CustomerService.getInstance();
        CustomersDto customer = customerService.findById(2L);
        out.write(header);
    %>
    <h1>
        <%= customer.description() %>
    </h1>
</ul>
<p>${cookie.get("user")} </p>
<p>${header["host"]}</p>
<p>${requestScope.customers[1].description()}</p>
<p>${requestScope.customers.size()}</p>
</body>
</html>
