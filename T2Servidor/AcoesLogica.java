package T2Servidor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AcoesLogica {

    public static PacoteMensagem realizaUsar(String sentence) {
        PacoteMensagem pacote = new PacoteMensagem();

        List<String> lista = new ArrayList<>();
        lista = Arrays.asList(sentence.split(" "));

        if(lista.size() < 3) return null;

        pacote.setAcao(Acoes.valueOf(lista.get(0)));
        pacote.setObjeto(lista.get(1));
        pacote.setAlvo(lista.get(2));

        return pacote;
    }
    
}
