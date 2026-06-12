package controlador;

import dao.BonoDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Bono;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

@WebServlet("/BonoServlet")
public class BonoServlet extends HttpServlet {

    private final BonoDao bonoDao = new BonoDao();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion == null) {
            accion = "crear";
        }

        boolean ok = false;

        switch (accion) {
            case "crear":
                ok = bonoDao.registrar(buildBono(request));
                break;
            case "actualizar":
                Bono bono = buildBono(request);
                bono.setIdBono(Integer.parseInt(request.getParameter("idBono")));
                ok = bonoDao.actualizar(bono);
                break;
            case "eliminar":
                ok = bonoDao.eliminar(Integer.parseInt(request.getParameter("idBono")));
                break;
            default:
                response.sendRedirect("home.jsp?msg=accion_invalida_bono");
                return;
        }

        response.sendRedirect("home.jsp?msg=" + (ok ? "ok_bono" : "error_bono"));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Bono> bonos = bonoDao.listar();
        request.setAttribute("bonos", bonos);
        request.getRequestDispatcher("home.jsp").forward(request, response);
    }

    private Bono buildBono(HttpServletRequest request) {
        Bono bono = new Bono();
        bono.setNombre(request.getParameter("nombre"));
        bono.setDescripcion(request.getParameter("descripcion"));

        String porcentaje = request.getParameter("porcentajeDescuento");
        if (porcentaje != null && !porcentaje.isBlank()) {
            bono.setPorcentajeDescuento(new BigDecimal(porcentaje));
        }

        String vigencia = request.getParameter("vigencia");
        if (vigencia != null && !vigencia.isBlank()) {
            bono.setVigencia(Date.valueOf(vigencia));
        }

        bono.setEstado(request.getParameter("estado"));
        return bono;
    }
}
