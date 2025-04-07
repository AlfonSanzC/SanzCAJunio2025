package es.albarregas.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author alfon
 */
@WebServlet(name = "Registro", urlPatterns = {"/Registro"})
public class Registro extends HttpServlet {

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        Map<String, String> campos = new HashMap<>();
        Map<String, String> errores = new HashMap<>();

        // Rellena campos siempre, es decir precarga
        for (String param : new String[]{"Nombre", "Apellidos", "Fecha_nacimiento", "Usuario", "Password", "Genero"}) {
            String val = request.getParameter(param);
            if (val != null) {
                campos.put(param, val);
            }
        }

        String[] preferencias = request.getParameterValues("Preferencias");

        if (request.getParameter("mostrarErrores") != null) {
            for (String campo : new String[]{"Nombre", "Fecha_nacimiento", "Usuario", "Password", "Genero"}) {
                String val = request.getParameter(campo);
                if (val == null || val.trim().isEmpty()) {
                    errores.put(campo, campo.replace("_", " ") + " es obligatorio");
                }
            }
        }

        StringBuilder formulario = new StringBuilder();
        formulario.append("<!DOCTYPE html><html lang='es'><head><title>Formulario</title>")
                .append("<meta charset='UTF-8'><meta name='viewport' content='width=device-width, initial-scale=1.0'>")
                .append("<link rel='stylesheet' type='text/css' href='./css/estilos.css'/>")
                .append("</head><body><h1>Entrada de datos</h1>")
                .append("<form method='post' action='Registro'>")
                .append("<label><strong>Nombre: </strong></label>")
                .append("<input type='text' name='Nombre' value='").append(campos.getOrDefault("Nombre", "")).append("'/><br>")
                .append(errores.containsKey("Nombre") ? "<p style='color:red;'>" + errores.get("Nombre") + "</p>" : "")
                .append("<br><label><strong>Apellidos: </strong></label>")
                .append("<input type='text' name='Apellidos' value='").append(campos.getOrDefault("Apellidos", "")).append("'/><br><br>")
                .append("<label><strong>Fecha de nacimiento: </strong></label>")
                .append("<input type='date' name='Fecha_nacimiento' value='").append(campos.getOrDefault("Fecha_nacimiento", "")).append("'/><br>")
                .append(errores.containsKey("Fecha_nacimiento") ? "<p style='color:red;'>" + errores.get("Fecha_nacimiento") + "</p>" : "")
                .append("<br><label><strong>Usuario: </strong></label>")
                .append("<input type='text' name='Usuario' value='").append(campos.getOrDefault("Usuario", "")).append("'/><br>")
                .append(errores.containsKey("Usuario") ? "<p style='color:red;'>" + errores.get("Usuario") + "</p>" : "")
                .append("<br><label><strong>Contraseña: </strong></label>")
                .append("<input type='password' name='Password'/><br>")
                .append(errores.containsKey("Password") ? "<p style='color:red;'>" + errores.get("Password") + "</p>" : "")
                .append("<br><label><strong>Género: </strong></label>");

        String[] generos = {"Hombre", "Mujer", "Otro"};
        for (String genero : generos) {
            formulario.append("<input type='radio' name='Genero' value='").append(genero).append("'")
                    .append((campos.getOrDefault("Genero", "Hombre").equals(genero)) ? " checked" : "")
                    .append("> <label>").append(genero).append("</label>");
        }

        if (errores.containsKey("Genero")) {
            formulario.append("<p style='color:red;'>").append(errores.get("Genero")).append("</p>");
        }

        formulario.append("<br><br><label><strong>Preferencias:</strong></label>");
        String[] todasPreferencias = {"Deportes", "Lectura", "Cine", "Viajes"};
        for (String pref : todasPreferencias) {
            formulario.append("<input type='checkbox' name='Preferencias' value='").append(pref).append("'")
                    .append(preferencias != null && java.util.Arrays.asList(preferencias).contains(pref) ? " checked" : "")
                    .append("> <label>").append(pref).append("</label>");
        }

        formulario.append("<br><br><input type='submit' name='Enviar' value='Enviar'/>")
                .append("<input type='reset' name='Limpiar' value='Limpiar formulario'/>")
                .append("</form></body></html>");

        out.println(formulario.toString());
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Map<String, String> errores = new HashMap<>();
        for (String campo : new String[]{"Nombre", "Fecha_nacimiento", "Usuario", "Password", "Genero"}) {
            String val = request.getParameter(campo);
            if (val == null || val.trim().isEmpty()) {
                errores.put(campo, campo + " es obligatorio");
            }
        }

        //Reviso si hay errores, si los hay, reenvio al mismo servlet
        if (!errores.isEmpty()) {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();

            out.println("<!DOCTYPE html>");
            out.println("<html><head><title>Error en el registro</title></head><body>");
            out.println("<h1>Existen errores en el registro</h1>");
            out.println("<form method='get' action='Registro'>");

            // Reenvía los datos como campos ocultos
            for (String param : new String[]{"Nombre", "Apellidos", "Fecha_nacimiento", "Usuario", "Genero"}) {
                String val = request.getParameter(param);
                if (val != null) {
                    out.println("<input type='hidden' name='" + param + "' value='" + val + "'/>");
                }
            }

            String[] preferencias = request.getParameterValues("Preferencias");
            if (preferencias != null) {
                for (String pref : preferencias) {
                    out.println("<input type='hidden' name='Preferencias' value='" + pref + "'/>");
                }
            }

            out.println("<input type='hidden' name='mostrarErrores' value='true'/>");
            out.println("<input type='submit' value='Continuar'/>");
            out.println("</form></body></html>");
            return;
        }

        //Cuando todo está correcto, se muestra el resumen
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html><html><head><title>Resumen</title></head><body>");
        out.println("<h1>Datos introducidos</h1>");

        for (Map.Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
            String key = entry.getKey();
            if (!key.equals("Enviar")) {
                out.println("<p><strong>" + key.replace("_", " ") + ":</strong> ");
                for (String val : entry.getValue()) {
                    out.println(val + " ");
                }
                out.println("</p>");
            }
        }

        out.println("<form action='Registro' method='get'>");
        out.println("<input type='submit' value='Volver a inicio'/>");
        out.println("</form></body></html>");
    }

    @Override
    public String getServletInfo() {
        return "short description";
    }
}
