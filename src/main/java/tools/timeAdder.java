package tools;

import java.util.Scanner;

public class timeAdder {
    private final static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        boolean flag = true;
        String input;
        int newMinute, newSecond;
        int minute = 0 , second = 0, hour = 0;
        while(flag){
            input = sc.nextLine();
            if(input.equals("q")){
                flag = false;
                System.out.println(hour+":"+minute+":"+second);
            }else{
                newMinute = Integer.parseInt(input.substring(0, input.length()-2));
                newSecond = Integer.parseInt(input.substring(input.length()-2));
                if (newSecond > 59){
                    System.out.println("This is not a valid time!");
                    continue;
                }
                minute += newMinute;
                second += newSecond;
                minute += second / 60;
                second %= 60;
                hour += minute / 60;
                minute %= 60;
            }
        }
    }
}
