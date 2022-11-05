import T2Servidor.Mapa;
import T2Servidor.Sala;

public class App {
    public static void main(String[] args) {
        Mapa mapa = new Mapa();
        for(Sala salas : mapa.getSalas()) {
            System.out.println(salas.toString());
        }
    }
}
