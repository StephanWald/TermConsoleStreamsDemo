package demo;

import java.io.*;

/**
 * this class runs the sample
 * it reads from the console and posts the input to the isFromClient InputStream
 */
public class ExerciseTermConsoleDemo {


    public static void main(String[] args) throws IOException {

        ExerciseTermConsoleDemo demo = new ExerciseTermConsoleDemo();
        demo.run();

    }

    /**
     * A demo implementation of a "client"
     * Reads strings from the Java console
     * and sends them to the Termconsole mockup using the demo.MessagesFromClientInputStream
     * and prints to console whatever it receives by the demo.MessagesToClientOutputStream
     * @throws IOException
     */
    private void run() throws IOException {

        InputStream isFromClient = new MessagesFromClientInputStream();
        MessagesToClientOutputStream osToClient = new MessagesToClientOutputStream(this::consoleCallback);
        TermConsoleSimulator ts = new TermConsoleSimulator(isFromClient, osToClient);
        Thread thread = new Thread(ts);
        thread.start();

        while (true)
        {
            // Enter data using BufferReader
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            // Reading data using readLine
            byte[] inputFromClient = reader.readLine().getBytes();
            ((MessagesFromClientInputStream) isFromClient).recv(inputFromClient);

        }

    }

    /**
     * This is the callback method that receives the answer from BBj TermConsole
     * in reality this will invoke the method to send the string to the
     * client using websocket
     * @param character
     */
    private void consoleCallback(Character character) {
        System.out.print(character);
    }

}
