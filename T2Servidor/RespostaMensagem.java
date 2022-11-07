package T2Servidor;

import java.util.List;

public class RespostaMensagem {
    private Jogador jogador;
    private List<Jogador> jogadores;
    private Sala sala;
    private Sala novaSala;
    private List<Sala> salas;
    private String erro;
    private Acoes acao;
    private String mensagem;
    
    public RespostaMensagem(String erro) {
        this.erro = erro;
    }

    public RespostaMensagem(Jogador jogador, Sala sala, Sala novSala,String erro, Acoes acao, String mensagem, List<Jogador> jogadores, List<Sala> salas) {
        this.jogador = jogador;
        this.sala = sala;
        this.novaSala = novSala;
        this.erro = erro;
        this.acao = acao;
        this.mensagem = mensagem;
        this.jogadores = jogadores;
        this.salas = salas;
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

    public Sala getNovaSala() {
        return novaSala;
    }

    public List<Jogador> getJogadores() {
        return jogadores;
    }

    public List<Sala> getSalas() {
        return salas;
    }    
}
