package concord.tarefa3.models;

public class Saia {
    private String cor;
    private String tecido;
    private double comprimento;
    private boolean plissada;

    public Saia(String cor, String tecido, double comprimento, boolean plissada) {
        this.cor = cor;
        this.tecido = tecido;
        this.comprimento = comprimento;
        this.plissada = plissada;
    }

    // Getters para facilitar a formatação
    public String getCor() { return cor; }
    public String getTecido() { return tecido; }
    public double getComprimento() { return comprimento; }
    public boolean isPlissada() { return plissada; }


}
