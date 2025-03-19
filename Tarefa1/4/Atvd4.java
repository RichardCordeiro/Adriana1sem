import java.util.Scanner;

public class Atvd4 {
    public static void main(String []args){
        Scanner ler = new Scanner(System.in);
        float custof, percentd, percenti, result;
        percentd = 28;
        percenti = 45;
        System.out.print("entre com o custo de fabricacao: ");
        custof = ler.nextFloat();
        

        System.out.print("Salario pós reajuste ");
        result = custof + custof*(percentd/100) + custof*(percenti/100);
        System.out.println(result);
    }
}
