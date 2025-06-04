package concord.tarefa3.models;

public class Vestido {
    private int id;
    private String cor;
    private String tamanho;
    private String tecido;
    private String marca;
    public Vestido() {
    }

    public Vestido(String cor, String tamanho, String tecido, String marca) {
        this.cor = cor;
        this.tamanho = tamanho;
        this.tecido = tecido;
        this.marca = marca;
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

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public String getTecido() {
        return tecido;
    }

    public void setTecido(String tecido) {
        this.tecido = tecido;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    @Override
    public String toString() {
        return cor + " - " + tamanho + " - " + marca;
    }
}
