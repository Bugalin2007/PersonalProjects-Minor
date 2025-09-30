package practice;

import java.util.Random;
import java.util.Scanner;

/*
Program # :  1.2
Techniques : Flow Control
Goal : read in Player input, generate a random figure, and match them.
 */
public class paperScissorRock {
    private static final Scanner sc = new Scanner(System.in);
    private static Random random = new Random();
    private static final String[] rockAlias = new String[]{"rock","rocks","stone","0"};
    private static final String[] paperAlias = new String[]{"paper","papers","sheet","1"};
    private static final String[] scissorAlias = new String[]{"scissor","scissors","shear","shears","2"};

    private static boolean queryAlias(String input,String[] alias){
        for (String s : alias) {
            if (s.equals(input)) {
                return true;
            }
        }
        return false;
    }

    private static int compare(int player,int computer){
        return (player - computer + 3) % 3;
    }

    public static void main(String[] args) {
        String input = sc.nextLine().toLowerCase();
        int player,computer;
        if(queryAlias(input,paperAlias)){
            player = 1;
        }else if(queryAlias(input,scissorAlias)){
            player = 2;
        }else if(queryAlias(input,rockAlias)){
            player = 0;
        }else{
            System.out.println("Invalid gesture!");
            System.exit(0);
            player = -1;
        }
        computer = random.nextInt(0,3);
        System.out.println("Computer: " + computer);
        switch (compare(player,computer)){
            case 0 -> System.out.println("You tie!");
            case 1 -> System.out.println("You win!");
            case -1 -> System.out.println("You lose!");
        }
    }
}
