package T2Servidor;

import java.util.List;

public class Porta {
    List<Direcoes> direcoes;
    List<Boolean> abertas;

    

    public Porta(List<Direcoes> direcoes, List<Boolean> abertas) {
        this.direcoes = direcoes;
        this.abertas = abertas;
    }

    public List<Direcoes> getDirecoes() {
        return direcoes;
    }

    public void setDirecoes(List<Direcoes> direcoes) {
        this.direcoes = direcoes;
    }

    public List<Boolean> getAbertas() {
        return abertas;
    }
    
    public void setAbertas(List<Boolean> abertas) {
        this.abertas = abertas;
    }

    
}
