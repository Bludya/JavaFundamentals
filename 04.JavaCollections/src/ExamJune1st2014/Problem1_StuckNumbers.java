package ExamJune1st2014;

import java.util.HashSet;
import java.util.Scanner;

public class Problem1_StuckNumbers {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        String[] numbers = sc.nextLine().split(" ");
        int count=0;

        for(int num1=0;num1<n;num1++){
            for(int num2=0;num2<n;num2++){
                if(num2!=num1){
                    for(int num3=0;num3<n;num3++){
                        if(num3!=num2 && num3!=num1){
                            for(int num4=0;num4<n;num4++){
                                if(num4!=num1 && num4!=num2 && num4!=num3 && (numbers[num1]+numbers[num2]).equals(numbers[num3]+numbers[num4])){
                                    System.out.printf("%s|%s==%s|%s%n",numbers[num1],numbers[num2],numbers[num3],numbers[num4]);
                                    count++;
                                }
                            }
                        }
                    }
                }
            }
        }
        if(count==0){
            System.out.println("No");
        }
    }
}
