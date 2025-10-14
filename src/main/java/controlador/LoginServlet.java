
package controlador;

/**
 *
 * @author huamanls
 */
import dao.UsuarioDao;
import modelo.Usuario;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String correo = request.getParameter("email"); // O nombres, según cómo mapeaste
        String contrasenia = request.getParameter("password");

        UsuarioDao dao = new UsuarioDao();
        Usuario usuario = dao.validarLogin(correo, contrasenia);

        if (usuario != null) {
            // Guardar usuario en sesión
            HttpSession sesion = request.getSession();
            sesion.setAttribute("usuarioLogueado", usuario);

            response.sendRedirect("index.jsp");
        } else {
            response.sendRedirect("login.jsp?error=1");
        }
    }
}