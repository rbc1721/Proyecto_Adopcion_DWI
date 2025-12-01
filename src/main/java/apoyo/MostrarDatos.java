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

    public static List<MascotaEncontrada> MostrarMascotaEncontradas(List<Reporte> reportes, String especie) {
        List<MascotaEncontrada> lista = new ArrayList<>();

        for (Reporte reporte : reportes) {

            int idMascota = reporte.getIdMascota();
            Mascota mascota = MascotaDao.obtener(idMascota);

            if (mascota != null && mascota.getEspecie().equals(especie)) {                                   
                MascotaEncontrada me = new MascotaEncontrada(reporte.getCodigo(), reporte.getFecha(), reporte.getDireccion(), reporte.getIdUsuario(), idMascota, mascota.getEspecie(), mascota.getRaza(), mascota.getColor(), mascota.getTamanio(), mascota.getDescripcion());
                lista.add(me);
            }
        }
        return lista;
    }
}
