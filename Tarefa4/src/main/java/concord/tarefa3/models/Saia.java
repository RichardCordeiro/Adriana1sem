package concord.tarefa3.models;

public class Saia {
    private int id;
    private String cor;
    private String tecido;
    private double comprimento;
    private boolean plissada;

    public Saia() {
    }

    public Saia(String cor, String tecido, double comprimento, boolean plissada) {
        this.cor = cor;
        this.tecido = tecido;
        this.comprimento = comprimento;
        this.plissada = plissada;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getTecido() {
        return tecido;
    }

    public void setTecido(String tecido) {
        this.tecido = tecido;
    }

    public double getComprimento() {
        return comprimento;
    }

    public void setComprimento(double comprimento) {
        this.comprimento = comprimento;
    }

    public boolean isPlissada() {
        return plissada;
    }

    public void setPlissada(boolean plissada) {
        this.plissada = plissada;
    }

    @Override
    public String toString() {
        return "Saia [ID: " + id + ", Cor: " + cor + ", Tecido: " + tecido + 
               ", Comprimento: " + comprimento + "cm, Plissada: " + (plissada ? "Sim" : "Não") + "]";
    }
}
