package T2Servidor;

public class Porta {
    Direcoes direcao;
    boolean aberta;
    String sala;

    public Porta(Direcoes direcao, boolean aberta, String sala) {
        this.direcao = direcao;
        this.aberta = aberta;
        this.sala = sala;
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

    public String getSala() {
        return sala;
    }    
    
}
