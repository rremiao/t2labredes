package T2Servidor;

public class RespostaMensagem {
    private Jogador jogador;
    private Sala sala;
    private String erro;
    private Acoes acao;
    private String mensagem;
    
    public RespostaMensagem(Jogador jogador, Sala sala, String erro, Acoes acao, String mensagem) {
        this.jogador = jogador;
        this.sala = sala;
        this.erro = erro;
        this.acao = acao;
        this.mensagem = mensagem;
    }

    public Acoes getAcao() {
        return acao;
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

    public String getMensagem() {
        return mensagem;
    }    
}
