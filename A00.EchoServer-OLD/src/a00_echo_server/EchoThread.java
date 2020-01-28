package a00_echo_server;

import java.io.*;
import java.net.*;

class EchoThread implements Runnable {
	protected Socket clientSocket = null;

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
    }

    public void run() {
        try {

			// If this method is running, the connection has succeeded.
			// Tell the user
			system.out.println( "Sucessfully connected" );

            InputStream fromClient = clientSocket.getInputStream();
            OutputStream toClient = clientSocket.getOutputStream();
						char charFromClient;
						String

            long time = System.currentTimeMillis();
            output.write(("HTTP/1.1 200 OK\n\nWorkerRunnable: - " +
time +
"").getBytes());
            output.close();
            input.close();
            System.out.println("Request processed: " + time);
        } catch (IOException e) {
            //report exception somewhere.
            e.printStackTrace();
        }
    }

		/*
		* Desc:
				Method that checks if the character stream includes non-letter
				characters
		*/
		public boolean checkCharacter() {

			return true;
		}

		/*
		* Desc:
				Method that checks if the character string spells 'quit'
		*/
		public boolean checkQuit( String charFromClient) {

		}
  }

//