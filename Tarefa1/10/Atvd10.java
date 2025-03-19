import java.util.Scanner;

public class Atvd10 {
    public static void main(String []args){
        Scanner ler = new Scanner(System.in);
        float a1, a2, media;
        System.out.print("Entre com a da primeira avaliação: ");
        a1 = ler.nextInt();
        System.out.print("Entre com a da segunda avaliação: ");
        a2 = ler.nextInt();
        media = (a1+a2)/2;

        if(media >= 6){
            System.out.print("Aprovado");
        }
        else{
            System.out.print("Reprovado");
        }
    }
}
