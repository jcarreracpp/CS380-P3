
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
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
            short length = 22;
            short ident = 0;
            short flagsAndFrag = 2;
            flagsAndFrag <<= 13;
            byte ttl = 50;
            byte protocol = 6;
            //int checksum = -25422;
            int checksum = 0;
            int sourceAddr = 1824010952;
            //int destAddr = 874862746;
            int destAddr = 52;
            destAddr <<= 4;
            destAddr += 37;
            destAddr <<= 4;
            destAddr += 88;
            destAddr <<= 4;
            destAddr += 154;
            byte datachunk = 0;
            short[] checksumCount;
            byte[] temp = new byte[22];
            temp[0] = versionAndIHL;
            temp[1] = tos;
            temp[2] = (byte)(length >> 8);
            temp[3] = (byte)(length);
            temp[4] = (byte)ident;
            temp[5] = (byte)ident;
            temp[6] = (byte)(flagsAndFrag >> 8);
            temp[7] = (byte)(flagsAndFrag);
            temp[8] = ttl;
            temp[9] = protocol;
            temp[10] = 0;
            temp[11] = 0;
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
            
            for(int i = 0; i < 20; i+=2){
                int checktemp = 0;

                checktemp += ((temp[i] << 8)& 0xFF);
                checktemp += ((temp[i+1])& 0xFF);
                //checktemp = (short)~checktemp;
                checksum += (checktemp);
                
            }
            
            checksum += (checksum >> 16);
            checksum = ~checksum;
            temp[10] = (byte)(checksum >> 8);
            temp[11] = (byte)(checksum);
            
            InputStream is = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is, "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            OutputStream os = socket.getOutputStream();
            
            os.write(temp);
            
            System.out.println(br.readLine());

            // 00110100001001010101100010011010
            
            //byte[] addr = InetAddress.getByName("codebank.xyz").getAddress();

//            for(int i = 0; i < addr.length; i++)
//                System.out.print(addr[i]+ " ");
    }
    }
}
