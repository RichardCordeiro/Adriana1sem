package concord.tarefa3.models;

public class Mochila {
    private String marca;
    private int litros;
    private String cor;
    private boolean temCompartimentoNotebook;

    public Mochila(String marca, int litros, String cor, boolean temCompartimentoNotebook) {
        this.marca = marca;
        this.litros = litros;
        this.cor = cor;
        this.temCompartimentoNotebook = temCompartimentoNotebook;
    }

    // Getters para facilitar a formatação
    public String getMarca() { return marca; }
    public int getLitros() { return litros; }
    public String getCor() { return cor; }
    public boolean isTemCompartimentoNotebook() { return temCompartimentoNotebook; }

}
