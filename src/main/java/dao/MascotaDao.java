package dao;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.Mascota;

public class MascotaDao {

    public static Integer registrar(Mascota mascota) {
        int idMascota = -1;
        String sql = "INSERT INTO mascotas(nombre, especie, raza, color, edad, tamanio, descripcion, foto) VALUES(?,?,?,?,?,?,?,?)";

        try (Connection conexion = Conexion.getInstancia().getConexion(); PreparedStatement ps = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {

            ps.setString(1, mascota.getNombre());
            ps.setString(2, mascota.getEspecie());
            ps.setString(3, mascota.getRaza());
            ps.setString(4, mascota.getColor());
            ps.setInt(5, mascota.getEdad());
            ps.setString(6, mascota.getTamanio());
            ps.setString(7, mascota.getDescripcion());
            ps.setString(8, mascota.getFoto());

            if (ps.executeUpdate() > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    idMascota = rs.getInt(1);
                }
            }
            return idMascota;

        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
        return idMascota;
    }

    public static Mascota obtener(int idMascota) {
        Mascota mascota = null;
        String sql = "SELECT * FROM mascotas WHERE idMascota = ?";

        try (Connection conexion = Conexion.getInstancia().getConexion(); PreparedStatement ps = conexion.prepareCall(sql);) {

            ps.setInt(1, idMascota);
            ResultSet datos = ps.executeQuery();

            mascota = new Mascota();

            while (datos.next()) {

                mascota.setIdMascota(datos.getInt("idMascota"));
                mascota.setNombre(datos.getString("nombre"));
                mascota.setEspecie(datos.getString("especie"));
                mascota.setRaza(datos.getString("raza"));
                mascota.setColor(datos.getString("color"));
                mascota.setEdad(datos.getInt("edad"));
                mascota.setTamanio(datos.getString("tamanio"));
                mascota.setDescripcion(datos.getString("descripcion"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mascota;
    }
    /*
    public static boolean actualizar(Mascota mascota) {
        String sql = "UPDATE mascotas SET estado = ? WHERE idMascota = ? ";

        try {
            Connection conexion = Conexion.getInstancia().getConexion();
            PreparedStatement ps = conexion.prepareStatement(sql);

            ps.setString(4, mascota.getEstado());
            ps.setInt(6, mascota.getIdMascota());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
      public static List<Mascota> obtener(String condicion, String valor) {
        List<Mascota> mascotas = new ArrayList<>();
        String sql = "SELECT * FROM mascotas WHERE idMascota = ?";

        try {
            Connection conexion = Conexion.getInstancia().getConexion();
            PreparedStatement ps = conexion.prepareCall(sql);
            ResultSet datos = ps.executeQuery();

            while (datos.next()) {
                Mascota mascota = new Mascota();

                mascota.setIdMascota(datos.getInt("idMascota"));
                mascota.setNombre(datos.getString("nombre"));
                mascota.setEspecie(datos.getString("especie"));
                mascota.setRaza(datos.getString("raza"));           
                mascota.setColor(datos.getString("color"));
                mascota.setEdad(datos.getInt("edad"));
                mascota.setTamanio(datos.getString("tamanio"));
                mascota.setDescripcion(datos.getString("descripcion"));    

                mascotas.add(mascota);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mascotas;
    }
     */
}
