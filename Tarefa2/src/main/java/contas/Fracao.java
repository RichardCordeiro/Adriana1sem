package contas;

public class Fracao {
    private int numerador;
    private int denominador;
    private String resultado;


    public Fracao(int numerador, int denominador) {
        if (denominador == 0) {
            resultado = "Erro: O denominador não pode ser zero.";
        } else {
            this.numerador = numerador;
            this.denominador = denominador;
            simplificar();
            resultado = this.paraString();
        }
    }

    private int mdc(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return Math.abs(a);
    }

    private void simplificar() {
        int divisorComum = mdc(numerador, denominador);
        numerador /= divisorComum;
        denominador /= divisorComum;

        if (denominador < 0) {
            numerador = -numerador;
            denominador = -denominador;
        }
    }

    public Fracao somar(Fracao outra) {
        int novoNumerador = this.numerador * outra.denominador + outra.numerador * this.denominador;
        int novoDenominador = this.denominador * outra.denominador;
        return new Fracao(novoNumerador, novoDenominador);
    }

    public Fracao subtrair(Fracao outra) {
        int novoNumerador = this.numerador * outra.denominador - outra.numerador * this.denominador;
        int novoDenominador = this.denominador * outra.denominador;
        return new Fracao(novoNumerador, novoDenominador);
    }

    public Fracao multiplicar(Fracao outra) {
        int novoNumerador = this.numerador * outra.numerador;
        int novoDenominador = this.denominador * outra.denominador;
        return new Fracao(novoNumerador, novoDenominador);
    }

    public Fracao dividir(Fracao outra) {
        if (outra.numerador == 0) {
            return new Fracao(0, 1);
        }
        int novoNumerador = this.numerador * outra.denominador;
        int novoDenominador = this.denominador * outra.numerador;
        return new Fracao(novoNumerador, novoDenominador);
    }

    public String getResultado() {
        return resultado;
    }


    public String paraString() {
        return numerador + "/" + denominador;
    }
}
