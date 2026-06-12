package controlador;

import dao.CampaniaClienteDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.CampaniaCliente;

import java.io.IOException;
import java.util.List;

@WebServlet("/CampaniaClienteServlet")
public class CampaniaClienteServlet extends HttpServlet {

    private final CampaniaClienteDao campaniaClienteDao = new CampaniaClienteDao();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion == null) {
            accion = "asignar";
        }

        boolean ok = false;

        switch (accion) {
            case "asignar":
                CampaniaCliente campaniaCliente = new CampaniaCliente();
                campaniaCliente.setIdCampania(Integer.parseInt(request.getParameter("idCampania")));
                campaniaCliente.setIdCliente(Integer.parseInt(request.getParameter("idCliente")));
                campaniaCliente.setEstado(request.getParameter("estado"));
                ok = campaniaClienteDao.asignarCliente(campaniaCliente);
                break;
            case "estado":
                ok = campaniaClienteDao.actualizarEstado(
                        Integer.parseInt(request.getParameter("idCampaniaCliente")),
                        request.getParameter("estado")
                );
                break;
            case "eliminar":
                ok = campaniaClienteDao.eliminar(Integer.parseInt(request.getParameter("idCampaniaCliente")));
                break;
            default:
                response.sendRedirect("home.jsp?msg=accion_invalida_campania_cliente");
                return;
        }

        response.sendRedirect("home.jsp?msg=" + (ok ? "ok_campania_cliente" : "error_campania_cliente"));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idCampania = Integer.parseInt(request.getParameter("idCampania"));
        List<CampaniaCliente> asignaciones = campaniaClienteDao.listarPorCampania(idCampania);
        request.setAttribute("asignaciones", asignaciones);
        request.getRequestDispatcher("home.jsp").forward(request, response);
    }
}
