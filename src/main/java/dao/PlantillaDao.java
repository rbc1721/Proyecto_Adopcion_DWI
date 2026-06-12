package dao;

import config.Conexion;
import modelo.Plantilla;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PlantillaDao {

    public boolean registrar(Plantilla plantilla) {
        String sql = "INSERT INTO Plantilla (nombre, asunto, contenido, tipoCanal, estado) VALUES (?, ?, ?, ?, ?)";

        try {
            Connection conn = Conexion.getInstancia().getConexion();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, plantilla.getNombre());
            ps.setString(2, plantilla.getAsunto());
            ps.setString(3, plantilla.getContenido());
            ps.setString(4, plantilla.getTipoCanal());
            ps.setString(5, plantilla.getEstado());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean actualizar(Plantilla plantilla) {
        String sql = "UPDATE Plantilla SET nombre = ?, asunto = ?, contenido = ?, tipoCanal = ?, estado = ? WHERE idPlantilla = ?";

        try {
            Connection conn = Conexion.getInstancia().getConexion();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, plantilla.getNombre());
            ps.setString(2, plantilla.getAsunto());
            ps.setString(3, plantilla.getContenido());
            ps.setString(4, plantilla.getTipoCanal());
            ps.setString(5, plantilla.getEstado());
            ps.setInt(6, plantilla.getIdPlantilla());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean eliminar(int idPlantilla) {
        String sql = "DELETE FROM Plantilla WHERE idPlantilla = ?";

        try {
            Connection conn = Conexion.getInstancia().getConexion();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idPlantilla);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public Plantilla obtenerPorId(int idPlantilla) {
        String sql = "SELECT * FROM Plantilla WHERE idPlantilla = ?";

        try {
            Connection conn = Conexion.getInstancia().getConexion();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idPlantilla);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapPlantilla(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Plantilla> listar() {
        List<Plantilla> lista = new ArrayList<>();
        String sql = "SELECT * FROM Plantilla ORDER BY idPlantilla DESC";

        try {
            Connection conn = Conexion.getInstancia().getConexion();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(mapPlantilla(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    private Plantilla mapPlantilla(ResultSet rs) throws Exception {
        Plantilla plantilla = new Plantilla();
        plantilla.setIdPlantilla(rs.getInt("idPlantilla"));
        plantilla.setNombre(rs.getString("nombre"));
        plantilla.setAsunto(rs.getString("asunto"));
        plantilla.setContenido(rs.getString("contenido"));
        plantilla.setTipoCanal(rs.getString("tipoCanal"));
        plantilla.setEstado(rs.getString("estado"));
        return plantilla;
    }
}
