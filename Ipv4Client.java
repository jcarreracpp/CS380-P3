
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;


/**
 *
 * @author Jorge
 */
public class Ipv4Client {
    public static void main(String[] args) throws Exception {
        try (Socket socket = new Socket("codebank.xyz", 38003)) {
            int payload;

            InputStream is = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is, "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            OutputStream os = socket.getOutputStream();
            
            for(int i = 0; i < 12; i++ ){
                payload = 2;
                for(int j = i; j > 0; j--){
                    payload *=2;
                }
            os.write(recalculatePacket(payload));
            System.out.println("data length: "+payload);
            System.out.println(br.readLine()+"\n");
            }
        }
    }
    public static byte[] recalculatePacket(int size) {
        byte versionAndIHL = 69;
        byte tos = 0;
        short length = (short)(20 + size);
        short ident = 0;
        short flagsAndFrag = 1;
        flagsAndFrag <<= 14;
        byte ttl = 50;
        byte protocol = 6;
        int checksum = 0;
        int sourceAddr = 1824010952;
        int destAddr = 874862746;
        int[] checksumCount = new int[22];
        byte[] temp = new byte[(20+size)];
        temp[0] = versionAndIHL;
        temp[1] = tos;
        temp[2] = (byte) (length >> 8);
        temp[3] = (byte) (length);
        temp[4] = (byte) (ident >> 8);
        temp[5] = (byte) (ident);
        temp[6] = (byte) (flagsAndFrag >> 8);
        temp[7] = (byte) (flagsAndFrag);
        temp[8] = ttl;
        temp[9] = protocol;
        temp[10] = 0;
        temp[11] = 0;
        temp[12] = (byte) (sourceAddr >> 24);
        temp[13] = (byte) (sourceAddr >> 16);
        temp[14] = (byte) (sourceAddr >> 8);
        temp[15] = (byte) (sourceAddr);
        temp[16] = (byte) (destAddr >> 24);
        temp[17] = (byte) (destAddr >> 16);
        temp[18] = (byte) (destAddr >> 8);
        temp[19] = (byte) (destAddr);
        for(int k = 20; k < length; k++){
            temp[k] = 0;
        }
        for (int j = 0; j < 20; j += 2) {
            checksumCount[(j / 2)] += (short) (temp[j] << 8);
            checksumCount[(j / 2)] += (short) (temp[(j + 1)] & 0xFF);
        }
        for (int i = 0; i < 10; i++) {
            checksum += checksumCount[i];
        }
        checksum++;
        checksum = ~checksum;
        temp[10] = (byte) (checksum >> 8);
        temp[11] = (byte) (checksum & 0xFF);
        return temp;
    }
}
