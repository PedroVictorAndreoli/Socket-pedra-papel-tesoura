import java.io.*;
import java.net.*;
import java.sql.SQLOutput;
import java.util.Scanner;
import java.util.Vector;


public class Main {
    public static void main(String[] args) throws Exception {
        tcpServer tcp = new tcpServer();
        tcp.run();
    }
}

class tcpServer{
    public void run() throws Exception{
        ServerSocket serverSocket = new ServerSocket(5000);
        Scanner scanner = new Scanner(System.in);
        int res = 0;
        String s ="",s1 ="",resJodador1,resJogador2;
        System.out.println("Aguardando Joagador 2 ...");

        Socket socket = serverSocket.accept();
        Vector<Integer> vec = new Vector<Integer>(3);
        vec.add(1);
        vec.add(2);
        vec.add(3);
        System.out.println("Conexao recebida" + socket.getInetAddress().getHostName());

        DataInputStream in = new DataInputStream(socket.getInputStream());
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());

        while (!vec.contains(res)) {
            System.out.println("Informe: \n1 - para papel\n2 - para pedra\n3 - para tesoura");
            res = scanner.nextInt();
            if (!vec.contains(res))
                System.out.println("numero invalido");
        }
        int k = in.readInt();
        System.out.println("Aguardando jogador 2");
        if(res == 1)
            resJodador1 = "Jogador 1 jogou papel";
        else if (res == 2)
            resJodador1 = "Jogador 1 jogou pedra";
        else
            resJodador1 = "Jogador 1 jogou tesoura";

        if(k == 1)
            resJogador2 = "Jogador 2 jogou papel";
        else if (k == 2)
            resJogador2 = "Jogador 2 jogou pedra";
        else
            resJogador2 = "Jogador 2 jogou tesoura";
        s1 = resJodador1 + " e " + resJogador2;

        if(res == k)
            s = "empate";
        else if (res == 2 && k== 3)
            s = "jogador 1 ganha";
        else if (res == 3 && k== 2)
            s = "jogador 2 ganha";
        else if (res == 1 && k== 2)
            s = "jogador 1 ganha";
        else if (res == 2 && k== 1)
            s = "jogador 2 ganha";
        else if (res == 3 && k== 1)
            s = "jogador 1 ganha";
        else
            s = "jogador 2 ganha";

        System.out.println(s1);
        System.out.println(s);
        out.writeUTF(s1);
        out.writeUTF(s);

        in.close();
        out.close();
        socket.close();
        serverSocket.close();
    }

}