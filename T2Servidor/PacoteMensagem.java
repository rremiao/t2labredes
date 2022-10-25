package T2Servidor;

public class PacoteMensagem {
    private Acoes acao;

    private String alvo;
    
    private String objeto;

    private String jogador;

    private String direcao;

    public PacoteMensagem() {}

    public PacoteMensagem(Acoes acao, String alvo, String objeto, 
        String jogador, String direcao) {
            this.acao = acao;
            this.alvo = alvo;
            this.objeto = objeto;
            this.jogador = jogador;
            this.direcao = direcao;
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

    public String getJogador() {
        return this.jogador;
    }

    public void setJogador(String jogador) {
        this.jogador = jogador;
    }

    public String getDirecao() {
        return this.direcao;
    }

    public void setDirecao(String direcao) {
        this.direcao = direcao;
    }

    @Override
    public String toString() {
        return this.getAlvo();
    }
} 
