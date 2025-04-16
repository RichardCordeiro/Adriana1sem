package concord.tarefa3.models;

public class Jaqueta {
    private String modelo;
    private String cor;
    private String material;
    private boolean impermeavel;

    public Jaqueta(String modelo, String cor, String material, boolean impermeavel) {
        this.modelo = modelo;
        this.cor = cor;
        this.material = material;
        this.impermeavel = impermeavel;
    }


    public String getModelo() { return modelo; }
    public String getCor() { return cor; }
    public String getMaterial() { return material; }
    public boolean isImpermeavel() { return impermeavel; }

}
