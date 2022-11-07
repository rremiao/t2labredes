package T2Servidor;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.List;

public class ConstroiMensagem {

    public RespostaMensagem constroiMensagem(DatagramSocket socket, PacoteMensagem mensagem, List<Jogador> jogadores, List<Sala> salas, InetAddress ip, int port) throws IOException {
        RespostaMensagem respostaMensagem = null;
        for(Erro e : Erro.values()) {
            if(mensagem.getErro() == e) {
                respostaMensagem = new RespostaMensagem(e.getMensagemErro());
                socket.send(new DatagramPacket(e.getMensagemErro().getBytes(), e.getMensagemErro().getBytes().length, ip, port));                
                return respostaMensagem;
            }
        }
        switch(mensagem.getAcao()) {
            case EXAMINAR: //BUG no print              
                return examinar(mensagem, jogadores, salas, ip, port);
            case CRIAR: //OK
                return criar(mensagem, jogadores, salas, ip, port);
            case MOVER: // OK
                return mover(mensagem, jogadores, salas, ip, port);
            case PEGAR:
                return pegar(mensagem, jogadores, salas, ip, port);
            default: 
                return null;
        }

    }

    public RespostaMensagem examinar(PacoteMensagem pacote, List<Jogador> jogadores, List<Sala> salas, InetAddress ip, int port) {
        RespostaMensagem resposta = null;
        System.out.println("Salas: " + salas.toString());
        String mensagem = examinar(salas.get(Integer.valueOf(pacote.getSala())), pacote);
        resposta = new RespostaMensagem(pacote.getJogador(), salas.get(Integer.valueOf(pacote.getSala())), null, null, pacote.getAcao(), mensagem, jogadores, null) ;
        return resposta;
    }

    public RespostaMensagem criar(PacoteMensagem pacote, List<Jogador> jogadores, List<Sala> salas, InetAddress ip, int port) {
        String mensagem = "Novo jogador criado";
        jogadores.add(pacote.getJogador());
        Sala sala =  salas.get(Integer.parseInt(pacote.getSala()));
        sala.jogadores.add(pacote.getJogador().getNome());
        // salas.set(Integer.valueOf(pacote.getSala()), sala);
        RespostaMensagem resposta = new RespostaMensagem(pacote.getJogador(), sala, null, null, pacote.getAcao(), mensagem, jogadores, null);
        return resposta;
    }

    public RespostaMensagem mover(PacoteMensagem pacote, List<Jogador> jogadores, List<Sala> salas, InetAddress ip, int port) {
        Jogador jogador = jogadores.stream().filter(x -> x.getPorta().equals(String.valueOf(port))).findFirst().get(); 
        
        //Sala antiga
        salas.get(Integer.parseInt(pacote.getSala())).getJogadores().remove(pacote.getJogador().getNome());
        Sala antiga = salas.get(Integer.parseInt(pacote.getSala()));
        //Sala nova
        String novaSala = mover(salas.get(Integer.valueOf(pacote.getSala())), pacote); 
        salas.get(Integer.parseInt(novaSala)).getJogadores().add(pacote.getJogador().getNome());
        String mensagem = "Movendo para outra sala." + "\n" + examinar(salas.get(Integer.valueOf(novaSala)), pacote);
        Sala nova = salas.get(Integer.parseInt(novaSala));
        RespostaMensagem resposta = new RespostaMensagem(jogador, antiga, nova, null, pacote.getAcao(), mensagem, jogadores, null);
        return resposta;
    }

    public RespostaMensagem pegar(PacoteMensagem pacote, List<Jogador> jogadores, List<Sala> salas, InetAddress ip, int port) {
        String mensagem = pacote.getJogador().getNome() + " pegou o objeto: " + pacote.getObjeto();
        salas.get(Integer.valueOf(pacote.getSala())).getObjetos().remove(Objetos.valueOf(pacote.getObjeto()));
        pacote.getJogador().getInventario().add(Objetos.valueOf(pacote.getObjeto()));
        RespostaMensagem resposta = new RespostaMensagem(pacote.getJogador(), salas.get(Integer.valueOf(pacote.getSala())), null, null, pacote.getAcao(), mensagem, jogadores, salas);
        return resposta;
    }

    public String examinar(Sala sala, PacoteMensagem pacote) {
        String resposta = " ";
        if(pacote.getObjeto() != null) {
            resposta = sala.getObjetos().stream()
                .filter(x -> x.getObjeto().equals(pacote.getObjeto()))
                .findFirst().get().getDescricao();
            return resposta;
        }
        resposta = "Sala:" + sala.id + "\n";
        resposta += "Objetos: " + "\n";
        for(Objetos o : sala.objetos) {
            resposta += o.name() + ": " + o.getDescricao() + "\n";
        }
        resposta += "Jogadores: " + "\n";
        for(String jogador : sala.jogadores) {
            resposta += jogador;
        }
        return resposta;
    }

    public String mover(Sala sala, PacoteMensagem pacote)  {
        String resposta = sala.getPortas().stream()
            .filter(x -> x.direcao.getDirecao().equals(pacote.getDirecao()))
            .findFirst()
            .get()
            .sala;
        
        return resposta;
    }

}
