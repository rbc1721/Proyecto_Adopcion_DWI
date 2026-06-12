package controlador;

import dao.NotificacionDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Notificacion;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

@WebServlet("/NotificacionServlet")
public class NotificacionServlet extends HttpServlet {

    private final NotificacionDao notificacionDao = new NotificacionDao();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion == null) {
            accion = "crear";
        }

        boolean ok = false;

        switch (accion) {
            case "crear":
                Notificacion notificacion = new Notificacion();
                notificacion.setMensaje(request.getParameter("mensaje"));
                notificacion.setTipoEnvio(request.getParameter("tipoEnvio"));
                notificacion.setEstado(request.getParameter("estado"));
                notificacion.setIdCampania(Integer.parseInt(request.getParameter("idCampania")));
                notificacion.setIdCliente(Integer.parseInt(request.getParameter("idCliente")));

                String fechaEnvio = request.getParameter("fechaEnvio");
                if (fechaEnvio != null && !fechaEnvio.isBlank()) {
                    notificacion.setFechaEnvio(parseTimestamp(fechaEnvio));
                } else {
                    notificacion.setFechaEnvio(new Timestamp(System.currentTimeMillis()));
                }

                ok = notificacionDao.registrar(notificacion);
                break;
            case "estado":
                ok = notificacionDao.actualizarEstado(
                        Integer.parseInt(request.getParameter("idNotificacion")),
                        request.getParameter("estado")
                );
                break;
            default:
                response.sendRedirect("home.jsp?msg=accion_invalida_notificacion");
                return;
        }

        response.sendRedirect("home.jsp?msg=" + (ok ? "ok_notificacion" : "error_notificacion"));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idCliente = Integer.parseInt(request.getParameter("idCliente"));
        List<Notificacion> notificaciones = notificacionDao.listarPorCliente(idCliente);
        request.setAttribute("notificaciones", notificaciones);
        request.getRequestDispatcher("home.jsp").forward(request, response);
    }

    private Timestamp parseTimestamp(String valor) {
        String normalizado = valor.trim().replace("T", " ");
        if (normalizado.length() == 16) {
            normalizado = normalizado + ":00";
        }
        return Timestamp.valueOf(normalizado);
    }
}
