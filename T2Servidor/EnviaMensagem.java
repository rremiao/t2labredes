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
                respostaMensagem = new RespostaMensagem(null, null, e.getMensagemErro(), null, null);
                socket.send(new DatagramPacket(e.getMensagemErro().getBytes(), e.getMensagemErro().getBytes().length));                
                return respostaMensagem;
            }
        }
        System.out.println("GET SALA: " + mensagem.getSala());
        String mensagemServidor = "";
        switch(mensagem.getAcao()) {
            case EXAMINAR: mensagemServidor = examinar(salas.get(Integer.valueOf(mensagem.getSala())), mensagem); break;
            case CRIAR: mensagemServidor = "Novo jogador criado"; break;
            default: break;
        }
        respostaMensagem = new RespostaMensagem(mensagem.getJogador(), salas.get(Integer.valueOf(mensagem.getSala())), null, mensagem.getAcao(), mensagemServidor);
        System.out.println("Mensagem: " + mensagemServidor);
        System.out.println("Byte:" + mensagemServidor.getBytes().length);
        sendData = mensagemServidor.getBytes();
        System.out.println("porta:" + port);
        DatagramPacket packet = new DatagramPacket(sendData, sendData.length, ip, port);
        socket.send(packet);
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
