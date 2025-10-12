package controlador;

import apoyo.MostrarDatos;
import dao.MascotaDao;
import dao.ReporteDao;
import serviciosExternos.GestionImagenes;
import java.io.IOException;
import jakarta.servlet.*;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.time.LocalDate;
import java.util.List;
import modelo.Mascota;
import modelo.MascotaEncontrada;
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
        // processRequest(request, response); 
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        try {
            List<Reporte> lista = ReporteDao.obtener("Encontrado", "pendiente");
            List<MascotaEncontrada> listaReportes = MostrarDatos.MostrarMascotaEncontradas(lista);

            if (listaReportes == null || listaReportes.isEmpty()) {
                request.setAttribute("mensaje", "No se encontraron reportes disponibles");
                return;
            }
            request.setAttribute("listaReportes", listaReportes);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mensaje", "Error al cargar los reportes de mascota encontradas" + e.getMessage());
        }
        RequestDispatcher rd = request.getRequestDispatcher("/buscar.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

        try {
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
            if (archivo == null || archivo.getSize() == 0) {
                response.sendRedirect("buscar.jsp?msg=La+foto+no+se+cargo+correctamente");
                return;
            }

            if (archivo.getSize() > 50 * 1024) {
                response.sendRedirect("buscar.jsp?msg=La+foto+excede+los+50+kb");
                return;
            }

            String url = GestionImagenes.enviarFoto(archivo);
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
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("buscar.jsp?msg=Ocurrio+un+error+inesperado");
        }
    }
}
