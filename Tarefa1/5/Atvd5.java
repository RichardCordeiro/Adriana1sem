import java.util.Scanner;

public class Atvd5 {
    public static void main(String []args){
        Scanner ler = new Scanner(System.in);
        float salario, nCarros, vTotal, vCarro, result;
        System.out.print("entre com salario fixo: ");
        salario = ler.nextFloat();
        System.out.print("entre com o numero de carros vendidos: ");
        nCarros = ler.nextInt();
        System.out.print("entre com o valor por carro: ");
        vCarro = ler.nextInt();

        
        vTotal = nCarros*vCarro;
        System.out.println("Valor total de vendas: " + vTotal);
        System.out.print("Salario pós reajuste ");
        result = salario + vTotal + vTotal*5/100; 
        System.out.println(result);
    }
}
