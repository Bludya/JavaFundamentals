import java.util.Scanner;

/**
 * Created by Bludya on 15.3.2016 г..
 */
public class GhettoNumeralSystem {
    public static String ghettoNumber(char c){
        switch (c){
            case '0': return "Gee";
            case '1': return "Bro";
            case '2': return "Zuz";
            case '3': return "Ma";
            case '4': return "Duh";
            case '5': return "Yo";
            case '6': return "Dis";
            case '7': return "Hood";
            case '8': return "Jam";
            case '9': return "Mack";
        }
        return "";
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String sum = "";
        for (char c : sc.nextLine().toCharArray()) {
            sum+=ghettoNumber(c);
        }
        System.out.println(sum);
    }
}
