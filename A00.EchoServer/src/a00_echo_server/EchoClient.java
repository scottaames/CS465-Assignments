package a00_echo_server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class EchoClient {

    private static Socket socket = null;
    public static void main(String[] args) throws IOException {
        try {
            socket = new Socket(InetAddress.getLocalHost(), 8080);
            System.out.println("Client Running on port 8080");

            //Send the message to the server
            OutputStream toClient = socket.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(toClient);
            BufferedWriter bw = new BufferedWriter(osw);
            System.out.println("Type in a string and Press Enter...");
            Scanner sc = new Scanner(System.in);
            String string = sc.next();
            System.out.println("string = " + string);
            String sendMessage = string + "\n"; ////Next to line
            bw.write(sendMessage);
            bw.flush();
            System.out.println("Message sent to the server : " + sendMessage);

            //Get the return message from the server
            InputStream fromClient = socket.getInputStream();
            InputStreamReader fromClientReader = new InputStreamReader(fromClient);
            BufferedReader br = new BufferedReader(fromClientReader);
            String message = br.readLine();
            System.out.println("Message received from the server : " + message);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

}