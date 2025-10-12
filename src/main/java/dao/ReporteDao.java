package dao;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.Reporte;

/**
 *
 * @author Eduardo Clase DAO para reportes
 */
public class ReporteDao {

    public static int registrar(Reporte reporte) {
        int codigo = -1;
        String sql = "INSERT INTO reportes(tipo, fecha, direccion, estado, idUsuario, idMascota) VALUES(?,?,?,?,?,?)";
        try (Connection conexion = Conexion.getInstancia().getConexion(); PreparedStatement ps = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {

            ps.setString(1, reporte.getTipo());
            ps.setDate(2, java.sql.Date.valueOf(reporte.getFecha()));
            ps.setString(3, reporte.getDireccion());
            ps.setString(4, reporte.getEstado());
            ps.setString(5, reporte.getIdUduario());
            if (reporte.getIdMascota() != null) {
                ps.setInt(6, reporte.getIdMascota());
            } else {
                ps.setNull(6, java.sql.Types.INTEGER);
            }

            if (ps.executeUpdate() > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    codigo = rs.getInt(1);
                }
            }
            return codigo;

        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
        return codigo;
    }

    public static List<Reporte> obtener(String tipo, String estado) {
        List<Reporte> reportes = new ArrayList<>();
        String sql = "SELECT * FROM reportes WHERE tipo = ? AND estado = ?";

        try (Connection conexion = Conexion.getInstancia().getConexion(); PreparedStatement ps = conexion.prepareCall(sql);) {

            ps.setString(1, tipo);
            ps.setString(2, estado);
            ResultSet datos = ps.executeQuery();

            while (datos.next()) {
                Reporte reporte = new Reporte();

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

    public static boolean actualizar(Reporte reporte) {
        String sql = "UPDATE reportes SET estado = ? WHERE idReporte = ? ";

        try (Connection conexion = Conexion.getInstancia().getConexion(); PreparedStatement ps = conexion.prepareStatement(sql);) {

            ps.setString(5, reporte.getEstado());
            ps.setInt(7, reporte.getIdMascota());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
