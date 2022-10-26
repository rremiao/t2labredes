package T2Servidor;

public enum Objetos {
    CHAVE(1, "Chave");

    private int codigo;

    private String objeto;

    Objetos(int codigo, String objeto) {
        this.codigo = codigo;
        this.objeto = objeto;
    }
    
    public int getCodigo() { return this.codigo;}

    public String getObjeto() { return this.objeto;}
}   
