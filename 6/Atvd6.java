import java.util.Scanner;

public class Atvd6{

    public static void main(String []args){
       Scanner ler = new Scanner(System.in);
       int c, f; 
       System.out.print("Entre com a temperatura em Fahrenheit: ");
       f = ler.nextInt();
       c = ((f -32) *5)/9;    
       
       System.out.print("Temperatura em Celsius: "+ c);
    }
}