<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Procesaror Divisas</title>
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/estilos.css">
    </head>
    <body class="resultadoDivisas">
        <%!
            Map<String, Map<String, Double>> tasasCambio = new HashMap<>();

            double convertirDivisa(double cantidad, String divisaOrigen, String divisaDestino) {
                if (divisaOrigen.equals(divisaDestino)) {
                    return cantidad;
                }
                double tasaOrigen = tasasCambio.get("euros").get(divisaOrigen);
                double tasaDestino = tasasCambio.get("euros").get(divisaDestino);
                return cantidad * (tasaDestino / tasaOrigen);
            }

            {
                tasasCambio.put("euros", new HashMap<>());
                tasasCambio.get("euros").put("dolares", 1.18);
                tasasCambio.get("euros").put("yenes", 142.68);
                tasasCambio.get("euros").put("libras", 0.83);
                tasasCambio.get("euros").put("euros", 1.0);

                tasasCambio.put("dolares", new HashMap<>());
                tasasCambio.get("dolares").put("euros", 0.85);
                tasasCambio.get("dolares").put("yenes", 120.92);
                tasasCambio.get("dolares").put("libras", 0.73);

                tasasCambio.put("libras", new HashMap<>());
                tasasCambio.get("libras").put("euros", 1.16);
                tasasCambio.get("libras").put("yenes", 193.85);
                tasasCambio.get("libras").put("dolares", 1.22);

                tasasCambio.put("yenes", new HashMap<>());
                tasasCambio.get("yenes").put("euros", 0.00613541);
                tasasCambio.get("yenes").put("libras", 0.00516);
                tasasCambio.get("yenes").put("dolares", 0.00667);
            }
        %>

        <%
            String resultado = "";
            if (request.getParameter("cantidad") != null) {
                try {
                    double cantidad = Double.parseDouble(request.getParameter("cantidad"));
                    String divisaOrigen = request.getParameter("divisaOrigen");
                    String divisaDestino = request.getParameter("divisaDestino");
                    double resultadoConversion = convertirDivisa(cantidad, divisaOrigen, divisaDestino);
                    resultado = String.format("%.2f %s = %.2f %s", cantidad, divisaOrigen, resultadoConversion, divisaDestino);
                } catch (NumberFormatException e) {
                    resultado = "Error: Por favor, introduce una cantidad válida.";
                } catch (Exception e) {
                    resultado = "Error en la conversión: " + e.getMessage();
                }
            }
        %>

        <% if (!resultado.isEmpty()) {%>
        <h2>Intercambio de Divisas</h2>
        <p><%= resultado%></p>
        <% }%>

        <div class="enlacesDivisas">
            <p><a href="<%= request.getContextPath()%>">Menú</a></p>
            <p><a href="javascript:history.back()">Volver atrás</a></p>
        </div>

    </body>
</html>