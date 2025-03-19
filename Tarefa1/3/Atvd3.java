import java.util.Scanner;

public class Atvd3 {
    public static void main(String []args){
        Scanner ler = new Scanner(System.in);
        float salario, reajuste, result;
        System.out.print("entre com salario atual: ");
        salario = ler.nextFloat();
        System.out.print("entre com o percentual em (%): ");
        reajuste = ler.nextFloat();
        

        System.out.print("Salario pós reajuste ");
        result = salario + salario*(reajuste/100);
        System.out.println(result);
    }
}
