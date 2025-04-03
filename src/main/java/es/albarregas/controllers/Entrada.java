package es.albarregas.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
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
@WebServlet(name = "Entrada", urlPatterns = {"/Entrada"})
public class Entrada extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Controlador</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Controlador at " + request.getContextPath() + "</h1>");
            out.println("<h1>Hola Mundo</h1>");
            out.println("<br>");
            out.println("<p><a href='.'>Inicio</a></p>");
            out.println("</body>");
            out.println("</html>");
        }
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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use the following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Controlador</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Método " + request.getMethod() + "</h1>");
            out.println("<h1>Bienvenido al Servlet</h1>");
            out.println("<br>");

            String opcion = request.getParameter("boton");

            switch (opcion) {
                case "Enumeration":
                    // Método usando getParameterNames
                    Enumeration<String> parametros = request.getParameterNames();
                    while (parametros.hasMoreElements()) {
                        String nombre = parametros.nextElement();
                        if (!nombre.equals("enviar")) {
                            String[] valores = request.getParameterValues(nombre);

                            out.println("<p>Los valores de " + nombre + " son: </p>");
                            out.println("<ul>");
                            for (String valor : valores) {
                                out.println("<li>" + valor + "</li>");
                            }
                            out.println("</ul>");
                        }
                    }
                    break;

                case "Mapa":
                    // Método usando getParameterMap
                    Map<String, String[]> parameterMap = request.getParameterMap();
                    for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
                        String paramNombre = entry.getKey();
                        String[] valores = entry.getValue();

                        if (!paramNombre.equals("map")) {
                            out.println("<p>" + paramNombre + ":</p>");
                            out.println("<ul>");
                            for (String valor : valores) {
                                out.println("<li>" + valor + "</li>");
                            }
                            out.println("</ul>");
                        }
                    }
                    break;
            }

            out.println("<br>");
            out.println("<p><a href='.'>Inicio</a></p>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
