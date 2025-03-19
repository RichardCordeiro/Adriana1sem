import java.util.Scanner;

public class Atvd9 {
    public static void main(String []args){
        Scanner ler = new Scanner(System.in);
        float m, qtd, total;
        System.out.print("Entre com a quantidade de maças: ");
        qtd = ler.nextInt();
        if(qtd >= 12){
            m = 1;
        }
        else{
            m = 1.30f;
        }
        total = m * qtd;
        System.out.print("O valor total da compra ficou em "+total);
    }
}
