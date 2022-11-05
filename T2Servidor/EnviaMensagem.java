package T2Servidor;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.List;

public class EnviaMensagem {

    public RespostaMensagem transmiteMensagem(DatagramSocket socket, PacoteMensagem mensagem, List<Jogador> jogadores, List<Sala> salas) throws IOException {
        RespostaMensagem respostaMensagem = null;
        for(Erro e : Erro.values()) {
            if(mensagem.getErro() == e) {
                respostaMensagem = new RespostaMensagem(null, null, e.getMensagemErro());
                socket.send(new DatagramPacket(e.getMensagemErro().getBytes(), e.getMensagemErro().getBytes().length));                
                return respostaMensagem;
            }
        }
        respostaMensagem = new RespostaMensagem(mensagem.getJogador(), salas.get(Integer.valueOf(mensagem.getSala())), null);
        String mensagemServidor = "";
        switch(mensagem.getAcao()) {
            case EXAMINAR: mensagemServidor = examinar(salas.get(Integer.valueOf(mensagem.getSala())), mensagem); break;
            default: break;
        }
        socket.send(new DatagramPacket(mensagemServidor.getBytes(), mensagemServidor.getBytes().length));
        return respostaMensagem;
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

}
