package T2Servidor;

public class Porta {
    Direcoes direcao;
    boolean aberta;

    public Porta(Direcoes direcao, boolean aberta) {
        this.direcao = direcao;
        this.aberta = aberta;
    }

    public Direcoes getDirecao() {
        return direcao;
    }

    public void setDirecao(Direcoes direcao) {
        this.direcao = direcao;
    }

    public boolean getAberta() {
        return aberta;
    }

    public void setAberta(boolean aberta) {
        this.aberta = aberta;
    }    
}
