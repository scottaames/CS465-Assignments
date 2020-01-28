import java.io.*;
import java.net.*;

class EchoThread implements Runnable {
    protected Socket clientSocket;
    private boolean isRunning;

    /*
     * Constructor method
     * Desc:
     *
     * Input:
     *   clientSocket: The socket that the client is using on their computer that
     *     we want to use to talk to them
     *
     */
    public EchoThread(Socket clientSocket) {
        this.clientSocket = clientSocket;
        this.isRunning = false;
    }

    public void run() {
        try {
            // If this method is running, the connection has succeeded.
            // Tell the user
            System.out.println("Successfully connected");

            InputStream fromClient = clientSocket.getInputStream();
            OutputStream toClient = clientSocket.getOutputStream();
            //char charFromClient;
            //String

            long time = System.currentTimeMillis();
            toClient.write(("HTTP/1.1 200 OK\n\nWorkerRunnable: - " +
                    time +
                    "").getBytes());
            toClient.close();
            fromClient.close();
            System.out.println("Request processed: " + time);
        } catch (IOException e) {
            //report exception somewhere.
            e.printStackTrace();
        }
    }

    
}