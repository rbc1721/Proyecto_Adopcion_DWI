package controlador;

import dao.HistorialCampaniaDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.HistorialCampania;

import java.io.IOException;
import java.util.List;

@WebServlet("/HistorialCampaniaServlet")
public class HistorialCampaniaServlet extends HttpServlet {

    private final HistorialCampaniaDao historialCampaniaDao = new HistorialCampaniaDao();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HistorialCampania historial = new HistorialCampania();
        historial.setCantidadClientes(Integer.parseInt(request.getParameter("cantidadClientes")));
        historial.setEstadoEnvio(request.getParameter("estadoEnvio"));
        historial.setObservacion(request.getParameter("observacion"));
        historial.setIdCampania(Integer.parseInt(request.getParameter("idCampania")));

        boolean ok = historialCampaniaDao.registrar(historial);
        response.sendRedirect("home.jsp?msg=" + (ok ? "ok_historial" : "error_historial"));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idCampania = Integer.parseInt(request.getParameter("idCampania"));
        List<HistorialCampania> historial = historialCampaniaDao.listarPorCampania(idCampania);
        request.setAttribute("historialCampania", historial);
        request.getRequestDispatcher("home.jsp").forward(request, response);
    }
}
