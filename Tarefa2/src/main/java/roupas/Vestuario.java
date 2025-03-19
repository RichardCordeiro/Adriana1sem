package roupas;

public abstract class Vestuario {
    private String tamanho;
    private String cor;
    private String material;

    public Vestuario(String tamanho, String cor, String material) {
        this.tamanho = tamanho;
        this.cor = cor;
        this.material = material;
    }

    public void vestir() {
        System.out.println("Vestindo " + this.getClass().getSimpleName().toLowerCase() + " " + cor + " tamanho " + tamanho + " de " + material);
    }

    public void lavar() {
        System.out.println("Lavando " + this.getClass().getSimpleName().toLowerCase() + "...");
    }

    public void secar() {
        System.out.println(this.getClass().getSimpleName().toLowerCase() + " está secando.");
    }
}