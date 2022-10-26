package T2Servidor;

public class PacoteMensagem {
    private Acoes acao;

    private String alvo;
    
    private String objeto;

    private Jogador jogador;

    private String direcao;

    private String sala;

    private String mensagem;

    public PacoteMensagem() {}

    public PacoteMensagem(Acoes acao, String alvo, String objeto, 
        Jogador jogador, String direcao, String sala, String mensagem) {
            this.acao = acao;
            this.alvo = alvo;
            this.objeto = objeto;
            this.jogador = jogador;
            this.direcao = direcao;
            this.sala = sala;
            this.mensagem = mensagem;
        }

    public Acoes getAcao() {
        return this.acao;
    }

    public void setAcao(Acoes acao) {
        this.acao = acao;
    }

    public String getAlvo() {
        return this.alvo;
    }

    public void setAlvo(String alvo) {
        this.alvo = alvo;
    }

    public String getObjeto() {
        return this.objeto;
    }

    public void setObjeto(String objeto) {
        this.objeto = objeto;
    }

    public Jogador getJogador() {
        return this.jogador;
    }

    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }

    public String getDirecao() {
        return this.direcao;
    }

    public void setDirecao(String direcao) {
        this.direcao = direcao;
    }

    public String getSala() {
        return this.sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }

    public String getMensagem() { return this.mensagem;}

    public void setMensagem(String mensagem) { this.mensagem = mensagem;}

    @Override
    public String toString() {
        return this.getAlvo();
    }
} 
