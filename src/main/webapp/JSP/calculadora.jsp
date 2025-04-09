<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Calculadora</title>
</head>
<body>
    <%
        String mensaje = "";
        try {
            int operando1 = Integer.parseInt(request.getParameter("operacion1"));
            int operando2 = Integer.parseInt(request.getParameter("operacion2"));
            String operacion = request.getParameter("operacion");

            switch (operacion) {
                case "sumar":
                    mensaje = "La suma " + operando1 + " + " + operando2 + " = " + (operando1 + operando2);
                    break;
                case "restar":
                    mensaje = "La resta " + operando1 + " - " + operando2 + " = " + (operando1 - operando2);
                    break;
                case "multiplicar":
                    mensaje = "La multiplicación " + operando1 + " * " + operando2 + " = " + (operando1 * operando2);
                    break;
                case "dividir":
                    if (operando2 == 0) {
                        mensaje = "No se puede dividir por 0";
                    } else {
                        double resultado = (double) operando1 / operando2;
                        mensaje = "La división " + operando1 + " / " + operando2 + " = " + resultado;
                    }
                    break;
                default:
                    mensaje = "Operación no reconocida";
            }

        } catch (NumberFormatException e) {
            mensaje = "Alguno de los operandos no es válido";
        }
    %>

    <h1>Calculadora</h1>
    <h2><%= mensaje %></h2>
    <p><a href="../HTML/calculadora.html">Volver al men&uacute;</a></p>
</body>
</html>