<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.net.URLDecoder" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/estilos.css">
        <title>Cookies</title>
    </head>
    <body>
        <div class="contenedorCentro">
            <div class="formularioCookiejsp">
                <h1>Control de Cookies</h1>


                <form action="controlCookie.jsp" method="post">
                    <table>
                        <tr>
                            <th>Nombre de la cookie</th>
                            <th>Valor de la Cookie</th>
                        </tr>
                        <tr>
                            <td><input type="text" name="nombreCookie"/></td>
                            <td><input type="text" name="valorCookie"/></td>
                        </tr>
                    </table>
                    <div >
                        <input type="submit" name="accion" value="Crear"/>
                        <input type="submit" name="accion" value="Mostrar"/>
                        <input type="submit" name="accion" value="Modificar"/>
                        <input type="submit" name="accion" value="Eliminar"/>
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>