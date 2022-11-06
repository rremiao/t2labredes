package T2Servidor;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.List;

public class EnviaMensagem {

    public RespostaMensagem transmiteMensagem(DatagramSocket socket, PacoteMensagem mensagem, List<Jogador> jogadores, List<Sala> salas, InetAddress ip, int port) throws IOException {
        RespostaMensagem respostaMensagem = null;
        byte sendData [] = new byte[1024];
        for(Erro e : Erro.values()) {
            if(mensagem.getErro() == e) {
                respostaMensagem = new RespostaMensagem(e.getMensagemErro());
                socket.send(new DatagramPacket(e.getMensagemErro().getBytes(), e.getMensagemErro().getBytes().length, ip, port));                
                return respostaMensagem;
            }
        }
        String mensagemServidor = "";
        Jogador j;
        switch(mensagem.getAcao()) {
            case EXAMINAR: //OK
                mensagemServidor = examinar(salas.get(Integer.valueOf(mensagem.getSala())), mensagem); 
                respostaMensagem = new RespostaMensagem(mensagem.getJogador(), salas.get(Integer.valueOf(mensagem.getSala())), null, null, mensagem.getAcao(), mensagemServidor, jogadores);
                socket.send(new DatagramPacket(mensagemServidor.getBytes(), mensagemServidor.getBytes().length, ip, port));
                return respostaMensagem;                
            case CRIAR: //OK
                mensagemServidor = "Novo jogador criado"; 
                respostaMensagem = new RespostaMensagem(mensagem.getJogador(), salas.get(Integer.valueOf(mensagem.getSala())), null, null, mensagem.getAcao(), mensagemServidor, jogadores);
                socket.send(new DatagramPacket(mensagemServidor.getBytes(), mensagemServidor.getBytes().length, ip, port));
                return respostaMensagem;
            case MOVER: // Precisa atualizar a lista de jogadores das salas
                j = jogadores.stream().filter(x -> x.getPorta().equals(String.valueOf(port))).findFirst().get(); 
                String novaSala = mover(salas.get(Integer.valueOf(mensagem.getSala())), mensagem);
                mensagemServidor = "Movendo para outra sala." + "\n" + examinar(salas.get(Integer.valueOf(novaSala)), mensagem);
                respostaMensagem = new RespostaMensagem(j, salas.get(Integer.valueOf(mensagem.getSala())), salas.get(Integer.valueOf(novaSala)), null, mensagem.getAcao(), mensagemServidor, jogadores);
                socket.send(new DatagramPacket(mensagemServidor.getBytes(), mensagemServidor.getBytes().length, ip, port));
                return respostaMensagem;
            default: 
                return null;
        }

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
