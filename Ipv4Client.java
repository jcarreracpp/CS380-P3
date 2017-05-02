
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.Socket;


/**
 *
 * @author Jorge
 */
public class Ipv4Client {
    public static void main(String[] args) throws Exception {
        try (Socket socket = new Socket("codebank.xyz", 38003)) {
            byte versionAndIHL = 69;
            byte tos = 0;
            short length = (byte) 20;
            short ident = 0;
            short flagsAndFrag = (short)16384;
            byte ttl = 50;
            byte protocol = 6;
            short checksum = -25422;
            int sourceAddr = 1824010952;
            int destAddr = 874862746;
            byte datachunk = 0;
            short[] checksumCount;
            byte[] temp = new byte[22];
            temp[0] = versionAndIHL;
            temp[1] = tos;
            temp[2] = (byte)(length >> 8);
            temp[3] = (byte)(length);
            temp[4] = 0;
            temp[5] = 0;
            temp[6] = (byte)(flagsAndFrag >> 8);
            temp[7] = (byte)(flagsAndFrag);
            temp[8] = ttl;
            temp[9] = protocol;
            temp[10] = (byte)(checksum >> 8);
            temp[11] = (byte)(checksum);
            temp[12] = (byte)(sourceAddr >> 24);
            temp[13] = (byte)(sourceAddr >> 16);
            temp[14] = (byte)(sourceAddr >> 8);
            temp[15] = (byte)(sourceAddr);
            temp[16] = (byte)(destAddr >> 24);
            temp[17] = (byte)(destAddr >> 16);
            temp[18] = (byte)(destAddr >> 8);
            temp[19] = (byte)(destAddr);
            temp[20] = 0;
            temp[21] = 0;
            
            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();
            
            os.write(temp);
            System.out.println(is.read());
            System.out.println(is.read());
            System.out.println(is.read());
            System.out.println(is.read());
            System.out.println(is.read());
            System.out.println(is.read());
            System.out.println(is.read());
            System.out.println(is.read());
            System.out.println(is.read());
            System.out.println(is.read());
            System.out.println(is.read());
            System.out.println(is.read());
            System.out.println(is.read());
            System.out.println(is.read());
            System.out.println(is.read());
            System.out.println(is.read());
            System.out.println(is.read());
            
           // byte[] addr = InetAddress.getByName("108.184.50.200").getAddress();

            //for(int i = 0; i < addr.length; i++)
            //    System.out.print(addr[i]+ " ");
    }
    }
}
