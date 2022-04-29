package nutriscoremfiakka;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class outputs a random integer from an HTTP service.
 *
 * @author arthu
 */
public class HTTPRandomGenerator {

    public static int getValue() {

        int MAX_SCORE = 4;
        int result = -1;

        URL url;
        try {
            url = new URL("https://www.random.org/integers/?num=1&min=0&max="
                    + MAX_SCORE
                    + "&col=1&base=10&format=plain&rnd=new");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            while ((output = br.readLine()) != null) {
                result = Integer.parseInt(output);
            }

            conn.disconnect();
        } catch (MalformedURLException ex) {
            Logger.getLogger(HTTPRandomGenerator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ProtocolException ex) {
            Logger.getLogger(HTTPRandomGenerator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(HTTPRandomGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
