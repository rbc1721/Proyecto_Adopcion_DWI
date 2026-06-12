package dao;

import config.Conexion;
import modelo.HistorialCampania;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class HistorialCampaniaDao {

    public boolean registrar(HistorialCampania historial) {
        String sql = "INSERT INTO HistorialCampania (cantidadClientes, estadoEnvio, observacion, idCampania) VALUES (?, ?, ?, ?)";

        try {
            Connection conn = Conexion.getInstancia().getConexion();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, historial.getCantidadClientes());
            ps.setString(2, historial.getEstadoEnvio());
            ps.setString(3, historial.getObservacion());
            ps.setInt(4, historial.getIdCampania());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public List<HistorialCampania> listarPorCampania(int idCampania) {
        List<HistorialCampania> lista = new ArrayList<>();
        String sql = "SELECT * FROM HistorialCampania WHERE idCampania = ? ORDER BY idHistorial DESC";

        try {
            Connection conn = Conexion.getInstancia().getConexion();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idCampania);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(mapHistorial(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    public List<HistorialCampania> listar() {
        List<HistorialCampania> lista = new ArrayList<>();
        String sql = "SELECT * FROM HistorialCampania ORDER BY idHistorial DESC";

        try {
            Connection conn = Conexion.getInstancia().getConexion();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(mapHistorial(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    private HistorialCampania mapHistorial(ResultSet rs) throws Exception {
        HistorialCampania historial = new HistorialCampania();
        historial.setIdHistorial(rs.getInt("idHistorial"));
        historial.setFechaProceso(rs.getTimestamp("fechaProceso"));
        historial.setCantidadClientes(rs.getInt("cantidadClientes"));
        historial.setEstadoEnvio(rs.getString("estadoEnvio"));
        historial.setObservacion(rs.getString("observacion"));
        historial.setIdCampania(rs.getInt("idCampania"));
        return historial;
    }
}
