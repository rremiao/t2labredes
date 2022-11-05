package T2Servidor;

public class RespostaMensagem {
    private Jogador jogador;
    private Sala sala;
    private String erro;
    
    public RespostaMensagem(Jogador jogador, Sala sala, String erro) {
        this.jogador = jogador;
        this.sala = sala;
        this.erro = erro;
    }

    public Jogador getJogador() {
        return jogador;
    }

    public Sala getSala() {
        return sala;
    }

    public String getErro() {
        return erro;
    }    
}
