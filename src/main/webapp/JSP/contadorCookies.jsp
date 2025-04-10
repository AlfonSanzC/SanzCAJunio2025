<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.net.URLDecoder" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Contador de Visitas</title>
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/estilos.css">
    </head>
    <body class="contenedorContcookies">
        <%
    String cookieName = "CONTADOR";
    Cookie[] cookies = request.getCookies();
    Cookie contadorCookie = null;
    int contador = 1;

    if (cookies != null) {
        for (Cookie cookie : cookies) {
            if (cookieName.equals(cookie.getName())) {
                contadorCookie = cookie;
                contador = Integer.parseInt(URLDecoder.decode(cookie.getValue(), "UTF-8"));
                break;
            }
        }
    }

    if (contadorCookie == null) {
        contadorCookie = new Cookie(cookieName, URLEncoder.encode(String.valueOf(contador), "UTF-8"));
    } else {
        contador++;
        contadorCookie.setValue(URLEncoder.encode(String.valueOf(contador), "UTF-8"));
    }

    contadorCookie.setMaxAge(3600); 
    response.addCookie(contadorCookie);
    %>

    <h2>Has visitado la página <%= contador %> <%= contador == 1 ? "vez" : "veces" %></h2>

    <form action="" method="get">
        <input type="submit" value="Recargar">
    </form>
    <br><br>
    <form action="" method="post">
        <input type="hidden" name="action" value="reset">
        <input type="submit" value="Eliminar">
        <span class = "botones"><input type="button" value="Volver" name="volver" onClick = "location.href='<%=request.getContextPath()%>'" ></span>
    </form>

    <%
    if ("POST".equalsIgnoreCase(request.getMethod()) && "reset".equals(request.getParameter("action"))) {
        contadorCookie.setMaxAge(0); 
        response.addCookie(contadorCookie);
        response.sendRedirect(request.getRequestURI());
    }
    %>
    </body>
</html>
