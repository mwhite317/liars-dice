package net;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.net.UnknownHostException;


public class WebPoster {

    static private WebPoster webPoster = new WebPoster();
    private String gameName;

    static private final String SITE = "https://liarsdice-f5256.firebaseio.com/";

    private WebPoster() {
        gameName = getMACAddress();
    }

    /**
     * Send to Server current player information
     *
     * @param name       Player name
     * @param diceValues list of Player's dice Face Values
     */
    public static void postDice(String name, int[] diceValues) {
        webPoster.composeAndSendDice(name, diceValues);
    }

    /**
     * Send to Server current game message
     *
     * @param message Info to post
     */
    public static void postMessage(String message) {
        webPoster.composeAndSendMessage(message);
    }

    /**
     * Instance method corresponding to postDice
     *
     * @param name       Player name
     * @param diceValues list of Player's dice Face Values
     */
    private void composeAndSendDice(String name, int[] diceValues) {
        StringBuilder sb = new StringBuilder("{\"dice\": \"");
        for (int i = 0; i < diceValues.length; i++) {
            sb.append((i == 0 ? "" : ",") + diceValues[i]);
        }
        sb.append("\", \"timeStamp\":" + System.currentTimeMillis() + "}");
        send("dice/G" + gameName + "-" + name + "/.json", sb.toString(), true);
    }

    /**
     * Instance method corresponding to postDice
     *
     * @param message Message to post
     */
    private void composeAndSendMessage(String message) {
        String s = "{\"message\": \"" + message + "\"}";
        send("games/G" + gameName + "/.json", s, false);
    }

    /**
     * getMACAddress and use it as game identifier
     */
    private static String getMACAddress() {
        byte[] macAddress = null;
        try {
            InetAddress address = InetAddress.getLocalHost();
            NetworkInterface networkInterface = NetworkInterface.getByInetAddress(address);
            macAddress = networkInterface.getHardwareAddress();
        } catch (SocketException | UnknownHostException e) {
            System.out.println("Cannot read Mac Address" + e.getMessage());
            System.exit(1);
        }

        //now convert to string
        StringBuilder sb = new StringBuilder();
        for (byte b : macAddress) {
            sb.append(String.format("%02X", b));
        }
        return sb.toString();
    }

    /**
     * Send to Server Player dice info
     *
     * @param urlString composed url to call
     * @param payload   dice and timestamp information
     */
    private void send(String urlString, String payload, boolean isPut) {
        // System.out.println(SITE+urlString+"="+payload+"=");
        URL url = null;
        try {
            url = new URL(SITE + urlString);
        } catch (MalformedURLException e) {
            System.err.println("BAD URL " + urlString + " error= " + e.getMessage());
            System.exit(1);
        }
        HttpURLConnection httpConn = null;
        try {
            httpConn = (HttpURLConnection) url.openConnection();
            //httpConn.setRequestProperty("X-HTTP-Method-Override", "PATCH");
            httpConn.setRequestMethod(isPut ? "PUT" : "POST");
            httpConn.setReadTimeout(5000);  //5 seconds

            // For PUT body
            httpConn.setDoOutput(true);
            httpConn.setRequestProperty("Content-Type", "application/json");
            httpConn.setRequestProperty("Accept", "application/json");
            OutputStreamWriter os = new OutputStreamWriter(httpConn.getOutputStream());
            os.write(payload);
            os.flush();
            os.close();
            // For PUT body END
            int response = httpConn.getResponseCode();
            //System.err.println(response);
        } catch (IOException e) {
            System.err.println("Cannot establish connection to " + urlString + " error= " + e.getMessage());
            // System.exit(1);
        } //finally {
           // if (httpConn != null) {
           //     httpConn.disconnect();
           // }
        //}
    }

}