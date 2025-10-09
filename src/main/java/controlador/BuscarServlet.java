package controlador;

import dao.MascotaDao;
import dao.ReporteDao;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.time.LocalDate;
import modelo.Mascota;
import modelo.Reporte;

/**
 *
 * @author Eduardo
 */
public class BuscarServlet extends HttpServlet {

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
        int edad = Integer.parseInt(request.getParameter("edad"));
        String tamanio = request.getParameter("tamanio");
        String descripcion = request.getParameter("descripcion");

        String direccion = "";
        String idUsuario = "pepe";

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
        Mascota mascota = new Mascota(nombre, especie, raza, color, edad, tamanio, descripcion, color);
        int idMascota = MascotaDao.registrar(mascota);
        if (idMascota > 0) {
            Reporte reporte = new Reporte("Busqueda", LocalDate.now(), direccion, "Pendiente", idUsuario, idMascota);
            if (ReporteDao.registrar(reporte) > 0) {
                request.setAttribute("mensaje", "Registro exitoso");
            } else {
                request.setAttribute("mensaje", "Error al intentar registrar el reporte de busqueda");
            }
        } else {
            request.setAttribute("mensaje", "Error al intentar registrar los datos de la mascota");
        }
        RequestDispatcher rd = request.getRequestDispatcher("buscar.jsp");
        rd.forward(request, response);
    }
}
