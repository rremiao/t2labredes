package T2Servidor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Mapa {
    private List<Sala> salas;
    private List<Objetos> objetos0;
    private List<Objetos> objetos1;
    private List<Objetos> objetos2;
    private List<Objetos> objetos3;
    private List<Objetos> objetos4;
    public Mapa() {
        this.salas = new ArrayList<>();
        this.objetos0 = new ArrayList<>();
        this.objetos0.add(Objetos.CHAVE);
        this.objetos1 = new ArrayList<>();
        this.objetos1.add(Objetos.CHAVE);
        this.objetos2 = new ArrayList<>();
        this.objetos2.add(Objetos.CHAVE);
        this.objetos3 = new ArrayList<>();
        this.objetos3.add(Objetos.CHAVE);
        this.objetos4 = new ArrayList<>();
        this.objetos4.add(Objetos.CHAVE);
        this.salas.add(
            new Sala("0",
             Arrays.asList(new Porta(Direcoes.SUL, true, "1")), 
             this.objetos0, null, false
            )
        );
        this.salas.add(
            new Sala("1",
             Arrays.asList(
                new Porta(Direcoes.SUL, false, "1"),
                new Porta(Direcoes.NORTE, true, "0"),
                new Porta(Direcoes.OESTE, false, "2"),
                new Porta(Direcoes.LESTE, false, "3")
                ), 
                this.objetos1,
                null, false
            )
        );
        this.salas.add(
            new Sala("2",
             Arrays.asList(
                new Porta(Direcoes.LESTE, false, "1")
                ), 
                this.objetos2, null, false
            )
        );
        this.salas.add(
            new Sala("1",
             Arrays.asList(
                new Porta(Direcoes.OESTE, false, "2")
                ), 
                this.objetos3, null, true
            )
        );
        this.salas.add(
            new Sala("4",
             Arrays.asList(
                new Porta(Direcoes.NORTE, false, "0")
                ), 
                this.objetos4, null, false
            )
        );
    }
    

    @Override
    public String toString() {
        return "Mapa [salas=" + salas + "]";
    }


    public List<Sala> getSalas() {
        return salas;
    }

    

}
