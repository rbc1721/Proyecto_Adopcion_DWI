package dao;

import config.Conexion;
import modelo.Notificacion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class NotificacionDao {

    public boolean registrar(Notificacion notificacion) {
        String sql = "INSERT INTO Notificacion (mensaje, fechaEnvio, tipoEnvio, estado, idCampania, idCliente) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            Connection conn = Conexion.getInstancia().getConexion();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, notificacion.getMensaje());
            ps.setTimestamp(2, notificacion.getFechaEnvio());
            ps.setString(3, notificacion.getTipoEnvio());
            ps.setString(4, notificacion.getEstado());
            ps.setInt(5, notificacion.getIdCampania());
            ps.setInt(6, notificacion.getIdCliente());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public List<Notificacion> listarPorCliente(int idCliente) {
        List<Notificacion> lista = new ArrayList<>();
        String sql = "SELECT * FROM Notificacion WHERE idCliente = ? ORDER BY idNotificacion DESC";

        try {
            Connection conn = Conexion.getInstancia().getConexion();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idCliente);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(mapNotificacion(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    public List<Notificacion> listar() {
        List<Notificacion> lista = new ArrayList<>();
        String sql = "SELECT * FROM Notificacion ORDER BY idNotificacion DESC";

        try {
            Connection conn = Conexion.getInstancia().getConexion();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(mapNotificacion(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    public boolean actualizarEstado(int idNotificacion, String estado) {
        String sql = "UPDATE Notificacion SET estado = ? WHERE idNotificacion = ?";

        try {
            Connection conn = Conexion.getInstancia().getConexion();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, estado);
            ps.setInt(2, idNotificacion);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    private Notificacion mapNotificacion(ResultSet rs) throws Exception {
        Notificacion notificacion = new Notificacion();
        notificacion.setIdNotificacion(rs.getInt("idNotificacion"));
        notificacion.setMensaje(rs.getString("mensaje"));
        notificacion.setFechaEnvio(rs.getTimestamp("fechaEnvio"));
        notificacion.setTipoEnvio(rs.getString("tipoEnvio"));
        notificacion.setEstado(rs.getString("estado"));
        notificacion.setIdCampania(rs.getInt("idCampania"));
        notificacion.setIdCliente(rs.getInt("idCliente"));
        return notificacion;
    }
}
