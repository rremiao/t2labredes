package T2Servidor;

public enum Acoes {
    EXAMINAR(1, "Examinar"),
    MOVER(2, "Mover"),
    PEGAR(3, "Pegar"),
    LARGAR(4, "Largar"),
    INVENTARIO(5, "Inventario"),
    USAR(6, "Usar"),
    FALAR(7, "Falar"),
    COCHICHAR(8, "Cochichar"),
    AJUDA(9, "Ajuda");
    
    private final int codigo;
    
    private final String acao;

    Acoes(int codigo, String acao) {
        this.codigo = codigo;
        this.acao = acao;
    }

    
    public int getCodigo() { return this.codigo;}
    
    
    public String getAcao() { return this.acao;}
}
