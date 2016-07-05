import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * Created by mist36 on 2016/06/28.
 */
public class JavaNetHttpClient {


    public static void main(String[] args) throws  IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        JavaNetHttpClient jn = new JavaNetHttpClient();

        String code = bufferedReader.readLine();

        executeGet (code);
    }

    public static void executeGet(String value) throws IOException {

        System.out.println("===== HTTP GET Start =====");

        try {

            URL url = new URL("http://weather.livedoor.com/forecast/webservice/json/v1?city="+ value);

            HttpURLConnection connection = null;

            try {
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    try (InputStreamReader isr = new InputStreamReader(connection.getInputStream(),
                            StandardCharsets.UTF_8);
                         BufferedReader reader = new BufferedReader(isr)) {
                        String line;
                        while ((line = reader.readLine()) != null) {
                            System.out.println(line);
                        }
                    }
                }
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("===== HTTP GET End =====");
    }

    }
