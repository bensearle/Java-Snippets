import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;


/**
 * @author Ben Searle
 * Class to encode to Base64 and decode from Base64 data of the following types
 *  byte[]
 *  int[]
 */
public class Base64 {

    /**
     * Main method to test other methods
     *
     * @param args
     */
    public static void main(String[] args) {
        byte[] byteArray = {-128, -1, 0, 1, 127}; // initial array
        String byteEN = encodeByteArr(byteArray); // encode array
        byte[] byteDE = decodeByteArr(byteEN); // decode string

        // print results
        System.out.print("Byte[] = { ");
        for (int i = 0; i < byteArray.length; i++) {
            System.out.print(byteArray[i] + " ");
        }
        System.out.println("}");
        System.out.print("Encoded  = " + byteEN);
        System.out.print("Decoded = { ");
        for (int i = 0; i < byteDE.length; i++) {
            System.out.print(byteDE[i] + " ");
        }
        System.out.println("}");
        System.out.println(""); // print blank line

        int[] intArray = {-2147483648, -1, 0, 1, 2147483647}; // initial array
        String intEN = encodeIntArr(intArray); // encode array
        int[] intDE = decodeIntArr(intEN); // decode string
        // print results
        System.out.print("Int[] = { ");
        for (int i = 0; i < intArray.length; i++) {
            System.out.print(intArray[i] + " ");
        }
        System.out.println("}");
        System.out.print("Encoded  = " + intEN);
        System.out.print("Decoded = { ");
        for (int i = 0; i < intDE.length; i++) {
            System.out.print(intDE[i] + " ");
        }
        System.out.println("}");
        System.out.println(""); // print blank line
    }

    /**
     * method to encode a byte[] into a Base 64 string
     *
     * @param data is the byte array
     * @return base 64 string
     */
    public static String encodeByteArr(byte[] data) {
        BASE64Encoder encoder = new BASE64Encoder();
        String encodedBytes = encoder.encodeBuffer(data);
        return encodedBytes;
    }

    /**
     * method to decode a Base 64 string into a byte[]
     *
     * @param s is the base 64 string
     * @return byte array
     */
    public static byte[] decodeByteArr(String s) {
        try {
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] decodedBytes = decoder.decodeBuffer(s);
            return decodedBytes;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * method to encode a int[] into a Base 64 string 
     * convert to byte[] and then encode
     *
     * @param data is the int array
     * @return byte array
     */
    public static String encodeIntArr(int[] data) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(data.length * 4); // int is 4 bytes
        IntBuffer intBuffer = byteBuffer.asIntBuffer();
        intBuffer.put(data);
        byte[] byteData = byteBuffer.array();
        return encodeByteArr(byteData); // return encoded byte[]
    }

    /**
     * method to decode a Base 64 string into a int[] 
     * decode and then convert from byte[]
     *
     * @param data is the int array
     * @return byte array
     */
    public static int[] decodeIntArr(String s) {
        byte[] data = decodeByteArr(s); // get decoded byte[]
        IntBuffer intBuf = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).asIntBuffer();
        int[] intData = new int[intBuf.remaining()];
        intBuf.get(intData);
        return intData;
    }
}
