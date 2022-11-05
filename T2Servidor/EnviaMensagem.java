package T2Servidor;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class EnviaMensagem {

    public void transmiteMensagem(DatagramSocket socket, PacoteMensagem mensagem) throws IOException {
        socket.send(new DatagramPacket(mensagem.getMensagem().getBytes(), mensagem.getMensagem().getBytes().length));
    }

}
