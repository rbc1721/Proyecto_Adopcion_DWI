package controlador;

import dao.MascotaDao;
import modelo.Mascota;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet("/MascotaServlet")
@MultipartConfig
public class MascotaServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Mascota mascota = new Mascota();
        mascota.setNombre(request.getParameter("nombre"));
        mascota.setEspecie(request.getParameter("especie"));
        mascota.setRaza(request.getParameter("raza"));
        mascota.setColor(request.getParameter("color"));
        mascota.setEdad(Integer.parseInt(request.getParameter("edad")));
        mascota.setTamanio(request.getParameter("tamanio"));
        mascota.setDescripcion(request.getParameter("descripcion"));

        // Guardar imagen (solo nombre del archivo)
        Part foto = request.getPart("foto");
        String nombreArchivo = foto != null ? foto.getSubmittedFileName() : null;
        mascota.setFoto(nombreArchivo);

        // Registrar mascota en BD
        Integer idGenerado = MascotaDao.registrar(mascota);

        if (idGenerado != null && idGenerado > 0) {
            response.sendRedirect("assets/buscar.jsp");
        } else {
            response.getWriter().println("Error al registrar la mascota.");
        }
    }
}
