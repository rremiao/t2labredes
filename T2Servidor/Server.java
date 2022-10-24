package T2Servidor;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Server {
    public static void main(String args[])  throws Exception
    {
       if (args.length < 1) {
          System.out.println("Usage: java UDPServer <port>");
          return;
       }

       int port = Integer.parseInt(args[0]);

       DatagramSocket serverSocket = new DatagramSocket(port);
       System.out.println("Server is listening on port " + port);

          byte[] receiveData = new byte[1024];
          while(true)
             {
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);

                String sentence = new String(receivePacket.getData());

                System.out.println("Mensagem recebida: " + sentence);
             }
    }
    
}
