import java.util.Scanner;

/**
 * Created by Bludya on 18.3.2016 г..
 */
public class GetFirstOddOrEvenElements {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] firstLine = sc.nextLine().split(" ");
        String[] commands = sc.nextLine().split(" ");

        int count = Integer.parseInt(commands[1]);

        for(String number: firstLine){
            if(commands[2].toLowerCase().contains("odd") && Integer.parseInt(number)%2==1){
                System.out.print(number+" ");
            }
            else if(commands[2].toLowerCase().contains("even") && Integer.parseInt(number)%2==0){
                System.out.print(number+" ");
            }
            if(count--<0){
                return;
            }
        }
    }
}
