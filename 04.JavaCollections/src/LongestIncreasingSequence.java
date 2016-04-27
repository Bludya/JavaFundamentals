import java.util.ArrayList;
import java.util.Scanner;

public class LongestIncreasingSequence {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String[] numbers = sc.nextLine().split(" ");


        int startIndex=0;
        int longestCount = 1;
        int count=1;
        System.out.print(numbers[0]+" ");

        for(int i=1;i<numbers.length;i++){
            int a=Integer.parseInt(numbers[i-1]);
            int b=Integer.parseInt(numbers[i]);
            if(b>a){
                count++;                     //if the sequence continues increase the counter.
            }
            else {
                if(count>longestCount){      //If it is not increasing, and the sequence is bigger make it the biggest sequence
                    startIndex=i-count;      //
                    longestCount=count;
                }
                count=1;                    //reset counter for the new sequence
                System.out.println();
            }
            System.out.print(numbers[i]+" ");

        }
        if(count>longestCount){                 // added so it counts the last word if it is part of the sequence
            startIndex=numbers.length-count;
            longestCount=count;
        }
        System.out.println();
        System.out.print("Longest: ");
        for(int i=startIndex;i<startIndex+longestCount;i++){
            System.out.print(numbers[i]+" ");
        }
    }
}
