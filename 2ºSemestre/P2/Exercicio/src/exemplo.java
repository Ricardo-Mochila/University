/*
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class exemplo {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);



        double[][] temperature = new double[12][31];
        temperature[0] = new double[31];
        temperature[1] = new double[28];
        temperature[2] = new double[31];
        temperature[3] = new double[30];
        temperature[4] = new double[31];
        temperature[5] = new double[30];
        temperature[6] = new double[31];
        temperature[7] = new double[31];
        temperature[8] = new double[30];
        temperature[9] = new double[31];
        temperature[10] = new double[30];
        temperature[11] = new double[31];

        double max = 0;
        double min = 60;
        double average1M = 0;
        double average2M = 0;
        double average3M = 0;
        double average4M = 0;
        double average5M = 0;
        double average6M = 0;
        double average7M = 0;
        double average8M = 0;
        double average9M = 0;
        double average10M = 0;
        double average11M = 0;
        double average12M = 0;

        Random random = new Random();
        DecimalFormat round = new DecimalFormat(".#");
        int h = 0;
        int j = 0;
        int k = 0;
        int max1 = 0;
        int max2 = 0;
        int min1 = 0;
        int min2 = 0;


        for (j = 0; j < temperature.length; j++) {

            for (h = 0; h < temperature[j].length; h++) {
                temperature[j][h] = random.nextDouble()*70-23;


                if (max < temperature[j][h]) {
                    max = temperature[j][h];
                }
                if (max ==  temperature[j][h]){
                    max1 = j+1;
                    max2 = h+1;
                }


            }
            for (k = 0; k < temperature[j].length; k++) {
                if (min > temperature[j][k]) {
                    min = temperature[j][k];
                }
                if (min ==  temperature[j][k]){
                    min1 = j+1;
                    min2 = k+1;
                }


            }

        }
        for (int l = 0; l < temperature[0].length; l++){
            average1M = temperature[0][l] + average1M;
        }
        for (int l1 = 0; l1 < temperature[1].length; l1++){
            average2M = temperature[1][l1] + average2M;
        }
        for (int l2 = 0; l2 < temperature[2].length; l2++){
            average3M = temperature[2][l2] + average3M;
        }
        for (int l3 = 0; l3 < temperature[3].length; l3++){
            average4M = temperature[3][l3] + average4M;

        }for (int l4 = 0; l4 < temperature[4].length; l4++){
            average5M = temperature[4][l4] + average5M;

        }for (int l5 = 0; l5 < temperature[5].length; l5++){
            average6M = temperature[5][l5] + average6M;
        }
        for (int l6 = 0; l6 < temperature[6].length; l6++){
            average7M = temperature[6][l6] + average7M;

        }for (int l7 = 0; l7 < temperature[7].length; l7++){
            average8M = temperature[7][l7] + average8M;
        }
        for (int l8 = 0; l8 < temperature[8].length; l8++){
            average9M = temperature[8][l8] + average9M;
        }
        for (int l9 = 0; l9 < temperature[9].length; l9++){
            average10M = temperature[9][l9] + average10M;
        }
        for (int l10 = 0; l10 < temperature[10].length; l10++){
            average11M = temperature[10][l10] + average11M;

        }
        for (int l11 = 0; l11 < temperature[11].length; l11++){
            average12M = temperature[11][l11] + average12M;
        }

        System.out.print("1 - Dia Temperatura maxima e minima registada no ano\n" + "2 - Temperatura media de cada mes do ano\n" + "3 - Temperatura num determinado dia\n" + "0 - Terminar\n"+"Opcao: ");
        int menu = scanner.nextInt();
        while (menu != 0){
            switch (menu){
                case 1:
                    System.out.println("\nA temperatura maxima anual foi no dia: " + max2+", e no mes "+max1 +", e foi de: "+ round.format(max));
                    System.out.println("A temperatura minima anual foi no dia: " + min2+", e no mes "+min1 +", e foi de: "+ round.format(min)+"\n");
                    break;

                case 2:
                    System.out.println("\nTemperatura media no mes de Janeiro  " + average1M/31);
                    System.out.println("Temperatura media no mes de Fevereiro  " + average2M/28);
                    System.out.println("Temperatura media no mes de Marco  " + average3M/31);
                    System.out.println("Temperatura media no mes de Abril  " + average4M/30);
                    System.out.println("Temperatura media no mes de Maio  " + average5M/31);
                    System.out.println("Temperatura media no mes de Junho  " + average6M/30);
                    System.out.println("Temperatura media no mes de Julho  " + average7M/31);
                    System.out.println("Temperatura media no mes de Agosto  " + average8M/31);
                    System.out.println("Temperatura media no mes de Setembro  " + average9M/30);
                    System.out.println("Temperatura media no mes de Outobro  " + average10M/31);
                    System.out.println("Temperatura media no mes de Novembro  " + average12M/30);
                    System.out.println("Temperatura media no mes de Dezembro  " + average12M/31);
                    break;

                case 3:
                    System.out.print("Que Mes pretende procurar: ");
                    int month = scanner.nextInt();
                    System.out.print("Que dia pretende procurar: ");
                    int day = scanner.nextInt();

                    if (month < 1 || month > 12 || day < 1 || day-1 > temperature[month-1].length){
                        System.out.println("Introduza um dia valido");
                    }
                    else{
                        System.out.println(round.format(temperature[month-1][day-1]));
                    }
                    break;

                default:
                    System.out.println("Introduza uma opcao valida");
            }
            System.out.print("\n1 - Dia Temperatura maxima e minima registada no ano\n" + "2 - Temperatura media de cada mes do ano\n" + "3 - Temperatura num determinado dia\n" + "0 - Terminar\n"+"Opcao: ");
            menu = scanner.nextInt();


        }





    }
}
*/