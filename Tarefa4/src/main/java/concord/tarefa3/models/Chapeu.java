package concord.tarefa3.models;

public class Chapeu {
    private int id;
    private String cor;
    private String tamanho;
    private String material;
    private String marca;

    public Chapeu() {
    }

    public Chapeu(String cor, String tamanho, String material, String marca) {
        this.cor = cor;
        this.tamanho = tamanho;
        this.material = material;
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

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
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