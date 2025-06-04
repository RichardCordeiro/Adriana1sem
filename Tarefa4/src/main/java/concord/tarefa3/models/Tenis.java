package concord.tarefa3.models;

public class Tenis {
    private int id;
    private String cor;
    private String tamanho;
    private String material;
    private String marca;
    private boolean esportivo;

    public Tenis() {
    }

    public Tenis(String cor, String tamanho, String material, String marca, boolean esportivo) {
        this.cor = cor;
        this.tamanho = tamanho;
        this.material = material;
        this.marca = marca;
        this.esportivo = esportivo;
    }

    public Tenis(String cor, String tamanho, String marca) {
        this.cor = cor;
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

    public boolean isEsportivo() {
        return esportivo;
    }

    public void setEsportivo(boolean esportivo) {
        this.esportivo = esportivo;
    }

    @Override
    public String toString() {
        return "Tênis [ID: " + id + ", Cor: " + cor + ", Tamanho: " + tamanho + 
               ", Material: " + material + ", Marca: " + marca + 
               ", Esportivo: " + (esportivo ? "Sim" : "Não") + "]";
    }
}
