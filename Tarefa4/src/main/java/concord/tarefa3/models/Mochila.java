package concord.tarefa3.models;

public class Mochila {
    private int id;
    private String marca;
    private int litros;
    private String cor;
    private boolean temCompartimentoNotebook;

    public Mochila() {
    }

    public Mochila(String marca, int litros, String cor, boolean temCompartimentoNotebook) {
        this.marca = marca;
        this.litros = litros;
        this.cor = cor;
        this.temCompartimentoNotebook = temCompartimentoNotebook;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getLitros() {
        return litros;
    }

    public void setLitros(int litros) {
        this.litros = litros;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public boolean isTemCompartimentoNotebook() {
        return temCompartimentoNotebook;
    }

    public void setTemCompartimentoNotebook(boolean temCompartimentoNotebook) {
        this.temCompartimentoNotebook = temCompartimentoNotebook;
    }

    @Override
    public String toString() {
        return "Mochila [ID: " + id + ", Marca: " + marca + ", Litros: " + litros + 
               ", Cor: " + cor + ", Compartimento para Notebook: " + 
               (temCompartimentoNotebook ? "Sim" : "Não") + "]";
    }
}
