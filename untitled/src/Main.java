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
        int res = 0,cont = 0,pontosJogador1 =0,pontosJogador2 = 0;
        String s ="",s1 ="",s2 = "",resJodador1,resJogador2;
        System.out.println("Aguardando Joagador 2 ...");

        Socket socket = serverSocket.accept();
        Vector<Integer> vec = new Vector<Integer>(3);
        vec.add(1);
        vec.add(2);
        vec.add(3);
        System.out.println("Conexao recebida" + socket.getInetAddress().getHostName());

        DataInputStream in = new DataInputStream(socket.getInputStream());
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        while (cont<3) {
            while (!vec.contains(res)) {
                System.out.println("Informe: \n1 - para papel\n2 - para pedra\n3 - para tesoura");
                res = scanner.nextInt();
                if (!vec.contains(res))
                    System.out.println("numero invalido");
            }
            System.out.println("Aguardando jogador 2");
            int k = in.readInt();
            if (res == 1)
                resJodador1 = "Jogador 1 jogou papel";
            else if (res == 2)
                resJodador1 = "Jogador 1 jogou pedra";
            else
                resJodador1 = "Jogador 1 jogou tesoura";

            if (k == 1)
                resJogador2 = "Jogador 2 jogou papel";
            else if (k == 2)
                resJogador2 = "Jogador 2 jogou pedra";
            else
                resJogador2 = "Jogador 2 jogou tesoura";
            s1 = resJodador1 + " e " + resJogador2;

            if (res == k)
                s = "empate";
            else if (res == 2 && k == 3){
                s = "jogador 1 ganha";
                pontosJogador1 +=1;
            }
            else if (res == 3 && k == 2){
                s = "jogador 2 ganha";
                pontosJogador2 +=1;
            }
            else if (res == 1 && k == 2){
                s = "jogador 1 ganha";
                pontosJogador1 +=1;
            }
            else if (res == 2 && k == 1){
                s = "jogador 2 ganha";
                pontosJogador2 +=1;
            }
            else if (res == 3 && k == 1){
                s = "jogador 1 ganha";
                pontosJogador1 +=1;
            }
            else{
                s = "jogador 2 ganha";
                pontosJogador2 +=1;
            }



            cont += 1;
            if(cont == 3){
                if(pontosJogador1 == pontosJogador2)
                    s2 = "empate no total";
                else if (pontosJogador1>pontosJogador2)
                    s2 = "Jogador 1 venceu a melhor de tres";
                else
                    s2 = "Jogador 2 venceu a melhor de tres";
            }
            System.out.println(s1);
            System.out.println(s);
            out.writeUTF(s1);
            out.writeUTF(s);
            out.writeInt(cont);
            out.writeUTF(s2);
            res = 0;
        }
        System.out.println(s2);
        in.close();
        out.close();
        socket.close();
        serverSocket.close();
    }

}