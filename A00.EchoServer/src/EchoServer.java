import java.net.*;
import java.lang.Thread;

/*
* Desc:
    Class that acts as the server. It creates threads that will each handle
    individual clients.
 */
public class EchoServer {
    public static void main (String[] args) {

        try {

            // Open a socket on the server side
            ServerSocket server = new ServerSocket(8080);

            // Be able to continuously accept new clients as they come and go
            while (true) {

                // Wait until a client sends a request to us, the server
                Socket client = server.accept();

                // Open a new thread, sending the client's socket to the new thread
                Thread clientThread = new Thread(new EchoThread(client));

                // Begin execution of the thread that will handle the server's function
                clientThread.start();
            }
        }
        catch (Exception e) {
            System.err.println("Exception caught:" + e);
        }
    }
}