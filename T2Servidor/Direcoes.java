package T2Servidor;

public enum Direcoes {
    SUL(1, "SUL"),
    NORTE(2,"NORTE"),
    LESTE(3,"LESTE"),
    OESTE(4,"OESTE");

    private int codigo;
    private String direcao;

    Direcoes(int codigo, String direcao) {
        this.codigo = codigo;
        this.direcao = direcao;
    }

    public int getCodigo() { return this.codigo;}
    public String getDirecao() { return this.direcao;}
}
