
import java.util.Random;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Jogo da Adivinhacao");
        System.out.println("Adivinhe o numero secreto");

        int numAleatorio =  new Random().nextInt(101);
        int tentativas = 0;
        int acertos = 1;

        while (tentativas <5 ) {
            System.out.println("Digite um palpite: ");
            int palpite = sc.nextInt();

            tentativas++;

            if (palpite == numAleatorio) {
                System.out.println("Voce acertou em " + tentativas + " tentativas.");
                break;
            } else if (palpite < numAleatorio) {
                System.out.println("O numero e maior! " );
            }else if (palpite > numAleatorio) {
                System.out.println("O numero e menor! "  );

            }else if (acertos >0) {
                System.out.println( "Voce acertou ");
            }
        }if (numAleatorio != numAleatorio) {
            System.out.println("Voce perdeu!");
        }else {
            System.out.println("Voce esgotou suas tentativas e perdeu! ");
            System.out.println(tentativas + " tentativas utilizadas ");
        }
    }
}