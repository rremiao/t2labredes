package T2Servidor;

public enum Acoes {
    EXAMINAR(1, "Examinar", "Permite você examinar objetos ou a sala em que está"),
    MOVER(2, "Mover", "Permite você se mover de uma sala para a outra"),
    PEGAR(3, "Pegar", "Permite você pegar um objeto da sala"),
    LARGAR(4, "Largar", "Permite você largar um objeto na sala"),
    INVENTARIO(5, "Inventario", "Permite você listar seus itens no inventário"),
    USAR(6, "Usar", "Permite você usar um item"),
    FALAR(7, "Falar", "Permite você falar com os jogadores da sala"),
    COCHICHAR(8, "Cochichar", "Permite você cochichar com um único jogador da sala"),
    AJUDA(9, "Ajuda", "Lista os comandos da aplicação"),
    CRIAR(10, "Criar", "Cria novo jogador");
    
    private final int codigo;
    
    private final String acao;

    private final String explicacao;

    Acoes(int codigo, String acao, String explicacao) {
        this.codigo = codigo;
        this.acao = acao;
        this.explicacao = explicacao;
    }

    
    public int getCodigo() { return this.codigo;}
    
    public String getAcao() { return this.acao;}

    public String getExplicacao() { return this.explicacao;}
}
