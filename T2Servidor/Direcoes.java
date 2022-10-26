package T2Servidor;

public enum Direcoes {
    SUL(1, "S"),
    NORTE(2,"N"),
    LESTE(3,"L"),
    OESTE(4,"O");

    private int codigo;
    private String direcao;

    Direcoes(int codigo, String direcao) {
        this.codigo = codigo;
        this.direcao = direcao;
    }

    public int getCodigo() { return this.codigo;}
    public String getDirecao() { return this.direcao;}
}
