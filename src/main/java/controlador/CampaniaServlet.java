package controlador;

import dao.CampaniaDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Campania;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@WebServlet("/CampaniaServlet")
public class CampaniaServlet extends HttpServlet {

    private final CampaniaDao campaniaDao = new CampaniaDao();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion == null) {
            accion = "crear";
        }

        boolean ok = false;

        switch (accion) {
            case "crear":
                ok = campaniaDao.registrar(buildCampania(request));
                break;
            case "actualizar":
                Campania campania = buildCampania(request);
                campania.setIdCampania(Integer.parseInt(request.getParameter("idCampania")));
                ok = campaniaDao.actualizar(campania);
                break;
            case "eliminar":
                ok = campaniaDao.eliminar(Integer.parseInt(request.getParameter("idCampania")));
                break;
            default:
                response.sendRedirect("home.jsp?msg=accion_invalida_campania");
                return;
        }

        response.sendRedirect("home.jsp?msg=" + (ok ? "ok_campania" : "error_campania"));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Campania> campanias = campaniaDao.listar();
        request.setAttribute("campanias", campanias);
        request.getRequestDispatcher("home.jsp").forward(request, response);
    }

    private Campania buildCampania(HttpServletRequest request) {
        Campania campania = new Campania();
        campania.setNombre(request.getParameter("nombre"));
        campania.setDescripcion(request.getParameter("descripcion"));

        String fechaInicio = request.getParameter("fechaInicio");
        if (fechaInicio != null && !fechaInicio.isBlank()) {
            campania.setFechaInicio(Date.valueOf(fechaInicio));
        }

        String fechaFin = request.getParameter("fechaFin");
        if (fechaFin != null && !fechaFin.isBlank()) {
            campania.setFechaFin(Date.valueOf(fechaFin));
        }

        String fechaProgramada = request.getParameter("fechaProgramada");
        if (fechaProgramada != null && !fechaProgramada.isBlank()) {
            campania.setFechaProgramada(parseTimestamp(fechaProgramada));
        }

        campania.setTipoNotificacion(request.getParameter("tipoNotificacion"));
        campania.setEstado(request.getParameter("estado"));

        String idBono = request.getParameter("idBono");
        if (idBono != null && !idBono.isBlank()) {
            campania.setIdBono(Integer.parseInt(idBono));
        }

        String idPlantilla = request.getParameter("idPlantilla");
        if (idPlantilla != null && !idPlantilla.isBlank()) {
            campania.setIdPlantilla(Integer.parseInt(idPlantilla));
        }

        return campania;
    }

    private Timestamp parseTimestamp(String valor) {
        String normalizado = valor.trim().replace("T", " ");
        if (normalizado.length() == 16) {
            normalizado = normalizado + ":00";
        }
        return Timestamp.valueOf(normalizado);
    }
}
