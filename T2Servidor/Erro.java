package T2Servidor;

public enum Erro {
    EXAMINAR(1, "Não é possivel examinar esse objeto / sala"),
    MOVER(2, "Não é possivel mover nesta direção"),
    PEGAR(3, "Não é possivel pegar este objeto"),
    LARGAR(4, "Não é possivel largar este objeto"),
    USAR(5, "Não é possivel usar este item desta forma"),
    COCHICHAR(6, "Não é possivel cochichar para este jogador");

    private final int codigo;
    private final String mensagemErro;

    Erro(int codigo, String mensagemErro) {
        this.codigo = codigo;
        this.mensagemErro = mensagemErro;
    }

    public int getCodigo() { return this.codigo;}

    public String getMensagemErro() { return this.mensagemErro;}
}