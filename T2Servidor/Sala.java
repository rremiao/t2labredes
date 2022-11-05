package T2Servidor;

import java.util.ArrayList;
import java.util.List;

public class Sala {
    String id;
    List<Porta> portas;
    List<Objetos> objetos;
    String ascii;
    List<String> jogadores;
    boolean saida;

    public Sala(String id, List<Porta> portas, List<Objetos> objetos, String ascii, boolean saida) {
        this.id = id;
        this.portas = portas;
        this.objetos = objetos;
        this.ascii = ascii;
        this.jogadores = new ArrayList<>();
        this.saida = saida;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public List<Porta> getPortas() {
        return portas;
    }
    
    public void setPortas(List<Porta> portas) {
        this.portas = portas;
    }
    
    public List<Objetos> getObjetos() {
        return objetos;
    }
    
    public void setObjetos(List<Objetos> objetos) {
        this.objetos = objetos;
    }
    
    public String getAscii() {
        return ascii;
    }
    
    public void setAscii(String ascii) {
        this.ascii = ascii;
    }

    public void adicionaJogador(String jogador) {
        this.jogadores.add(jogador);
    }
    
    public void removeJogador(String jogador) {
        this.jogadores.remove(jogador);
    }

    public void removeObjeto(Objetos objeto) {
        this.objetos.remove(objeto);
    }

    public void adicionaObjeto(Objetos objeto) {
        this.objetos.add(objeto);
    }

    public List<String> getJogadores() {
        return jogadores;
    }

    public void setJogadores(List<String> jogadores) {
        this.jogadores = jogadores;
    }

    public boolean isSaida() {
        return saida;
    }

    public void setSaida(boolean saida) {
        this.saida = saida;
    }

    @Override
    public String toString() {
        return "Sala [ \n"+"id=" + id +"\n"+ "portas=" + portas +"\n" + "objetos=" + objetos + "\n"+"saida=" + saida + "\n"+"]";
    }

    
}
