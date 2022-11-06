package T2Cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

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
      
      clientSocket = setUserConnection(clientSocket, inFromUser, IPAddress, port);

      while(!clientSocket.isClosed()) {
         System.out.println("Digite seu comando:");         
         String mssg = inFromUser.readLine();
         byte[] sendData = new byte[1024];
         byte[] receiveData = new byte[1024];
         sendData = mssg.getBytes();
         DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
         clientSocket.send(sendPacket);

         // declara o pacote a ser recebido
         DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
         System.out.println(receivePacket.getData().toString());
         // recebe o pacote do cliente
         if(!mssg.contains("FIM")) {
            clientSocket.receive(receivePacket);
            String sentenceReceived = new String(receivePacket.getData());
            System.out.println("Mensagem recebida do servidor: " + sentenceReceived);
         } else {
            clientSocket.close();
         }
      }
   }

   private static DatagramSocket setUserConnection(DatagramSocket clientSocket, BufferedReader inFromUser, InetAddress IPAddress, int port) throws IOException {
      System.out.println("Digite seu nome: ");
      String connect = "CRIAR ";
      String name = connect.concat(inFromUser.readLine());
      byte[] sendName = new byte[1024];
      byte[] response = new byte[1024];
      sendName = name.getBytes();
      DatagramPacket sendNamePacket = new DatagramPacket(sendName, sendName.length, IPAddress, port);
      clientSocket.send(sendNamePacket);
      DatagramPacket receiveNamePacket = new DatagramPacket(response, response.length);   
      clientSocket.receive(receiveNamePacket);
      System.out.println("receivePacket: " + receiveNamePacket.getAddress().toString() + "\n" + "porta: " + receiveNamePacket.getPort());
      String responseReceived = new String(receiveNamePacket.getData());
      System.out.println(responseReceived); 
      if(responseReceived.contains("Erro")) {
         System.out.println("Usuario / Conexão Invalida.");
         clientSocket.close();
      }

      return clientSocket;
   }
}