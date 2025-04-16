package concord.tarefa3.models;

public class Camiseta {
    private String cor;
    private String tamanho;
    private String tecido;
    private String marca;

    public Camiseta(String cor, String tamanho, String tecido, String marca) {
        this.cor = cor;
        this.tamanho = tamanho;
        this.tecido = tecido;
        this.marca = marca;
    }


    public String getCor() { return cor; }
    public String getTamanho() { return tamanho; }
    public String getTecido() { return tecido; }
    public String getMarca() { return marca; }

}
