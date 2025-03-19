import java.util.Scanner;

public class Atvd2 {
    public static void main(String []args){
        Scanner ler = new Scanner(System.in);
        float total, branco, nulos, validos;
        float porcentb, porcentn,porcentv;
        String result;
        
        System.out.print("entre com a quantidade de votos em branco: ");
        branco = ler.nextFloat();
        System.out.print("entre com a quantidade de votos nulos: ");
        nulos = ler.nextFloat();
        System.out.print("entre com a quantidade de votos validos: ");
        validos = ler.nextFloat();
        total = branco + nulos + validos;
        
        System.out.print("percentual branco ");
        porcentb = (branco/total)*100;
        result = String.format("%.2f", porcentb);
        System.out.println(result  + " %");
        System.out.print("percentual nulo ");
        porcentn = (nulos/total)*100;
        result = String.format("%.2f", porcentn);
        System.out.println(result  + " %");
        System.out.print("percentual valido ");
        porcentv = (validos/total)*100;
        result = String.format("%.2f", porcentv);
        System.out.print(result + " %");

    }
}
