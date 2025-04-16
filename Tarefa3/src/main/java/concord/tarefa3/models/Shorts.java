package concord.tarefa3.models;

public class Shorts {
    private String tecido;
    private String cor;
    private String tamanho;
    private boolean bolsos;

    public Shorts(String tecido, String cor, String tamanho, boolean bolsos) {
        this.tecido = tecido;
        this.cor = cor;
        this.tamanho = tamanho;
        this.bolsos = bolsos;
    }

    // Getters para facilitar o acesso aos valores
    public String getTecido() { return tecido; }
    public String getCor() { return cor; }
    public String getTamanho() { return tamanho; }
    public boolean isBolsos() { return bolsos; }


}
