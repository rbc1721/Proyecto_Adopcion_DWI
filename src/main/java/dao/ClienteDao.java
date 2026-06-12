package dao;

import config.Conexion;
import modelo.Cliente;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClienteDao {

    public boolean registrar(Cliente cliente) {
        String sql = "INSERT INTO Cliente (dni, nombre, apellido, correo, telefono, fechaRegistro, segmento, estado) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            Connection conn = Conexion.getInstancia().getConexion();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, cliente.getDni());
            ps.setString(2, cliente.getNombre());
            ps.setString(3, cliente.getApellido());
            ps.setString(4, cliente.getCorreo());
            ps.setString(5, cliente.getTelefono());
            ps.setDate(6, cliente.getFechaRegistro());
            ps.setString(7, cliente.getSegmento());
            ps.setString(8, cliente.getEstado());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean actualizar(Cliente cliente) {
        String sql = "UPDATE Cliente SET dni = ?, nombre = ?, apellido = ?, correo = ?, telefono = ?, fechaRegistro = ?, segmento = ?, estado = ? WHERE idCliente = ?";

        try {
            Connection conn = Conexion.getInstancia().getConexion();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, cliente.getDni());
            ps.setString(2, cliente.getNombre());
            ps.setString(3, cliente.getApellido());
            ps.setString(4, cliente.getCorreo());
            ps.setString(5, cliente.getTelefono());
            ps.setDate(6, cliente.getFechaRegistro());
            ps.setString(7, cliente.getSegmento());
            ps.setString(8, cliente.getEstado());
            ps.setInt(9, cliente.getIdCliente());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean eliminar(int idCliente) {
        String sql = "DELETE FROM Cliente WHERE idCliente = ?";

        try {
            Connection conn = Conexion.getInstancia().getConexion();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idCliente);

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public Cliente obtenerPorId(int idCliente) {
        String sql = "SELECT * FROM Cliente WHERE idCliente = ?";

        try {
            Connection conn = Conexion.getInstancia().getConexion();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idCliente);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return mapCliente(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Cliente> listar() {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM Cliente ORDER BY idCliente DESC";

        try {
            Connection conn = Conexion.getInstancia().getConexion();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                lista.add(mapCliente(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    private Cliente mapCliente(ResultSet rs) throws Exception {
        Cliente cliente = new Cliente();
        cliente.setIdCliente(rs.getInt("idCliente"));
        cliente.setDni(rs.getString("dni"));
        cliente.setNombre(rs.getString("nombre"));
        cliente.setApellido(rs.getString("apellido"));
        cliente.setCorreo(rs.getString("correo"));
        cliente.setTelefono(rs.getString("telefono"));

        Date fechaRegistro = rs.getDate("fechaRegistro");
        cliente.setFechaRegistro(fechaRegistro);

        cliente.setSegmento(rs.getString("segmento"));
        cliente.setEstado(rs.getString("estado"));

        return cliente;
    }
}
