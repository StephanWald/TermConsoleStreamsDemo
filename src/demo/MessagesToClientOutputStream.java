package demo;

import java.io.IOException;
import java.io.OutputStream;
import java.util.function.Consumer;

public class MessagesToClientOutputStream extends OutputStream {

    Consumer<Character> toClientMethod;
    public MessagesToClientOutputStream(Consumer<Character> callback){
        this.toClientMethod = callback;
    }

    @Override
    public void write(int b) throws IOException {
        //immediately print the character
        //to start with we can send it to the client
        //in reality we'll need some batching
        toClientMethod.accept((char)b);
    }
}
