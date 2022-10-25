package T2Cliente;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Client {

public static void main(String args[]) throws Exception {
      
   if (args.length < 2) {
         System.out.println("Usage: java UDPClient <server_ip> <port>");
         return;
      }

      String serverAddr = args[0];
      int port = Integer.parseInt(args[1]);
      

      
      BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
      DatagramSocket clientSocket = new DatagramSocket();
      InetAddress IPAddress = InetAddress.getByName(serverAddr);
      
      while(true) {
         System.out.println("Digite uma mensagem: ");
         String mssg = inFromUser.readLine();
         byte[] sendData = new byte[1024];
         byte[] receiveData = new byte[1024];
         sendData = mssg.getBytes();
         DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
         clientSocket.send(sendPacket);

         // declara o pacote a ser recebido
         DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

         // recebe o pacote do cliente
         if(!mssg.contains("FIM")) {
            clientSocket.receive(receivePacket);
            String sentenceReceived = new String(receivePacket.getData());
            System.out.println("Mensagem recebida do servidor: " + sentenceReceived);
         } else {
            break;
         }
      }
      
      clientSocket.close();
   }
}