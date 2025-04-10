<%@page import="java.net.URLDecoder"%>
<%@page import="java.net.URLEncoder"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    Cookie cookie = null;
    Boolean existe = false;

    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
        for (int i = 0; i < cookies.length; i++) {
            if (cookies[i].getName().equals("nombreConCookie")) {
                cookie = cookies[i];
                break;
            }
        }
    }
    
    if (cookie == null) {
        cookie = new Cookie("nombreConCookie", URLEncoder.encode("Alfonso Sanz Carmona", "UTF-8"));
    } else {
        existe = Boolean.TRUE;
    }

    cookie.setMaxAge(3600);
    response.addCookie(cookie);

    StringBuilder persona = new StringBuilder();

    if (existe) {
        persona.append("Hola ").append(URLDecoder.decode(cookie.getValue(), "UTF-8"));
    } else {
        persona.append("Todavía no tenemos datos tuyos");
    }

%>

<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Nombre Cookies</title>
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/estilos.css">
    </head>
    <body class="contenedorMinombre">
       <h1><%=persona.toString()%></h1>
        
        <form action="miNombre.jsp" method="post">
            <span class = "btnNombreCookie"><input type="submit" value="Recargar" name="recarga"></span>
            <span class = "btnNombreCookie"><input type="button" value="Volver" name="volver" onClick = "location.href='<%=request.getContextPath()%>'" ></span>
        </form>  
    </body>
</html>
