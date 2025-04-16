package concord.tarefa3.models;

public class Vestido {
    private String cor;
    private String tamanho;
    private String tecido;
    private boolean temEstampa;

    public Vestido(String cor, String tamanho, String tecido, boolean temEstampa) {
        this.cor = cor;
        this.tamanho = tamanho;
        this.tecido = tecido;
        this.temEstampa = temEstampa;
    }

    // Getters para acessar os valores
    public String getCor() { return cor; }
    public String getTamanho() { return tamanho; }
    public String getTecido() { return tecido; }
    public boolean isTemEstampa() { return temEstampa; }
}
