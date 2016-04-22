import java.util.Scanner;

/**
 * Created by Bludya on 18.3.2016 г..
 */
public class Base7ToDecimal {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int base7 = sc.nextInt();
        int decimal = 0;
        int i = 0;
        while(base7>0){
            decimal+=(base7%10)*Math.pow(7,i);
            base7 = (base7/10);
            i++;
        }

        System.out.println(decimal);
    }
}
