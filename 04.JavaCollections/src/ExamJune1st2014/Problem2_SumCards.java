package ExamJune1st2014;

import java.util.Scanner;

public class Problem2_SumCards {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] cards = sc.nextLine().replaceAll("[SHDC]","").replaceAll("J","12").replaceAll("Q","13").replaceAll("K","14").replaceAll("A","15").split(" ");
        int count=0;
        String prevCard="0";
        int sum=0;

        for(String card:cards){
            if(card.equals(prevCard)){
                count++;
            }
            else{
                if(count>1){
                    sum+=Integer.parseInt(prevCard)*count*2;
                }
                else{
                    sum+=Integer.parseInt(prevCard)*count;
                }
                count=1;
                prevCard=card;
            }
        }
        if(count>1){
            sum+=Integer.parseInt(prevCard)*count*2;
        }
        else {
            sum+=Integer.parseInt(prevCard)*count;
        }

        System.out.println(sum);
    }
}
