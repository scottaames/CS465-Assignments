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
            String quitString = "";

            toClient.write(("EchoServer Connected:\n\n").getBytes());

            while (isRunning) {
                charFromClient = (char) fromClient.read();
                if (Character.isLetter(charFromClient)) {
                     switch (quitString.length()) {
                         case 0:
                             if (charFromClient == 'q') {
                                 quitString += 'q';
                             }
                             else {
                                 quitString = "";
                             }
                             break;
                         case 1:
                             if (charFromClient == 'u') {
                                 quitString += 'u';
                             }
                             else {
                                 quitString = "";
                             }
                             break;
                         case 2:
                             if (charFromClient == 'i') {
                                 quitString += 'i';
                             }
                             else {
                                 quitString = "";
                             }
                             break;
                         case 3:
                             if (charFromClient == 't') {
                                 isRunning = false;
                             }
                             break;
                     }
                     fromClient.nullInputStream();
                     toClient.write((byte) charFromClient);

                }
            }

            toClient.write("\nSession ended: exit word 'quit' was read from client.".getBytes());
            toClient.close();
            fromClient.close();
        } catch (IOException e) {
            //report exception somewhere.
            e.printStackTrace();
        }
    }

    
}