
package dao;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import modelo.Reportes;

/**
 *
 * @author Eduardo
 * Clase DAO para reportes
 */
public class ReportesDao {    
    
    public static boolean registrarReporte(Reportes reporte){
        String sql = "INSERT INTO reportes(String tipo, LocalDate fecha, String direccion, String estado, String idUduario, int idMascota)";
        try {
            Connection conexion = Conexion.getInstancia().getConexion();
            PreparedStatement ps = conexion.prepareStatement(sql);
            
            ps.setString(1, reporte.getTipo());
            ps.setDate(2, java.sql.Date.valueOf(reporte.getFecha()));
            ps.setString(3, reporte.getDireccion());
            ps.setString(4,reporte.getEstado());
            ps.setString(5, reporte.getIdUduario());
            if(reporte.getIdMascota() != null){
                ps.setInt(6, reporte.getIdMascota());
            }else{
                ps.setNull(6, java.sql.Types.INTEGER);
            }
            
            return ps.executeUpdate()>0;
            
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
        return false;
    }
    
    public static List<Reportes> obtenerReportes(){
        List<Reportes> reportes = new ArrayList<>();
        String sql = "SELECT * FROM reportes WHERE idReporte = ?";
        
        try {
            Connection conexion = Conexion.getInstancia().getConexion();
            PreparedStatement ps = conexion.prepareCall(sql);
            ResultSet datos = ps.executeQuery();
            
            while(datos.next()){
                Reportes reporte = new Reportes();
                
                reporte.setCodigo(datos.getInt("idReporte"));
                reporte.setTipo(datos.getString("tipo"));
                reporte.setFecha(datos.getDate("fecha").toLocalDate());
                reporte.setDireccion(datos.getString("direccion"));
                reporte.setEstado(datos.getString("estado"));
                reporte.setIdUduario(datos.getString("idUsuario"));
                reporte.setIdMascota(datos.getInt("idMascota"));
                
                reportes.add(reporte);
            }            
        } catch (Exception e) {
            e.printStackTrace();
        }                
        return reportes;
    }
    
    public static boolean actualizarReportes(Reportes reporte){
        String sql = "UPDATE reportes SET estado = ? WHERE idReporte = ? ";
        
        try {
            Connection conexion = Conexion.getInstancia().getConexion();
            PreparedStatement ps = conexion.prepareStatement(sql);
            
            ps.setString(4, reporte.getEstado());
            ps.setInt(6, reporte.getIdMascota());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    
    
    
}
