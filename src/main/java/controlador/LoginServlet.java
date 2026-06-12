
package controlador;

/**
 *
 * @author Lizbeth Huaman Ventura -1420446
 * Fecha: 10/06/2026
 */
import dao.UsuarioDao;
import modelo.Persona;
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

        // Accesos temporales mientras se estabiliza el login con BD.
        if ("admin@spa.com".equalsIgnoreCase(correo) && "123456".equals(contrasenia)) {
            Usuario usuarioAdmin = crearUsuarioTemporal(9999, "admin@spa.com", "Admin", "Spa", "ADMIN", "123456");
            HttpSession sesion = request.getSession();
            sesion.setAttribute("usuarioLogueado", usuarioAdmin);
            response.sendRedirect("home.jsp");
            return;
        }

        if ("cliente@spa.com".equalsIgnoreCase(correo) && "123456".equals(contrasenia)) {
            Usuario usuarioCliente = crearUsuarioTemporal(2001, "cliente@spa.com", "Cliente", "Demo", "CLIENTE", "123456");
            HttpSession sesion = request.getSession();
            sesion.setAttribute("usuarioLogueado", usuarioCliente);
            response.sendRedirect("index.jsp");
            return;
        }

        UsuarioDao dao = new UsuarioDao();
        Usuario usuario = dao.validarLogin(correo, contrasenia);

        if (usuario != null) {
            // Guardar usuario en sesión
             HttpSession sesion = request.getSession();
             sesion.setAttribute("usuarioLogueado", usuario);

            String rol = (usuario.getPersona() != null) ? usuario.getPersona().getRol() : null;
            if (rol != null && "ADMIN".equalsIgnoreCase(rol)) {
                response.sendRedirect("home.jsp");
            } else {
                response.sendRedirect("index.jsp");
            }
        } else {
            response.sendRedirect("login.jsp?error=1");
        }
    }

    private Usuario crearUsuarioTemporal(int idUsuario, String correo, String nombres, String apellidos, String rol, String contrasenia) {
        Persona persona = new Persona();
        persona.setNombres(nombres);
        persona.setApellidos(apellidos);
        persona.setCorreo(correo);
        persona.setRol(rol);

        Usuario usuarioTemporal = new Usuario();
        usuarioTemporal.setIdUsuario(idUsuario);
        usuarioTemporal.setCorreo(correo);
        usuarioTemporal.setContrasenia(contrasenia);
        usuarioTemporal.setPersona(persona);

        return usuarioTemporal;
    }
}