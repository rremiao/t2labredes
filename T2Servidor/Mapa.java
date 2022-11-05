package T2Servidor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Mapa {
    private List<Sala> salas;
    public Mapa() {
        this.salas = new ArrayList<>();
        this.salas.add(
            new Sala("0",
             Arrays.asList(new Porta(Direcoes.SUL, false, "1")), Arrays.asList(Objetos.CHAVE), null, false
            )
        );
        this.salas.add(
            new Sala("1",
             Arrays.asList(
                new Porta(Direcoes.SUL, false, "1"),
                new Porta(Direcoes.NORTE, false, "0"),
                new Porta(Direcoes.OESTE, false, "2"),
                new Porta(Direcoes.LESTE, false, "3")
                ), Arrays.asList(Objetos.CHAVE), null, false
            )
        );
        this.salas.add(
            new Sala("2",
             Arrays.asList(
                new Porta(Direcoes.LESTE, false, "1")
                ), Arrays.asList(Objetos.CHAVE), null, false
            )
        );
        this.salas.add(
            new Sala("1",
             Arrays.asList(
                new Porta(Direcoes.OESTE, false, "2")
                ), Arrays.asList(Objetos.CHAVE), null, true
            )
        );
        this.salas.add(
            new Sala("4",
             Arrays.asList(
                new Porta(Direcoes.NORTE, false, "0")
                ), Arrays.asList(Objetos.CHAVE), null, false
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
