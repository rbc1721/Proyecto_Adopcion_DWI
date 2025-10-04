
package dao;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import modelo.Reportes;

/**
 *
 * @author Eduardo
 */
public class ReportesDao {    
    
    public static boolean registrarReporte(Reportes reporte){
        String sql = "INSERT INTO reportes(String tipo, LocalDate fecha, String direccion, String estado, String idUduario, int idMascota)";
        try {
            Connection conexion = Conexion.getInstancia().getConexion();
            PreparedStatement ps = conexion.prepareStatement(sql);
            
            ps.setString(1, reporte.getTipo());
            ps.setDate(2, java.sql.Date.valueOf(reporte.getFecha()));
        } catch (Exception e) {
        }
        return false;
    }
}
