package T2Servidor;

import java.util.Arrays;
import java.util.List;


public class AcoesLogica {

    public static PacoteMensagem realizarExaminar(String sentence, Sala sala) {
        PacoteMensagem pacote = new PacoteMensagem();

        List<String> lista = Arrays.asList(sentence.split(" "));
        if(lista.size() < 2) return null;

        pacote.setAcao(Acoes.valueOf(lista.get(0).toUpperCase()));
        
        if(lista.get(1).toLowerCase().contains("sala")) {
            pacote.setSala(sala.id);
            return pacote;
        }

        for(Objetos o : sala.objetos) {
            if(lista.get(1).toUpperCase().contains(o.getObjeto())) {
                pacote.setSala(sala.id);
                pacote.setObjeto(o.getObjeto());
                return pacote;
            }

        }
        pacote.setErro(Erro.EXAMINAR);
        return pacote;
    }

    public static PacoteMensagem realizarMover(String sentence, Sala sala, Jogador jogador) {
        PacoteMensagem pacote = new PacoteMensagem();

        List<String> lista = Arrays.asList(sentence.split(" "));        
        if(lista.size() < 2) return null;
        pacote.setAcao(Acoes.valueOf(lista.get(0).toUpperCase()));
        pacote.setSala(sala.id);
        pacote.setJogador(jogador);
        for(Porta p : sala.getPortas()) {
            if(lista.get(1).toUpperCase().contains(p.getDirecao().getDirecao()) && p.getAberta()) {
                pacote.setDirecao(p.getDirecao().getDirecao());
                return pacote;    
            }
        }
        pacote.setErro(Erro.MOVER);
        System.out.println(pacote.getErro().getMensagemErro());
        return pacote;
    }

    public static PacoteMensagem realizarPegar(String sentence, Sala sala, Jogador jogador) {
        PacoteMensagem pacote = new PacoteMensagem();

        List<String> lista = Arrays.asList(sentence.split(" "));        
        if(lista.size() < 2) return null;

        pacote.setAcao(Acoes.valueOf(lista.get(0).toUpperCase()));
        pacote.setJogador(jogador);
        pacote.setSala(sala.id);
        for(Objetos o : sala.getObjetos()) {
            System.out.println("Objeto desejado: " + lista.get(1).toUpperCase() + " Objeto: " + o.getObjeto());
            if(lista.get(1).toUpperCase().contains(o.getObjeto())) {
                pacote.setObjeto(o.getObjeto());
                return pacote;
            }

        }
        pacote.setErro(Erro.PEGAR);
        return pacote;
    }

    public static PacoteMensagem realizarLargar(String sentence, Sala sala, Jogador jogador) {
        PacoteMensagem pacote = new PacoteMensagem();

        List<String> lista = Arrays.asList(sentence.split(" "));        
        if(lista.size() < 2) return null;

        pacote.setAcao(Acoes.valueOf(lista.get(0).toUpperCase()));
        pacote.setJogador(jogador);
        pacote.setSala(sala.id);
        for(Objetos o : sala.getObjetos()) {
            System.out.println("Objeto largados " + lista.get(1).toUpperCase() + " Objeto: " + o.getObjeto());
            if(lista.get(1).toUpperCase().contains(o.getObjeto())) {
                pacote.setObjeto(o.getObjeto());
                return pacote;
            }

        }
        pacote.setErro(Erro.LARGAR);
        return pacote;
    }

    public static PacoteMensagem realizarInventario(String sentence, Jogador jogador) {
        PacoteMensagem pacote = new PacoteMensagem();
        pacote.setJogador(jogador);
        pacote.setAcao(Acoes.valueOf(sentence));
        return pacote;
    }

    public static PacoteMensagem realizarUsar(String sentence,  Sala sala, Jogador jogador) {
        PacoteMensagem pacote = new PacoteMensagem();

        List<String> lista = Arrays.asList(sentence.split(" "));

        if(lista.size() < 3) return null;

        pacote.setJogador(jogador);
        pacote.setAcao(Acoes.valueOf(lista.get(0).toUpperCase()));

        if(pacote.getJogador().getInventario().contains(Objetos.valueOf(pacote.getObjeto()))) {
            pacote.setObjeto(lista.get(1));
        }    
        if(sala.getObjetos().contains(Objetos.valueOf(lista.get(2))) || sala.getPortas().stream()
                                                                                .filter(x -> x.getDirecao() == Direcoes.valueOf(pacote.getAlvo()))
                                                                                .findFirst().isPresent()) {
            pacote.setAlvo(sentence);
        }
        else{
            pacote.setErro(Erro.USAR);
        }
        
        return pacote;
    }

    
    public static PacoteMensagem realizarFalar(String sentence, Sala sala, Jogador jogador) {
        PacoteMensagem pacote = new PacoteMensagem();

        List<String> lista = Arrays.asList(sentence.split(" "));

        if(lista.size() < 2) return null;

        pacote.setJogador(jogador);
        pacote.setAcao(Acoes.valueOf(lista.get(0).toUpperCase()));
        pacote.setSala(sala.id);
        String mensagem = "";
        
        for(int i = 1; i < lista.size() - 1; i++) {
            mensagem.concat(lista.get(i) + " ");
        }
        
        pacote.setMensagem(mensagem);

        return pacote;
    }
    public static PacoteMensagem realizarCochichar(String sentence) {
        PacoteMensagem pacote = new PacoteMensagem();

        List<String> lista = Arrays.asList(sentence.split(" "));

        if(lista.size() < 2) return null;

        pacote.setAcao(Acoes.valueOf(lista.get(0).toUpperCase()));
        String mensagem = "";
        
        for(int i = 1; i < lista.size() - 1; i++) {
            mensagem.concat(lista.get(i) + " ");
        }
        
        pacote.setMensagem(mensagem);

        return pacote;
    }

    public static PacoteMensagem realizarAjuda(String sentence, Jogador jogador) {
        PacoteMensagem pacote = new PacoteMensagem();

        List<String> lista = Arrays.asList(sentence.split(" "));

        pacote.setAcao(Acoes.valueOf(lista.get(0).toUpperCase()));
        pacote.setJogador(jogador);

        return pacote;
    }
    
    public static PacoteMensagem realizarCriar(String sentence, int count, String ip, String porta) {
        PacoteMensagem pacote = new PacoteMensagem();
        String jogador = Arrays.asList(sentence.split(" ")).get(1);
        pacote.setAcao(Acoes.CRIAR);
        pacote.setSala("0");
        pacote.setJogador(new Jogador(count++, jogador, ip, porta));
        System.out.println("Pacote get sala:" + pacote.getSala());
        return pacote;
    }
}
