import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Problem07_MagicExchangableWords {
    public static void main(String[] args) {
        Map<Character,Character> charMap = new HashMap<>();
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split(" ");
        char[] firstWord = input[0].toCharArray();
        char[] secondWord = input[1].toCharArray();
        boolean output = true;

        for(int i=0;i<firstWord.length;i++){
            if(charMap.containsKey(firstWord[i])){
                if(charMap.get(firstWord[i])!=secondWord[i]){
                    output = false;
                    break;
                }
            }
            else{
                charMap.put(firstWord[i],secondWord[i]);
            }
        }

        System.out.println(output);

    }
}
