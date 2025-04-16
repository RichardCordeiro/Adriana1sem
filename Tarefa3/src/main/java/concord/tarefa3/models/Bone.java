package concord.tarefa3.models;

public class Bone {
    private String cor;
    private String estilo;
    private String tamanho;
    private String marca;

    public Bone(String cor, String estilo, String tamanho, String marca) {
        this.cor = cor;
        this.estilo = estilo;
        this.tamanho = tamanho;
        this.marca = marca;
    }



    public String getCor() {
        return cor;
    }

    public String getEstilo() {
        return estilo;
    }

    public String getTamanho() {
        return tamanho;
    }

    public String getMarca() {
        return marca;
    }

}
