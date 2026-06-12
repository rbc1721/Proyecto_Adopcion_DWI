package controlador;

import dao.ClienteDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Cliente;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet("/ClienteServlet")
public class ClienteServlet extends HttpServlet {

    private final ClienteDao clienteDao = new ClienteDao();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion == null) {
            accion = "crear";
        }

        boolean ok = false;

        switch (accion) {
            case "crear":
                ok = clienteDao.registrar(buildCliente(request));
                break;
            case "actualizar":
                Cliente cliente = buildCliente(request);
                cliente.setIdCliente(Integer.parseInt(request.getParameter("idCliente")));
                ok = clienteDao.actualizar(cliente);
                break;
            case "eliminar":
                ok = clienteDao.eliminar(Integer.parseInt(request.getParameter("idCliente")));
                break;
            default:
                response.sendRedirect("home.jsp?msg=accion_invalida_cliente");
                return;
        }

        response.sendRedirect("home.jsp?msg=" + (ok ? "ok_cliente" : "error_cliente"));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Cliente> clientes = clienteDao.listar();
        request.setAttribute("clientes", clientes);
        request.getRequestDispatcher("home.jsp").forward(request, response);
    }

    private Cliente buildCliente(HttpServletRequest request) {
        Cliente cliente = new Cliente();
        cliente.setDni(request.getParameter("dni"));
        cliente.setNombre(request.getParameter("nombre"));
        cliente.setApellido(request.getParameter("apellido"));
        cliente.setCorreo(request.getParameter("correo"));
        cliente.setTelefono(request.getParameter("telefono"));

        String fechaRegistro = request.getParameter("fechaRegistro");
        if (fechaRegistro != null && !fechaRegistro.isBlank()) {
            cliente.setFechaRegistro(Date.valueOf(fechaRegistro));
        }

        cliente.setSegmento(request.getParameter("segmento"));
        cliente.setEstado(request.getParameter("estado"));
        return cliente;
    }
}
