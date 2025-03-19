// import java.util.Scanner;

// import roupas.Camiseta;
// import roupas.Bone;
// import roupas.Shorts;
// import roupas.Vestido;
// import roupas.Vestuario;
import contas.Fracao;

public class TestaClasse { 
    public static void main(String[] args) {
        /*Scanner scanner = new Scanner(System.in);
        
        Camiseta minhaCamiseta = new Camiseta("M", "azul", "algodão");
        Bone meuBone = new Bone("GG", "Preto", "poliéster");
        Shorts meuShorts = new Shorts("G", "azul", "jeans");
        Vestido meuVestido = new Vestido("P", "vermelho", "Seda");

        int opcaoVestuario;
        int opcaoAcao;

        do {
            System.out.println("\nO que deseja interagir?");
            System.out.println("1 - Camiseta");
            System.out.println("2 - Boné");
            System.out.println("3 - Shorts");
            System.out.println("4 - Vestido");
            System.out.println("5 - Sair");
            System.out.print("Escolha uma opção: ");
            opcaoVestuario = scanner.nextInt();

            if (opcaoVestuario >= 1 && opcaoVestuario <= 4) {
                System.out.println("\nO que deseja fazer?");
                System.out.println("1 - Vestir");
                System.out.println("2 - Lavar");
                System.out.println("3 - Secar");
                System.out.print("Escolha uma opção: ");
                opcaoAcao = scanner.nextInt();

                switch (opcaoVestuario) {
                    case 1 -> executar(minhaCamiseta, opcaoAcao);
                    case 2 -> executar(meuBone, opcaoAcao);
                    case 3 -> executar(meuShorts, opcaoAcao);
                    case 4 -> executar(meuVestido, opcaoAcao);
                }
            } else if (opcaoVestuario != 5) {
                System.out.println("Opção inválida, tente novamente.");
            }

        } while (opcaoVestuario != 5);

        System.out.println("Saindo...");
        scanner.close();*/

        Fracao f1 = new Fracao(2, 5);
        Fracao f2 = new Fracao(3, 7);
        System.out.println("2/5 + 3/7 = " + f1.somar(f2).getResultado());

        Fracao f3 = new Fracao(4, 3);
        Fracao f4 = new Fracao(2, 7);
        System.out.println("4/3 - 2/7 = " + f3.subtrair(f4).getResultado());

        Fracao f5 = new Fracao(10, 3);
        Fracao f6 = new Fracao(4, 3);
        System.out.println("10/3 - 4/3 = " + f5.subtrair(f6).getResultado());

        Fracao f7 = new Fracao(5, 2);
        Fracao f8 = new Fracao(4, 3);
        Fracao f9 = new Fracao(2, 5);
        System.out.println("5/2 * (4/3 - 2/5) = " + f7.multiplicar(f8.subtrair(f9)).getResultado());

        Fracao f10 = new Fracao(5, 7);
        Fracao f11 = new Fracao(3, 4);
        System.out.println("5/7 * 3/4 = " + f10.multiplicar(f11).getResultado());

        Fracao f12 = new Fracao(4, 3);
        Fracao f13 = new Fracao(2, 5);
        Fracao f14= new Fracao(3, 2);

        System.out.println("4/3 + 2/5 + 3/2 = " + f12.somar(f13).somar(f14).getResultado());




    }

}

    /*public static void executar(Vestuario item, int opcaoAcao) {
        switch (opcaoAcao) {
            case 1 -> item.vestir();
            case 2 -> item.lavar();
            case 3 -> item.secar();
            default -> System.out.println("Opção inválida.");
        }
    }*/



