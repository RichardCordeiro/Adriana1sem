package concord.tarefa3.models;

public class Tenis {
    private String marca;
    private int tamanho;
    private String cor;
    private boolean esportivo;

    public Tenis(String marca, int tamanho, String cor, boolean esportivo) {
        this.marca = marca;
        this.tamanho = tamanho;
        this.cor = cor;
        this.esportivo = esportivo;
    }

    // Getters para facilitar o acesso aos valores
    public String getMarca() { return marca; }
    public int getTamanho() { return tamanho; }
    public String getCor() { return cor; }
    public boolean isEsportivo() { return esportivo; }


}
