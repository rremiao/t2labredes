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
       ConstroiMensagem constroiMensagem = new ConstroiMensagem();
       System.out.println(mapa.getSalas().get(0).toString());
       byte[] receiveData = new byte[1024];
       byte[] sendData = new byte[1024];
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

             PacoteMensagem pacote = new PacoteMensagem();
             List<String> lista = Arrays.asList(sentence.split(" "));
            //  System.out.println("Porta: " + receivePort);
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
                  pacote = AcoesLogica.realizarMover(sentence, s, j); 
                  break;
               case PEGAR: 
                  j = listaJogadores.stream().filter(x -> x.getPorta().equals(String.valueOf(receivePort))).findFirst().get();
                  s = mapa.getSalas().stream().filter(x -> x.getJogadores().contains(j.getNome())).findFirst().get();
                  pacote = AcoesLogica.realizarPegar(sentence, s, j); 
                  break;
               case LARGAR: 
                  j = listaJogadores.stream().filter(x -> x.getPorta().equals(String.valueOf(receivePort))).findFirst().get();
                  s = mapa.getSalas().stream().filter(x -> x.getJogadores().contains(j.getNome())).findFirst().get();
                  pacote = AcoesLogica.realizarLargar(sentence, s, j); 
                  break;
               case INVENTARIO: 
                  j = listaJogadores.stream().filter(x -> x.getPorta().equals(String.valueOf(receivePort))).findFirst().get();
                  pacote = AcoesLogica.realizarInventario(sentence, j); 
                  break;
               case USAR: 
                  j = listaJogadores.stream().filter(x -> x.getPorta().equals(String.valueOf(receivePort))).findFirst().get();
                  s = mapa.getSalas().stream().filter(x -> x.getJogadores().contains(j.getNome())).findFirst().get();
                  pacote = AcoesLogica.realizarUsar(sentence, s, j); 
                  break;
               case FALAR: 
                  j = listaJogadores.stream().filter(x -> x.getPorta().equals(String.valueOf(receivePort))).findFirst().get();
                  s = mapa.getSalas().stream().filter(x -> x.getJogadores().contains(j.getNome())).findFirst().get();
                  pacote = AcoesLogica.realizarFalar(sentence, s, j); 
                  break;
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
               case AJUDA: 
                  j = listaJogadores.stream().filter(x -> x.getPorta().equals(String.valueOf(receivePort))).findFirst().get();
                  pacote = AcoesLogica.realizarAjuda(sentence, j); 
                  break;
               case CRIAR: 
                  pacote = AcoesLogica.realizarCriar(sentence, count, IPAddress.getAddress().toString(), String.valueOf(receivePort)); 
                  break;
               default: throw new IllegalArgumentException("Comando invalido.");
             }
             //Construindo e transmitindo a resposta
             respostaMensagem = constroiMensagem.constroiMensagem(serverSocket, pacote, listaJogadores, mapa.getSalas(), IPAddress, receivePort); 
             switch(respostaMensagem.getAcao()) {
               case EXAMINAR:
                  sendData = respostaMensagem.getMensagem().getBytes();
                  System.out.println("Tamanho mensagem enviada: " + sendData.length);
                  serverSocket.send(new DatagramPacket(respostaMensagem.getMensagem().getBytes(), respostaMensagem.getMensagem().getBytes().length, IPAddress, receivePort));
                  break;
               case CRIAR:
                  //Não está funcionando esse if
                  if(respostaMensagem.getErro() != null) {} 
                  listaJogadores = respostaMensagem.getJogadores();
                  mapa.getSalas().set(Integer.valueOf(respostaMensagem.getSala().id), respostaMensagem.getSala());
                  serverSocket.send(new DatagramPacket(respostaMensagem.getMensagem().getBytes(), respostaMensagem.getMensagem().getBytes().length, IPAddress, receivePort));   
                  break;
               case MOVER:
                  mapa.getSalas().set(Integer.valueOf(respostaMensagem.getSala().id), respostaMensagem.getSala());
                  mapa.getSalas().set(Integer.valueOf(respostaMensagem.getNovaSala().id), respostaMensagem.getNovaSala());
                  serverSocket.send(new DatagramPacket(respostaMensagem.getMensagem().getBytes(), respostaMensagem.getMensagem().getBytes().length, IPAddress, receivePort));
                  break;
               case PEGAR:
                  mapa.getSalas().set(Integer.valueOf(respostaMensagem.getSala().id), respostaMensagem.getSala());
                  listaJogadores.set(respostaMensagem.getJogador().getId(), respostaMensagem.getJogador());
                  List<Jogador> jogadorSala = new ArrayList<>();
                  Sala sala = mapa.getSalas().get(Integer.valueOf(respostaMensagem.getSala().id));
                  for(String jogadorNome : sala.getJogadores()) {
                     jogadorSala.add(listaJogadores.stream().filter(x -> x.getNome().equals(jogadorNome)).findFirst().get());
                  }
                  for(Jogador jogadorPorta: jogadorSala) {
                     System.out.println("Mensagem Pegar: " + respostaMensagem.getMensagem());
                     serverSocket.send(new DatagramPacket(respostaMensagem.getMensagem().getBytes(), respostaMensagem.getMensagem().getBytes().length, IPAddress, Integer.valueOf(jogadorPorta.getPorta())));
                  }
                  break;
               case LARGAR:
                  mapa.getSalas().set(Integer.valueOf(respostaMensagem.getSala().id), respostaMensagem.getSala());
                  listaJogadores.set(respostaMensagem.getJogador().getId(), respostaMensagem.getJogador());
                  List<Jogador> jogadoresNaSala = new ArrayList<>();
                  Sala salas = mapa.getSalas().get(Integer.valueOf(respostaMensagem.getSala().id));
                  for(String jogadorNome : salas.getJogadores()) {
                     jogadoresNaSala.add(listaJogadores.stream().filter(x -> x.getNome().equals(jogadorNome)).findFirst().get());
                  }
                  for(Jogador jogadorPorta: jogadoresNaSala) {
                     System.out.println("Mensagem Largar: " + respostaMensagem.getMensagem());
                     serverSocket.send(new DatagramPacket(respostaMensagem.getMensagem().getBytes(), respostaMensagem.getMensagem().getBytes().length, IPAddress, Integer.valueOf(jogadorPorta.getPorta())));
                  }
                  break;
               case INVENTARIO:
                  serverSocket.send(new DatagramPacket(respostaMensagem.getMensagem().getBytes(), respostaMensagem.getMensagem().getBytes().length, IPAddress, Integer.valueOf(pacote.getJogador().getPorta())));
                  break;
               case AJUDA: 
                  serverSocket.send(new DatagramPacket(respostaMensagem.getMensagem().getBytes(), respostaMensagem.getMensagem().getBytes().length, IPAddress, Integer.valueOf(pacote.getJogador().getPorta())));
                  break;
               case FALAR:
                  // mapa.getSalas().set(Integer.valueOf(respostaMensagem.getSala().id), respostaMensagem.getSala());
                  // listaJogadores.set(respostaMensagem.getJogador().getId(), respostaMensagem.getJogador());
                  List<Jogador> jogadoresFala = new ArrayList<>();
                  Sala salasFala = mapa.getSalas().get(Integer.valueOf(respostaMensagem.getSala().id));
                  for(String jogadorNome : salasFala.getJogadores()) {
                     jogadoresFala.add(listaJogadores.stream().filter(x -> x.getNome().equals(jogadorNome)).findFirst().get());
                  }
                  for(Jogador jogadorPorta: jogadoresFala) {
                     System.out.println("Mensagem Falar: " + respostaMensagem.getMensagem());
                     serverSocket.send(new DatagramPacket(respostaMensagem.getMensagem().getBytes(), respostaMensagem.getMensagem().getBytes().length, IPAddress, Integer.valueOf(jogadorPorta.getPorta())));
                  }
                  break;
               case COCHICHAR:
                  serverSocket.send(new DatagramPacket(respostaMensagem.getMensagem().getBytes(), respostaMensagem.getMensagem().getBytes().length, IPAddress, Integer.valueOf(pacote.getJogador().getPorta())));
                  break;
               case USAR: 
                  mapa.getSalas().set(Integer.valueOf(respostaMensagem.getSala().id), respostaMensagem.getSala());
                  // mapa.getSalas().set(Integer.valueOf(respostaMensagem.getNovaSala().id), respostaMensagem.getNovaSala());
                  listaJogadores.set(respostaMensagem.getJogador().getId(), respostaMensagem.getJogador());
                  List<Jogador> jogadoresUsar = new ArrayList<>();
                  Sala salasUsar = mapa.getSalas().get(Integer.valueOf(respostaMensagem.getSala().id));
                  for(String jogadorNome : salasUsar.getJogadores()) {
                     jogadoresUsar.add(listaJogadores.stream().filter(x -> x.getNome().equals(jogadorNome)).findFirst().get());
                  }
                  for(Jogador jogadorPorta: jogadoresUsar) {
                     System.out.println("Mensagem Usar: " + respostaMensagem.getMensagem());
                     serverSocket.send(new DatagramPacket(respostaMensagem.getMensagem().getBytes(), respostaMensagem.getMensagem().getBytes().length, IPAddress, Integer.valueOf(jogadorPorta.getPorta())));
                  }
                  break;
               default: break;   
             }
            //  System.out.println(mapa.getSalas().get(0).toString());
             receiveData = new byte[1024];
             sendData = new byte[1024];
          }
    }
}
