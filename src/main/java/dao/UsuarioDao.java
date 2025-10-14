package dao;

/**
 *
 * @author huamanls
 */
import config.Conexion;
import modelo.Persona;
import modelo.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UsuarioDao {

    // REGISTRO
    public boolean registrarUsuario(Usuario usuario) {
        String sqlPersona = "INSERT INTO persona (nombres, apellidos, direccion, rol) VALUES (?, ?, ?, ?)";
        String sqlUsuario = "INSERT INTO usuario (contrasenia, idPersona) VALUES (?, ?)";

        try {
            Connection conn = Conexion.getInstancia().getConexion();

            // Insertar persona primero
            PreparedStatement psPersona = conn.prepareStatement(sqlPersona, PreparedStatement.RETURN_GENERATED_KEYS);
            psPersona.setString(1, usuario.getPersona().getNombres());
            psPersona.setString(2, usuario.getPersona().getApellidos());
            psPersona.setString(3, usuario.getPersona().getDireccion());
            psPersona.setString(4, usuario.getPersona().getRol());
            psPersona.executeUpdate();

            ResultSet rs = psPersona.getGeneratedKeys();
            if (rs.next()) {
                int idPersona = rs.getInt(1);

                // Insertar usuario con idPersona
                PreparedStatement psUsuario = conn.prepareStatement(sqlUsuario);
                psUsuario.setString(1, usuario.getContrasenia());
                psUsuario.setInt(2, idPersona);

                return psUsuario.executeUpdate() > 0;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // LOGIN
    public Usuario validarLogin(String correo, String contrasenia) {
        String sql = "SELECT u.idUsuario, u.contrasenia, p.* FROM usuario u JOIN persona p ON u.idPersona = p.idPersona WHERE p.nombres = ? AND u.contrasenia = ?";

        try {
            Connection conn = Conexion.getInstancia().getConexion();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, correo); 
            ps.setString(2, contrasenia);

            ResultSet rs = ps.executeQuery();
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

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}