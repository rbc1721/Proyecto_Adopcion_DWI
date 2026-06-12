
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
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@WebServlet("/RegistrarUsuarioServlet")
public class RegistrarUsuarioServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Obtener datos del formulario
        String nombres = request.getParameter("nombres");
        String apellidos = request.getParameter("apellidos");
        String direccion = request.getParameter("direccion");
        String correo = request.getParameter("correo");
        String contrasenia = request.getParameter("contrasenia");
        String rol = "CLIENTE";

        UsuarioDao dao = new UsuarioDao();
        if (dao.existeCorreo(correo)) {
            String correoCodificado = URLEncoder.encode(correo, StandardCharsets.UTF_8);
            response.sendRedirect("registro.jsp?msg=correo_existe&correo=" + correoCodificado);
            return;
        }

        // Crear objetos
        Persona persona = new Persona();
        persona.setNombres(nombres);
        persona.setApellidos(apellidos);
        persona.setDireccion(direccion);
        persona.setCorreo(correo);
        persona.setRol(rol);

        Usuario usuario = new Usuario();
        usuario.setContrasenia(contrasenia);
        usuario.setPersona(persona);

        // Insertar en BD
        boolean exito = dao.registrarUsuario(usuario);

        if (exito) {
            response.sendRedirect("registro.jsp?msg=registro_ok");
        } else {
            response.sendRedirect("registro.jsp?msg=error");
        }
    }
}
