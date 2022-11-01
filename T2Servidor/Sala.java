package T2Servidor;

import java.util.List;

public class Sala {
    String id;
    List<Porta> portas;
    List<Objetos> objetos;
    String ascii;

    

    public Sala(String id, List<Porta> portas, List<Objetos> objetos, String ascii) {
        this.id = id;
        this.portas = portas;
        this.objetos = objetos;
        this.ascii = ascii;
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
    
}
