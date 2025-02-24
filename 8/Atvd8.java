import java.util.Scanner;

public class Atvd8 {
    public static void main(String []args){
        Scanner ler = new Scanner(System.in);
        float valor;
        System.out.print("Entre com um valor: ");
        valor = ler.nextFloat();
        if(valor >= 0){
            System.out.print("É Positivo");
        }
        else{
            System.out.print("É Negativo");
        }
    }
}
