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
       Mapa mapa = new Mapa();
       List<Jogador> listaJogadores = new ArrayList<>();
       int count = 0;
       EnviaMensagem enviaMensagem = new EnviaMensagem();
       System.out.println(mapa.getSalas().get(0).toString());
       byte[] receiveData = new byte[1024];
          while(true)
          {
            RespostaMensagem respostaMensagem = null;
             // declara o pacote a ser recebido
             DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

             // recebe o pacote do cliente
             serverSocket.receive(receivePacket);
            
             // pega os dados, o endereco IP e a porta do cliente
             // para poder mandar a msg de volta
             String sentence = new String(receivePacket.getData());
             InetAddress IPAddress = receivePacket.getAddress();
             int receivePort = receivePacket.getPort();
            //  if(!sentence.trim().equals("FIM")){
            //    // cria pacote com o dado, o endereco do server e porta do servidor
            //    DatagramPacket sendPacket = new DatagramPacket(receiveData, receiveData.length, IPAddress, receivePort);
            //    serverSocket.send(sendPacket);
            //  }
            //  else {
            //     System.out.println("Encerrando o servidor...");
            //     System.exit(0);
            //     serverSocket.close();
            //     break;
            //  }

             PacoteMensagem pacote = new PacoteMensagem();
             List<String> lista = Arrays.asList(sentence.split(" "));
             System.out.println("Examinar: " + lista.get(0).toUpperCase());
             System.out.println("Direcao: " + lista.get(1).toUpperCase());
             Jogador j;
             Sala s;
            //  System.out.println("Case: " + Acoes.valueOf(lista.get(0)));
             switch(Acoes.valueOf(lista.get(0).toUpperCase())) {
               case EXAMINAR: 
                  j = listaJogadores.stream().filter(x -> x.getPorta().equals(String.valueOf(receivePort))).findFirst().get();
                  s = mapa.getSalas().stream().filter(x -> x.getJogadores().contains(j.getNome())).findFirst().get();
                  pacote = AcoesLogica.realizarExaminar(sentence, s); 
                  break;
               case MOVER: 
                  j = listaJogadores.stream().filter(x -> x.getPorta().equals(String.valueOf(receivePort))).findFirst().get();
                  s = mapa.getSalas().stream().filter(x -> x.getJogadores().contains(j.getNome())).findFirst().get();
                  pacote = AcoesLogica.realizarMover(sentence, s); 
                  break;
               case PEGAR: pacote = AcoesLogica.realizarPegar(sentence); break;
               case LARGAR: pacote = AcoesLogica.realizarLargar(sentence); break;
               case INVENTARIO: pacote = AcoesLogica.realizarInventario(sentence); break;
               case USAR: pacote = AcoesLogica.realizarUsar(sentence); break;
               case FALAR: pacote = AcoesLogica.realizarFalar(sentence); break;
               case COCHICHAR: 
               Jogador jogadorDestinatario = new Jogador();

                  for(Jogador jogador : listaJogadores) {
                     if(sentence.contains(jogador.getNome())) {
                        jogadorDestinatario = jogador;
                     }
                  }
                  if(jogadorDestinatario.getNome() != null) {
                     pacote = AcoesLogica.realizarCochichar(sentence); 
                     pacote.setJogador(jogadorDestinatario); 
                  } else {
                     pacote.setErro(Erro.COCHICHAR);
                  }
                  break;
               case AJUDA: pacote = AcoesLogica.realizarAjuda(sentence); break;
               case CRIAR: pacote = AcoesLogica.realizarCriar(sentence, count, IPAddress.getAddress().toString(), String.valueOf(receivePort)); break;
               default: throw new IllegalArgumentException("Comando invalido.");
             }
             respostaMensagem = enviaMensagem.transmiteMensagem(serverSocket, pacote, listaJogadores, mapa.getSalas(), IPAddress, receivePort); 
             switch(respostaMensagem.getAcao()) {
               case CRIAR:
                  //Não está funcionando esse if
                  if(respostaMensagem.getErro() != null) {} 
                  Jogador jogador = new Jogador(count++, lista.get(lista.size() -1), IPAddress.getAddress().toString(), String.valueOf(receivePort));
                  listaJogadores.add(jogador);
                  mapa.getSalas().get(Integer.parseInt(respostaMensagem.getSala().id)).jogadores.add(jogador.getNome());
                  break;
               case MOVER:
                  mapa.getSalas().get(Integer.parseInt(respostaMensagem.getSala().id)).getJogadores().remove(respostaMensagem.getJogador().getNome());
                  mapa.getSalas().get(Integer.parseInt(respostaMensagem.getNovaSala().id)).getJogadores().add(respostaMensagem.getJogador().getNome());
                  break;
               default: break;   
             }
             System.out.println(mapa.getSalas().get(0).toString());
             receiveData = new byte[1024];
          }
    }
}
