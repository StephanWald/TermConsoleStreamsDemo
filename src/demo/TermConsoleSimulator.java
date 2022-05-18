package demo;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


/**
 * This class simulates the BBj side of things
 * with a very simple implementation
 * it consumes all charactes that come from the client
 * if a block of data received contains the words "HELLO"
 * it returns a message to the client
 */
public class TermConsoleSimulator implements Runnable {

    InputStream isFromClient;
    OutputStream osToClient;


    public TermConsoleSimulator(InputStream isFromClient, MessagesToClientOutputStream osToClient) throws IOException {
        this.isFromClient = isFromClient;
        this.osToClient = osToClient;
    }

    @Override
    public void run()  {
        System.out.println("demo.TermConsoleSimulator started - this is the BBJ side of things!");

        while (true) {
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
            }
            byte[] bytesFromClient = new byte[0];
                try {
                    if (isFromClient.available()>0) {
                        bytesFromClient = isFromClient.readAllBytes();
                        if (new String(bytesFromClient).contains("HELLO")){
                            osToClient.write(new String("HI THERE\n").getBytes());
                        }
                    }
                } catch (IOException ex) {}
    }
}
}
