import java.util.Locale;
import java.util.Scanner;

/**
 * Created by Bludya on 15.3.2016 г..
 */
public class GetAverage {
    public static double average(Scanner sc){
        double sum=0;
        int count=0;
        while(sc.hasNextDouble()){
            sum+=sc.nextDouble();
            count++;
        }
        return sum/count;
    }
    public static void main(String[] args) {
        Locale.setDefault(Locale.ROOT);
        Scanner sc = new Scanner(System.in);
        System.out.format("%.2f",average(sc));
    }
}
