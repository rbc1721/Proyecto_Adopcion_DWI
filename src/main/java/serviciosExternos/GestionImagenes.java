package serviciosExternos;

import jakarta.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import org.json.JSONObject;

/**
 *
 * @author Eduardo
 */
public class GestionImagenes {

    private static final String API_KEY = "00046b32493d66f1f80fd28ccedf07ff";
    private static final String UPLOAD_URL = "https://thumbsnap.com/api/upload";

    public static String enviarFoto(Part foto) throws IOException {
        // 1. Generar un boundary único
        String boundary = "----JavaFormBoundary" + System.currentTimeMillis();

        // 2. Configurar la conexión HTTP
        URL url = new URL(UPLOAD_URL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
        conn.setConnectTimeout(15000);
        conn.setReadTimeout(15000);

        try (OutputStream output = conn.getOutputStream()) {

            // === Parte 1: API Key ===
            String keyPart = "--" + boundary + "\r\n"
                    + "Content-Disposition: form-data; name=\"key\"\r\n\r\n"
                    + API_KEY + "\r\n";
            output.write(keyPart.getBytes());

            // === Parte 2: Imagen ===
            String filePartHeader = "--" + boundary + "\r\n"
                    + "Content-Disposition: form-data; name=\"media\"; filename=\"" + foto.getSubmittedFileName() + "\"\r\n"
                    + "Content-Type: " + foto.getContentType() + "\r\n\r\n";
            output.write(filePartHeader.getBytes());

            // Enviar contenido binario usando transferTo()
            try (InputStream is = foto.getInputStream()) {
                is.transferTo(output);
            }

            output.write("\r\n".getBytes()); // separador final de la parte
            output.write(("--" + boundary + "--\r\n").getBytes()); // fin del multipart
            output.flush();
        }

        // === Leer la respuesta ===
        int status = conn.getResponseCode();
        InputStream respStream = (status == HttpURLConnection.HTTP_OK)
                ? conn.getInputStream()
                : conn.getErrorStream();

        String respJson;
        try (Scanner sc = new Scanner(respStream)) {
            sc.useDelimiter("\\A");
            respJson = sc.hasNext() ? sc.next() : "";
        }

        // === Parsear JSON ===
        JSONObject obj = new JSONObject(respJson);

        if (obj.optBoolean("success", false)) {
            JSONObject data = obj.getJSONObject("data");             
            return  data.getString("media");       
        } else {
            System.err.println("Error en subida: " + respJson);
            return null;
        }     
    }
}
