public class Atvd16 {
    public static void main(String []args){
        double j, f, m, media, total;
        
        j = 15000;
        f = 23000;
        m = 17000;
        total = j+f+m;
        media = total/3;
        String mediaFormatada = String.format("%.2f", media);
        System.out.println("O total gasto no trimestre é de: " + total);
        System.out.print("A media por mês é : " + mediaFormatada);
        
        
    }
}