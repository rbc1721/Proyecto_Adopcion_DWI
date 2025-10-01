
package dao;
import config.Conexion;
import java.sql.Connection;

public class PruebaConexion {
    public static Boolean Prueba(){
        try {
            Connection conexion = Conexion.getInstancia().getConexion();
            if(conexion != null){
                System.out.println("Conexion establecida");
            }else{
                System.out.println("No se pudo establecer la conexion");
            }
        } catch (Exception e) {
            System.out.println("Error al intentar conectarse a la bd "+ e.getMessage());
            e.printStackTrace();
        }
        return true;
    }
}
