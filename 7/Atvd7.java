import java.util.Scanner;

public class Atvd7 {
    public static void main(String []args){
        Scanner ler = new Scanner(System.in);
        float valor;
        System.out.print("Entre com um valor: ");
        valor = ler.nextFloat();
        if(valor > 10){
            System.out.print("É MAIOR QUE 10!!");
        }
        else if (valor < 10){
            System.out.print("É MENOR QUE 10!!");
        }
        else{
            System.out.print("É 10!!");
        }
    }
}
