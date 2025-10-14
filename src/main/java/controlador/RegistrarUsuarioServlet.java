
package controlador;

/**
 *
 * @author huamanls
 */
import dao.UsuarioDao;
import modelo.Persona;
import modelo.Usuario;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/RegistrarUsuarioServlet")
public class RegistrarUsuarioServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Obtener datos del formulario
        String nombres = request.getParameter("nombres");
        String apellidos = request.getParameter("apellidos");
        String direccion = request.getParameter("direccion");
        String rol = request.getParameter("rol");
        String contrasenia = request.getParameter("contrasenia");

        // Crear objetos
        Persona persona = new Persona();
        persona.setNombres(nombres);
        persona.setApellidos(apellidos);
        persona.setDireccion(direccion);
        persona.setRol(rol);

        Usuario usuario = new Usuario();
        usuario.setContrasenia(contrasenia);
        usuario.setPersona(persona);

        // Insertar en BD
        UsuarioDao dao = new UsuarioDao();
        boolean exito = dao.registrarUsuario(usuario);

        if (exito) {
            response.sendRedirect("login.jsp?msg=registro_ok");
        } else {
            response.sendRedirect("registro.jsp?msg=error");
        }
    }
}
