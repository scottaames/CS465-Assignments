import java.io.*;
import java.net.*;

/*
* Desc:
    Class that handles the execution of the server. It will return characters
    sent to it immediately using Telnet on the commandline.
 */
class EchoThread implements Runnable {

    // The socket object that will be used to connect to the client
    protected Socket clientSocket;

    // Boolean that keeps track of whether or not the client has quit
    private boolean isRunning;

    /*
     * Constructor method
     * Input:
     *   clientSocket: The socket that the client is using on their computer that
     *     we want to use to talk to them
     */
    public EchoThread(Socket clientSocket) {

        // Saving the socket input to the object field
        this.clientSocket = clientSocket;

        // Initializing the program running to true. If this object is created,
        //   the connection was successful.
        this.isRunning = true;
    }

    /*
    * Desc:
        Method run when the 'start' method is called on the thread. Acts as the
        main method for the object.
     */
    public void run() {
        try {
            // If this method is running, the connection has succeeded.
            System.out.println("Successfully connected");

            // Create the object that takes the input from the client
            InputStream fromClient = clientSocket.getInputStream();

            // Create the object that will send info back to the client
            OutputStream toClient = clientSocket.getOutputStream();

            // Variable that holds the character input from the client
            char charFromClient;

            // String that keeps track of whether or not the client wants to quit
            String quitString = "";

            // Tell the client that they have connected successfully
            toClient.write(("EchoServer Connected:\n\n").getBytes());

            // Continue accepting char input and giving back the char output
            //   to the client until they type quit
            while (isRunning) {

                // Read the character from the client
                charFromClient = (char) fromClient.read();

                // Check if the character is a letter
                if (Character.isLetter(charFromClient)) {

                    // Check how far the client is into typing 'quit'
                    // The longer the string length, the further in they are
                     switch (quitString.length()) {

                         // When the client has not begun typing in 'quit' yet
                         case 0:

                             // Check if the client has typed 'q'
                             if (charFromClient == 'q') {
                                 quitString += 'q';
                             }

                             // Otherwise, the sequence has been interrupted,
                             //   so we will clear the string
                             else {
                                 quitString = "";
                             }
                             break;

                         // When the client has typed 'q'
                         case 1:

                             // Check if the client has typed 'u'
                             if (charFromClient == 'u') {
                                 quitString += 'u';
                             }

                             // Otherwise, the sequence has been interrupted,
                             //   so we will clear the string
                             else {
                                 quitString = "";
                             }
                             break;

                         // When the client has typed 'qu'
                         case 2:

                             // Check if the client has typed 'i'
                             if (charFromClient == 'i') {
                                 quitString += 'i';
                             }

                             // Otherwise, the sequence has been interrupted,
                             //   so we will clear the string
                             else {
                                 quitString = "";
                             }
                             break;

                         // When the client has typed 'qui'
                         case 3:

                             // Check if the client has typed 't'
                             if (charFromClient == 't') {

                                 // If so, then the client has typed 'quit'
                                 //   uninterrupted and will now end the connection
                                 isRunning = false;
                             }

                             // Otherwise, the sequence has been interrupted,
                             //   so we will clear the string
                             else {
                                 quitString = "";
                             }
                             break;
                     }

                     // Write the letter back to the client
                     toClient.write((byte) charFromClient);
                }
                // If the client did not type a letter, nothing will be returned
            }

            // If the session has ented by entering 'quit', display this message to the client
            toClient.write("\nSession ended: exit word 'quit' was read from client.".getBytes());

            // Close the connection to and from the client
            toClient.close();
            fromClient.close();

        } catch (IOException e) {
            //report exception somewhere.
            e.printStackTrace();
        }
    }

    
}