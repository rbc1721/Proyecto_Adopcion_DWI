
package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;

/**
 *
 * @author Eduardo
 */
public class ReporteServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
        String nombre = request.getParameter("nombre");
        String especie = request.getParameter("especie");
        String raza = request.getParameter("raza");
        String color = request.getParameter("color");
        //int edad = Integer.parseInt(request.getParameter("edad"));
        String edad = request.getParameter("edad");
        String tamanio = request.getParameter("tamanio");
        String descripcion = request.getParameter("descripcion");
        /*
        Part archivo = request.getPart("foto");
        String nombreArchivo = archivo.getSubmittedFileName();
        
        if(archivo.getSize()>50*1024){
            response.getWriter().println("el archivo excede los 50 kb permitidos");
        }
        
        String ruta = getServletContext().getRealPath("") + "fotos";
        File directorio = new File(ruta);
        
        if(!directorio.exists()) directorio.mkdir();
        
        archivo.write(ruta+File.separator+nombreArchivo);
        response.getWriter().println("Se ha registrado correctamente");
        */
        System.out.println("La mascota se llama: " + nombre);
    }

}
