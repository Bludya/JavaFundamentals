import java.util.Scanner;

/**
 * Created by Bludya on 18.3.2016 г..
 */
public class HitTheTarget {
    public static void main(String[] args) {
        int target = new Scanner(System.in).nextInt();

        for(int i =1; i<=20;i++){
            for(int j=1;j<=20;j++){
                if(i+j==target){
                    System.out.printf("%d + %d = %d%n",i,j,target);
                }
                else if( i-j==target){
                    System.out.printf("%d - %d = %d%n",i,j,target);
                }
            }
        }

    }
}
