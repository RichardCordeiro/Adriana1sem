import java.util.Scanner;

public class Atvd1{

    public static void main(String []args){
       Scanner ler = new Scanner(System.in);
       int idade, ano, mes, dias; 
       System.out.println("Entre com a sua idade expressa em anos, meses e dias");
       System.out.print("anos: ");
       ano = ler.nextInt();
       System.out.print("meses: ");
       mes = ler.nextInt(); 
       System.out.print("dias: ");
       dias = ler.nextInt();
    
       

       idade = ano * 365 + mes * 30 + dias;
       System.out.print(idade);
    }
}