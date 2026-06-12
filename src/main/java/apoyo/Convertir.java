
package apoyo;

import jakarta.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author Lizbeth Huaman Ventura -1420446
 * Fecha: 10/06/2026
 */
public class Convertir {
    public static byte[] convertirPartABytes(Part part) throws IOException {
        try (InputStream input = part.getInputStream()) {
            return input.readAllBytes();
        }
    }
}
