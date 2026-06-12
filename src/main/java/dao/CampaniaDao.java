package dao;

import config.Conexion;
import modelo.Campania;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CampaniaDao {

    public boolean registrar(Campania campania) {
        String sql = "INSERT INTO Campania (nombre, descripcion, fechaInicio, fechaFin, fechaProgramada, tipoNotificacion, estado, idBono, idPlantilla) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            Connection conn = Conexion.getInstancia().getConexion();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, campania.getNombre());
            ps.setString(2, campania.getDescripcion());
            ps.setDate(3, campania.getFechaInicio());
            ps.setDate(4, campania.getFechaFin());
            ps.setTimestamp(5, campania.getFechaProgramada());
            ps.setString(6, campania.getTipoNotificacion());
            ps.setString(7, campania.getEstado());

            if (campania.getIdBono() == null) {
                ps.setNull(8, java.sql.Types.INTEGER);
            } else {
                ps.setInt(8, campania.getIdBono());
            }

            if (campania.getIdPlantilla() == null) {
                ps.setNull(9, java.sql.Types.INTEGER);
            } else {
                ps.setInt(9, campania.getIdPlantilla());
            }

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean actualizar(Campania campania) {
        String sql = "UPDATE Campania SET nombre = ?, descripcion = ?, fechaInicio = ?, fechaFin = ?, fechaProgramada = ?, tipoNotificacion = ?, estado = ?, idBono = ?, idPlantilla = ? WHERE idCampania = ?";

        try {
            Connection conn = Conexion.getInstancia().getConexion();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, campania.getNombre());
            ps.setString(2, campania.getDescripcion());
            ps.setDate(3, campania.getFechaInicio());
            ps.setDate(4, campania.getFechaFin());
            ps.setTimestamp(5, campania.getFechaProgramada());
            ps.setString(6, campania.getTipoNotificacion());
            ps.setString(7, campania.getEstado());

            if (campania.getIdBono() == null) {
                ps.setNull(8, java.sql.Types.INTEGER);
            } else {
                ps.setInt(8, campania.getIdBono());
            }

            if (campania.getIdPlantilla() == null) {
                ps.setNull(9, java.sql.Types.INTEGER);
            } else {
                ps.setInt(9, campania.getIdPlantilla());
            }

            ps.setInt(10, campania.getIdCampania());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean eliminar(int idCampania) {
        String sql = "DELETE FROM Campania WHERE idCampania = ?";

        try {
            Connection conn = Conexion.getInstancia().getConexion();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idCampania);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public Campania obtenerPorId(int idCampania) {
        String sql = "SELECT * FROM Campania WHERE idCampania = ?";

        try {
            Connection conn = Conexion.getInstancia().getConexion();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idCampania);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapCampania(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Campania> listar() {
        List<Campania> lista = new ArrayList<>();
        String sql = "SELECT * FROM Campania ORDER BY idCampania DESC";

        try {
            Connection conn = Conexion.getInstancia().getConexion();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(mapCampania(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    private Campania mapCampania(ResultSet rs) throws Exception {
        Campania campania = new Campania();
        campania.setIdCampania(rs.getInt("idCampania"));
        campania.setNombre(rs.getString("nombre"));
        campania.setDescripcion(rs.getString("descripcion"));
        campania.setFechaInicio(rs.getDate("fechaInicio"));
        campania.setFechaFin(rs.getDate("fechaFin"));
        campania.setFechaProgramada(rs.getTimestamp("fechaProgramada"));
        campania.setTipoNotificacion(rs.getString("tipoNotificacion"));
        campania.setEstado(rs.getString("estado"));
        campania.setFechaCreacion(rs.getTimestamp("fechaCreacion"));

        int idBono = rs.getInt("idBono");
        campania.setIdBono(rs.wasNull() ? null : idBono);

        int idPlantilla = rs.getInt("idPlantilla");
        campania.setIdPlantilla(rs.wasNull() ? null : idPlantilla);

        return campania;
    }
}
