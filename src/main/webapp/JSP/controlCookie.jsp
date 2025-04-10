<%@page import="java.net.URLEncoder"%>
<%@page import="java.net.URLDecoder"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    StringBuilder mensaje = new StringBuilder();
    String accion = request.getParameter("accion");
    String nombreCookie = request.getParameter("nombreCookie");
    Cookie[] cookies = request.getCookies();

    if (accion != null && !accion.equals("Índice")) {
        if (nombreCookie != null && !nombreCookie.isEmpty()) {
            Cookie cookie = null;

                }
            }
            
            
            if ("Crear".equals(accion)) {
                if (cookie == null) {
                    
                
            } else if ("Modificar".equals(accion)) {
               
                } else {
                    mensaje.append("La cookie ").append(nombreCookie).append(" no existe");
                }
            } else if ("Eliminar".equals(accion)) {
                
            }
        } else if ("Mostrar".equals(accion)) {
            
        } else {
            mensaje.append("El nombre de la cookie es necesario para esta acción");
        }

    
    
    response.sendRedirect("menuCookie.jsp?mensaje=" );
%>
