package dao;

import config.Conexion;
import modelo.Bono;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BonoDao {

    public boolean registrar(Bono bono) {
        String sql = "INSERT INTO Bono (nombre, descripcion, porcentajeDescuento, vigencia, estado) VALUES (?, ?, ?, ?, ?)";

        try {
            Connection conn = Conexion.getInstancia().getConexion();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, bono.getNombre());
            ps.setString(2, bono.getDescripcion());
            ps.setBigDecimal(3, bono.getPorcentajeDescuento());
            ps.setDate(4, bono.getVigencia());
            ps.setString(5, bono.getEstado());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean actualizar(Bono bono) {
        String sql = "UPDATE Bono SET nombre = ?, descripcion = ?, porcentajeDescuento = ?, vigencia = ?, estado = ? WHERE idBono = ?";

        try {
            Connection conn = Conexion.getInstancia().getConexion();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, bono.getNombre());
            ps.setString(2, bono.getDescripcion());
            ps.setBigDecimal(3, bono.getPorcentajeDescuento());
            ps.setDate(4, bono.getVigencia());
            ps.setString(5, bono.getEstado());
            ps.setInt(6, bono.getIdBono());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean eliminar(int idBono) {
        String sql = "DELETE FROM Bono WHERE idBono = ?";

        try {
            Connection conn = Conexion.getInstancia().getConexion();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idBono);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public Bono obtenerPorId(int idBono) {
        String sql = "SELECT * FROM Bono WHERE idBono = ?";

        try {
            Connection conn = Conexion.getInstancia().getConexion();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idBono);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapBono(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Bono> listar() {
        List<Bono> lista = new ArrayList<>();
        String sql = "SELECT * FROM Bono ORDER BY idBono DESC";

        try {
            Connection conn = Conexion.getInstancia().getConexion();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(mapBono(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    private Bono mapBono(ResultSet rs) throws Exception {
        Bono bono = new Bono();
        bono.setIdBono(rs.getInt("idBono"));
        bono.setNombre(rs.getString("nombre"));
        bono.setDescripcion(rs.getString("descripcion"));
        bono.setPorcentajeDescuento(rs.getBigDecimal("porcentajeDescuento"));
        bono.setVigencia(rs.getDate("vigencia"));
        bono.setEstado(rs.getString("estado"));
        return bono;
    }
}
