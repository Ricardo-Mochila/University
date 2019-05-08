/*import java.util.Random;
import java.util.Scanner;

public class Exercicio17 {
    public static void main(String[] args) {
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);
        int a = random.nextInt(9);
        int b = random.nextInt(9);
        int c = random.nextInt(9);
        System.out.print(a+" ");
        System.out.print(b+" ");
        System.out.println(c);
        System.out.print("Whats your first guess? ");
        int ai = scanner.nextInt();
        System.out.print("Whats your second guess? ");
        int bi = scanner.nextInt();
        System.out.print("Whats your third guess? ");
        int ci = scanner.nextInt();

        if(ai<10) {

            if (ai == a) {
                System.out.print("Fermi ");
            } else if (ai == b) {
                System.out.print("Pico ");
            } else if (ai == c) {
                System.out.print("Pico ");
            }
            else{
                System.out.print("Nano ");
            }
        }
        if(ci<10) {

            if (ci == c) {
                System.out.print("Fermi ");
            } else if (ci == a) {
                System.out.print("Pico ");
            } else if (ci == b) {
                System.out.print("Pico ");
            }
            else{
                System.out.print("Nano ");
            }
        }

        if(bi<10) {

            if (bi == b) {
                System.out.println("Fermi");
            } else if (bi == a) {
                System.out.println("Pico");
            } else if (bi == c) {
                System.out.println("Pico");
            }
            else{
                System.out.println("Nano");
            }
        }


    }
}
*/