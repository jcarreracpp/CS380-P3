
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
            short checksum = 0;
            int sourceAddr = 1824010952;

           // byte[] addr = InetAddress.getByName("108.184.50.200").getAddress();
            InetAddress addr = InetAddress.getByName("codebank.xyz");
            System.out.println(addr);
            //for(int i = 0; i < addr.length; i++)
            //    System.out.print(addr[i]+ " ");
    }
    }
}
