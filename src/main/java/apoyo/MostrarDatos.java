
package apoyo;

import dao.MascotaDao;
import java.util.ArrayList;
import java.util.List;
import modelo.Mascota;
import modelo.MascotaEncontrada;
import modelo.Reporte;

/**
 *
 * @author Eduardo
 */
public class MostrarDatos {
    public static List<MascotaEncontrada>MostrarMascotaEncontradas(List<Reporte> reportes){
        List<MascotaEncontrada> lista = new ArrayList<>();
        
        for (Reporte reporte : reportes) {
            int idMascota = reporte.getIdMascota();
            
            Mascota mascota = MascotaDao.obtener(idMascota);
            MascotaEncontrada me = new MascotaEncontrada(reporte.getCodigo(), reporte.getFecha(), reporte.getDireccion(), reporte.getIdUduario(), idMascota, mascota.getEspecie(), mascota.getRaza(), mascota.getColor(), mascota.getTamanio(),mascota.getDescripcion(), mascota.getFoto());
            lista.add(me);
        }       
        return lista;
    }
}
