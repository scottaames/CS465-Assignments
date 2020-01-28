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
        this.isRunning = true;
    }

    public void run() {
        try {
            // If this method is running, the connection has succeeded.
            // Tell the user
            System.out.println("Successfully connected");

            InputStream fromClient = clientSocket.getInputStream();
            OutputStream toClient = clientSocket.getOutputStream();
            char charFromClient;
            char[] quitArray = new char[4];

            toClient.write(("HTTP/1.1 200 OK\n\nEchoServer Connected:\n\n").getBytes());

            while (isRunning) {
                charFromClient = (char) fromClient.read();
                if (Character.isLetter(charFromClient)) {
                     switch (quitArray.length) {
                         case 0:
                             if (charFromClient == 'q') {
                                 quitArray[0] = charFromClient;
                             }
                             break;
                         case 1:
                             if (charFromClient == 'u') {
                                 quitArray[1] = charFromClient;
                             }
                             break;
                         case 2:
                             if (charFromClient == 'i') {
                                 quitArray[2] = charFromClient;
                             }
                             break;
                         case 3:
                             if (charFromClient == 't') {
                                 isRunning = false;
                             }
                             break;
                     }
                     toClient.write((byte) charFromClient);
                }
            }
            toClient.close();
            fromClient.close();
            System.out.println("Session ended: exit word 'quit' was read from client.");
        } catch (IOException e) {
            //report exception somewhere.
            e.printStackTrace();
        }
    }

    
}