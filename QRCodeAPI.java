import java.net.URL;
import java.net.HttpURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class QRCodeAPI {
    public static void main(String[] args) {
        try {
            // Create the URL for the QR code API
            URL url = new URL("https://api.qrserver.com/v1/create-qr-code/");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            
            // Set the parameters for the QR code
            String data = "data=Hello World";
            String size = "size=250x250";
            String format = "format=png";
            
            // Send the data to the API
            OutputStream os = con.getOutputStream();
            os.write(data.getBytes());
            os.write("&".getBytes());
            os.write(size.getBytes());
            os.write("&".getBytes());
            os.write(format.getBytes());
            os.flush();
            os.close();
            
            // Get the QR code image from the API
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            
            // Print the QR code image
            System.out.println(response.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
