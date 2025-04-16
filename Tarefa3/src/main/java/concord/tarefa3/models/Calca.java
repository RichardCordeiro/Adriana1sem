package concord.tarefa3.models;

public class Calca {
    private String tipo;
    private String tamanho;
    private String cor;
    private String tecido;

    public Calca(String tipo, String tamanho, String cor, String tecido) {
        this.tipo = tipo;
        this.tamanho = tamanho;
        this.cor = cor;
        this.tecido = tecido;
    }


    public String getTipo() { return tipo; }
    public String getTamanho() { return tamanho; }
    public String getCor() { return cor; }
    public String getTecido() { return tecido; }

}