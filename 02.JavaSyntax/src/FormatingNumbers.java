
import java.util.Locale;
import java.util.Scanner;

/**
 * Created by Bludya on 18.3.2016 г..
 */
public class FormatingNumbers {
    public static void main(String[] args) {
        Locale.setDefault(Locale.ROOT);
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        float b = sc.nextFloat();
        float c = sc.nextFloat();

        System.out.printf("|%-10s|%10s|%10.2f|%-10.3f|", Integer.toHexString(a).toUpperCase(), String.format(Integer.toBinaryString(a),"%10").replace(' ','0'),b,c);
    }
}
