package stream;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccessFileExample {
    public static void main(String[] args) {
        try {
            File file2 = new File("a111111111");
            System.out.println(new String(readFromFile(file2, 0, 18)));
            writeToFile(file2, "I love my country and my people", 31);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static byte[] readFromFile(File file2, int position, int size)
            throws IOException {

        RandomAccessFile file = new RandomAccessFile(file2, "rw");
        file.seek(position);
        byte[] bytes = new byte[size];
        file.read(bytes);
        file.close();
        return bytes;
    }
    private static void writeToFile(File file2, String data, int position)
            throws IOException {
        RandomAccessFile file = new RandomAccessFile(file2, "rw");
        file.seek(position);
        file.write(data.getBytes());
        file.close();
    }
}