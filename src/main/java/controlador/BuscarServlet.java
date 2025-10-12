package controlador;

import dao.MascotaDao;
import dao.ReporteDao;
import externalServices.FileManagement;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.*;
import jakarta.servlet.annotation.MultipartConfig;
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
@MultipartConfig(
        maxFileSize = 1024 * 50, // 50 KB
        maxRequestSize = 1024 * 100, // 100 KB
        fileSizeThreshold = 1024 * 10 // 10 KB
)

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
        String direccion = request.getParameter("direccion");
        String idUsuario = "pepe";

        Part archivo = request.getPart("foto");
        if (archivo == null || archivo.getSize() < 0 || archivo.getSize() > 50 * 1024) {
            response.sendRedirect("buscar.jsp?msg=La+foto+excede+los+50+kb+o+no+se+cargo+correctamente");
            return;
        }

        String url = FileManagement.enviarFoto(archivo);
        if (url == null) {
            response.sendRedirect("buscar.jsp?msg=Error+al+subir+la+imagen");
            return;
        }

        /*
        //Guardar la imagen en el directorio temporal local
        String carpetaDestino = getServletContext().getRealPath("/data");
     
        System.out.println("La carpeta de destino es: " + carpetaDestino);
        
        File carpeta = new File(carpetaDestino);
        if (!carpeta.exists()) carpeta.mkdir();
        
        String nombreArchivo = System.currentTimeMillis() + "_" + archivo.getSubmittedFileName();       
        archivo.write(carpetaDestino + File.separator + nombreArchivo);

         */
        Mascota mascota = new Mascota(nombre, especie, raza, color, edad, tamanio, descripcion, url);
        int idMascota = MascotaDao.registrar(mascota);
        if (idMascota < 1) {
            response.sendRedirect("buscar.jsp?msg=Error+al+registrar+los+datos+de+la+mascota");
            return;
        }
        Reporte reporte = new Reporte("Busqueda", LocalDate.now(), direccion, "Pendiente", idUsuario, idMascota);
        if (ReporteDao.registrar(reporte) < 1) {
            response.sendRedirect("buscar.jsp?msg=Error+al+registrar+el+reporte");
            return;
        }
        response.sendRedirect("buscar.jsp?msg=Registro+exitoso");
    }
}
