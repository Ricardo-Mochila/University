import java.util.Scanner;

public class exercico2_19 {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        System.out.print("What's your first name: ");
        String firstName = scanner.next();

        System.out.print("What's your middle name: ");
        String middleName = scanner.next();
        middleName = middleName.substring(0,1);


        System.out.print("What's your last name: ");
        String lastName = scanner.next();

        System.out.print(firstName+" ");
        System.out.print(middleName+". ");
        System.out.print(lastName);




    }
}
