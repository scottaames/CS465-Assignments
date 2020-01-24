package a00_echo_server;

import java.io.*;
import java.net.*;
import java.lang.Thread;

public class EchoServer {
  public static void main (String[] args) {
    try {
      ServerSocket server = new ServerSocket(8080);
      while (true) {
        Socket client = server.accept();
        Thread handler = new Thread(new EchoThread(client));
        handler.start();
      }
    }
    catch (Exception e) {
      System.err.println("Exception caught:" + e);
    }
  }
}