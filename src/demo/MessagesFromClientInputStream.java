package demo;

import java.util.*;
import java.io.*;
import java.util.concurrent.BlockingQueue;

import static java.lang.Math.min;

public class MessagesFromClientInputStream  extends InputStream {

    byte[] queue = new byte[0];
    public synchronized void recv(byte[] in){
        byte[] c = new byte[queue.length + in.length];
        System.arraycopy(queue, 0, c, 0, queue.length);
        System.arraycopy(in, 0, c, queue.length, in.length);
        queue = c;

    }


    @Override
    public int read() {
        if (queue.length>0) {
            int result = queue[0];
            return result;
        }
        else return -1;
    }

    @Override
    public int read(byte[] data, int offset, int length) throws IOException {
        throw new IOException("Not yet implemented!");
    }

    @Override
    public int read(byte[] data) throws IOException {
        throw new IOException("Not yet implemented!");
    }

    @Override
    public long skip(long bytesToSkip) throws IOException {
        throw new IOException("Not yet implemented!");
    }

    @Override
    public byte[] readAllBytes() throws IOException {
        byte[] temp = queue;
        queue = new byte[0];
        return temp;
    }

    @Override
    public int available() throws IOException {
        return queue.length;
    }
}
