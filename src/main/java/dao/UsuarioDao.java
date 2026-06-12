package dao;

/**
 *
 * @author Lizbeth Huaman Ventura -1420446
 * Fecha: 10/06/2026
 */
import config.Conexion;
import modelo.Persona;
import modelo.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UsuarioDao {

    public boolean existeCorreo(String correo) {
        String sql = "SELECT 1 FROM personas WHERE correo = ? LIMIT 1";

        try {
            Connection conn = Conexion.getInstancia().getConexion();
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, correo);
                try (ResultSet rs = ps.executeQuery()) {
                    return rs.next();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // REGISTRO
    public boolean registrarUsuario(Usuario usuario) {
        String sqlPersona = "INSERT INTO personas (nombres, apellidos, direccion, correo, rol) VALUES (?, ?, ?, ?, ?)";
        String sqlUsuario = "INSERT INTO usuarios (idUsuario, contrasenia, idPersona) VALUES (?, ?, ?)";

        try {
            Connection conn = Conexion.getInstancia().getConexion();

            try (PreparedStatement psPersona = conn.prepareStatement(sqlPersona, PreparedStatement.RETURN_GENERATED_KEYS)) {
                psPersona.setString(1, usuario.getPersona().getNombres());
                psPersona.setString(2, usuario.getPersona().getApellidos());
                psPersona.setString(3, usuario.getPersona().getDireccion());
                psPersona.setString(4, usuario.getPersona().getCorreo());
                psPersona.setString(5, usuario.getPersona().getRol());
                psPersona.executeUpdate();

                try (ResultSet rsPersona = psPersona.getGeneratedKeys()) {
                    if (rsPersona.next()) {
                        int idPersona = rsPersona.getInt(1);

                        String sqlMaxId = "SELECT COALESCE(MAX(idUsuario), 0) + 1 AS nuevoId FROM usuarios";
                        int nuevoIdUsuario = 1;

                        try (PreparedStatement psMax = conn.prepareStatement(sqlMaxId);
                             ResultSet rsId = psMax.executeQuery()) {
                            if (rsId.next()) {
                                nuevoIdUsuario = rsId.getInt("nuevoId");
                            }
                        }

                        try (PreparedStatement psUsuario = conn.prepareStatement(sqlUsuario)) {
                            psUsuario.setInt(1, nuevoIdUsuario);
                            psUsuario.setString(2, usuario.getContrasenia());
                            psUsuario.setInt(3, idPersona);
                            return psUsuario.executeUpdate() > 0;
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // LOGIN
    public Usuario validarLogin(String correo, String contrasenia) {
        String sql = "SELECT u.idUsuario, u.contrasenia, p.* FROM usuarios u "
                + "JOIN personas p ON u.idPersona = p.idPersona "
                + "WHERE p.correo = ? AND u.contrasenia = ?";

        try {
            Connection conn = Conexion.getInstancia().getConexion();
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, correo);
                ps.setString(2, contrasenia);

                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        Persona p = new Persona();
                        p.setIdPersona(rs.getInt("idPersona"));
                        p.setNombres(rs.getString("nombres"));
                        p.setApellidos(rs.getString("apellidos"));
                        p.setDireccion(rs.getString("direccion"));
                        p.setRol(rs.getString("rol"));

                        Usuario u = new Usuario();
                        u.setIdUsuario(rs.getInt("idUsuario"));
                        u.setContrasenia(rs.getString("contrasenia"));
                        u.setPersona(p);

                        return u;
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}