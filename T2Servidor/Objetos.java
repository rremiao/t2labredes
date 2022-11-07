package T2Servidor;

public enum Objetos {
    CHAVE(1, "CHAVE", "chave que pode ser usado em portas");

    private int codigo;

    private String objeto;

    private String descricao;

    Objetos(int codigo, String objeto, String descricao) {
        this.codigo = codigo;
        this.objeto = objeto;
        this.descricao = descricao;
    }
    
    public int getCodigo() { return this.codigo;}

    public String getObjeto() { return this.objeto;}

    public String getDescricao() { return this.descricao;}

    
}   
