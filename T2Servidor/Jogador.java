package T2Servidor;

import java.util.ArrayList;
import java.util.List;

public class Jogador {
    private int id;

    private String nome;

    private String ip;

    private String porta;

    private List<Objetos> inventario;   
    
    public Jogador() {}

    public Jogador(int id, String nome, String ip, String porta) {
        this.id = id;
        this.nome = nome;
        this.ip = ip;
        this.porta = porta;
        this.inventario = new ArrayList<>();
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIp() {
        return this.ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPorta() {
        return this.porta;
    }

    public void setPorta(String porta) {
        this.porta = porta;
    }

    public List<Objetos> getInventario() {
        return inventario;
    }

    public void setInventario(List<Objetos> inventario) {
        this.inventario = inventario;
    }
}
