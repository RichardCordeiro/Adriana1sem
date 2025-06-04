package concord.tarefa3.models;

public class Bone {
    private int id;
    private String cor;
    private String estilo;
    private String tamanho;
    private String marca;

    public Bone() {
    }

    public Bone(String cor, String estilo, String tamanho, String marca) {
        this.cor = cor;
        this.estilo = estilo;
        this.tamanho = tamanho;
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

    public String getEstilo() {
        return estilo;
    }

    public void setEstilo(String estilo) {
        this.estilo = estilo;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    @Override
    public String toString() {
        return cor + " - " + estilo + " - " + marca;
    }
}
