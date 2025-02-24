import java.util.Scanner;
public class Atvd11 {
    public static void main(String []args){
        Scanner ler = new Scanner(System.in);
        int atual, nasc;
        System.out.print("Ano atual: ");
        atual = ler.nextInt();
        System.out.print("Ano Nascimento: ");
        nasc = ler.nextInt();
        if(atual-nasc >= 16){
            System.out.print("Pode votar");
        }
        else{
            System.out.print("Não pode votar");
        }
        
    }
}
