package T2Servidor;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
       List<Jogador> listaJogadores = new ArrayList<>();
       int count = 0;
       byte[] receiveData = new byte[1024];
          while(true)
          {
             // declara o pacote a ser recebido
             DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

             // recebe o pacote do cliente
             serverSocket.receive(receivePacket);
            
             // pega os dados, o endereco IP e a porta do cliente
             // para poder mandar a msg de volta
             String sentence = new String(receivePacket.getData());
             InetAddress IPAddress = receivePacket.getAddress();
             int receivePort = receivePacket.getPort();

             System.out.println("Mensagem recebida: " + sentence);
             if(!sentence.trim().equals("FIM")){
             // cria pacote com o dado, o endereco do server e porta do servidor
             DatagramPacket sendPacket = new DatagramPacket(receiveData, receiveData.length, IPAddress, receivePort);
             serverSocket.send(sendPacket);
             }
             else {
                System.out.println("Encerrando o servidor...");
                System.exit(0);
                serverSocket.close();
                break;
             }

             PacoteMensagem pacote = new PacoteMensagem();
             List<String> lista = Arrays.asList(sentence.split(" "));
             switch(Acoes.valueOf(lista.get(0))) {
               case EXAMINAR: pacote = AcoesLogica.realizarExaminar(sentence);
               case MOVER: pacote = AcoesLogica.realizarMover(sentence);
               case PEGAR: pacote = AcoesLogica.realizarPegar(sentence);
               case LARGAR: pacote = AcoesLogica.realizarLargar(sentence);
               case INVENTARIO: pacote = AcoesLogica.realizarInventario(sentence);
               case USAR: pacote = AcoesLogica.realizarUsar(sentence);
               case FALAR: pacote = AcoesLogica.realizarFalar(sentence);
               case COCHICHAR: 
               Jogador jogadorDestinatario = new Jogador();

                           for(Jogador j : listaJogadores) {
                              if(sentence.contains(j.getNome())) {
                                 jogadorDestinatario = j;
                              }
                           }
                           pacote = AcoesLogica.realizarCochichar(sentence); pacote.setJogador(jogadorDestinatario);
               case AJUDA: pacote = AcoesLogica.realizarAjuda(sentence);
               case CRIAR: pacote = AcoesLogica.realizarCriar(sentence, count, IPAddress.getAddress().toString(), String.valueOf(receivePort));
               default: throw new IllegalArgumentException("Comando invalido."); 

             }
          }
    }
}
