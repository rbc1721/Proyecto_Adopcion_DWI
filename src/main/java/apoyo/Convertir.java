
package apoyo;

import jakarta.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author Eduardo
 */
public class Convertir {
    public static byte[] convertirPartABytes(Part part) throws IOException {
        try (InputStream input = part.getInputStream()) {
            return input.readAllBytes();
        }
    }
}
