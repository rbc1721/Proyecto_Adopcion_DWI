package controlador;

import dao.MascotaDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * @author Eduardo
 */
@WebServlet(name = "ImagenServlet", urlPatterns = {"/ImagenServlet"})
public class ImagenServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int id = Integer.parseInt(request.getParameter("id"));
            byte[] foto = MascotaDao.obtenerFoto(id);

            if (foto != null) {
                System.out.println("La foto no es null");
                response.setContentType("image/jpeg"); 
                response.setContentLength(foto.length);
                response.getOutputStream().write(foto);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

