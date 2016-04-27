package ExamJune1st2014;

import java.math.BigDecimal;
import java.util.Scanner;

public class Problem3_SimpleExpression {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        String expression= sc.nextLine().replaceAll(" ","");
        String[] numbers = expression.replaceAll("[+-]"," ").split(" ");

        char[] operators = expression.replaceAll("[ .,0-9]","").toCharArray();
        BigDecimal sum = new BigDecimal(numbers[0]);
        sum.setScale(30);
        for(int i=0;i<operators.length;i++){
            if(operators[i]=='+'){
                sum=sum.add(new BigDecimal(numbers[i+1]).setScale(30));
            }
            else {
                sum=sum.subtract(new BigDecimal(numbers[i+1]).setScale(30));
            }

        }

        System.out.println(sum);

    }
}
