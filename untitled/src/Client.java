import java.io.*;
import java.net.*;
import java.sql.SQLOutput;
import java.util.Scanner;
import java.util.Vector;


public class Client{
    public static void main(String[] args) throws Exception {
        tcpClient tcp = new tcpClient();
        tcp.run();
    }
}

class tcpClient{
    public void run() throws Exception{
        Scanner scanner = new Scanner(System.in);
        int res = 0,cont = 0;
        String s,s2= "",s1;

        Socket socket = new Socket("127.0.0.1",5000);
        Vector<Integer> vec = new Vector<Integer>(3);
        vec.add(1);
        vec.add(2);
        vec.add(3);
        DataInputStream in = new DataInputStream(socket.getInputStream());
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        while (cont <3) {
            while (!vec.contains(res)) {
                System.out.println("Informe: \n1 - para papel\n2 - para pedra\n3 - para tesoura");
                res = scanner.nextInt();
                if (vec.contains(res))
                    out.writeInt(res);
                else
                    System.out.println("numero invalido");
            }
            System.out.println("Aguardando jogador 1");
            s = in.readUTF();
            s1 = in.readUTF();
            cont = in.readInt();
            s2 = in.readUTF();
            System.out.println(s);
            System.out.println(s1);
            res = 0;
        }
        System.out.println(s2);
        in.close();
        out.close();
        socket.close();
    }

}