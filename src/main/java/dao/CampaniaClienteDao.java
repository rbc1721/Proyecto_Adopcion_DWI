package dao;

import config.Conexion;
import modelo.CampaniaCliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CampaniaClienteDao {

    public boolean asignarCliente(CampaniaCliente campaniaCliente) {
        String sql = "INSERT INTO CampaniaCliente (idCampania, idCliente, estado) VALUES (?, ?, ?)";

        try {
            Connection conn = Conexion.getInstancia().getConexion();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, campaniaCliente.getIdCampania());
            ps.setInt(2, campaniaCliente.getIdCliente());
            ps.setString(3, campaniaCliente.getEstado());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean actualizarEstado(int idCampaniaCliente, String estado) {
        String sql = "UPDATE CampaniaCliente SET estado = ? WHERE idCampaniaCliente = ?";

        try {
            Connection conn = Conexion.getInstancia().getConexion();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, estado);
            ps.setInt(2, idCampaniaCliente);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean eliminar(int idCampaniaCliente) {
        String sql = "DELETE FROM CampaniaCliente WHERE idCampaniaCliente = ?";

        try {
            Connection conn = Conexion.getInstancia().getConexion();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idCampaniaCliente);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public List<CampaniaCliente> listarPorCampania(int idCampania) {
        List<CampaniaCliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM CampaniaCliente WHERE idCampania = ? ORDER BY idCampaniaCliente DESC";

        try {
            Connection conn = Conexion.getInstancia().getConexion();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idCampania);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(mapCampaniaCliente(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    public List<CampaniaCliente> listar() {
        List<CampaniaCliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM CampaniaCliente ORDER BY idCampaniaCliente DESC";

        try {
            Connection conn = Conexion.getInstancia().getConexion();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(mapCampaniaCliente(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    private CampaniaCliente mapCampaniaCliente(ResultSet rs) throws Exception {
        CampaniaCliente campaniaCliente = new CampaniaCliente();
        campaniaCliente.setIdCampaniaCliente(rs.getInt("idCampaniaCliente"));
        campaniaCliente.setIdCampania(rs.getInt("idCampania"));
        campaniaCliente.setIdCliente(rs.getInt("idCliente"));
        campaniaCliente.setFechaAsignacion(rs.getTimestamp("fechaAsignacion"));
        campaniaCliente.setEstado(rs.getString("estado"));
        return campaniaCliente;
    }
}
