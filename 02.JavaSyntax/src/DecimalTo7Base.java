import java.util.Scanner;

/**
 * Created by Bludya on 18.3.2016 г..
 */
public class DecimalTo7Base {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int decimal = sc.nextInt();
        String sevenBase = "";

        while(decimal>=7){
            sevenBase=decimal%7+sevenBase;
            decimal/=7;
        }
        sevenBase=decimal+sevenBase;

        System.out.println(sevenBase);
    }
}
