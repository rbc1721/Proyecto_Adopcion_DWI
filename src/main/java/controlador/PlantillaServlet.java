package controlador;

import dao.PlantillaDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Plantilla;

import java.io.IOException;
import java.util.List;

@WebServlet("/PlantillaServlet")
public class PlantillaServlet extends HttpServlet {

    private final PlantillaDao plantillaDao = new PlantillaDao();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion == null) {
            accion = "crear";
        }

        boolean ok = false;

        switch (accion) {
            case "crear":
                ok = plantillaDao.registrar(buildPlantilla(request));
                break;
            case "actualizar":
                Plantilla plantilla = buildPlantilla(request);
                plantilla.setIdPlantilla(Integer.parseInt(request.getParameter("idPlantilla")));
                ok = plantillaDao.actualizar(plantilla);
                break;
            case "eliminar":
                ok = plantillaDao.eliminar(Integer.parseInt(request.getParameter("idPlantilla")));
                break;
            default:
                response.sendRedirect("home.jsp?msg=accion_invalida_plantilla");
                return;
        }

        response.sendRedirect("home.jsp?msg=" + (ok ? "ok_plantilla" : "error_plantilla"));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Plantilla> plantillas = plantillaDao.listar();
        request.setAttribute("plantillas", plantillas);
        request.getRequestDispatcher("home.jsp").forward(request, response);
    }

    private Plantilla buildPlantilla(HttpServletRequest request) {
        Plantilla plantilla = new Plantilla();
        plantilla.setNombre(request.getParameter("nombre"));
        plantilla.setAsunto(request.getParameter("asunto"));
        plantilla.setContenido(request.getParameter("contenido"));
        plantilla.setTipoCanal(request.getParameter("tipoCanal"));
        plantilla.setEstado(request.getParameter("estado"));
        return plantilla;
    }
}
