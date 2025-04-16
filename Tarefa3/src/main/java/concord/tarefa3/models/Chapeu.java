package concord.tarefa3.models;

public class Chapeu {
    private String tipo;
    private String cor;
    private String material;
    private String tamanho;

    public Chapeu(String tipo, String cor, String material, String tamanho) {
        this.tipo = tipo;
        this.cor = cor;
        this.material = material;
        this.tamanho = tamanho;
    }


    public String getTipo() { return tipo; }
    public String getCor() { return cor; }
    public String getMaterial() { return material; }
    public String getTamanho() { return tamanho; }

}