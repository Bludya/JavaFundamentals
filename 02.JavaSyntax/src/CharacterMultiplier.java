import java.util.Scanner;

/**
 * Created by Bludya on 18.3.2016 г..
 */
public class CharacterMultiplier {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] lines = sc.nextLine().split(" ");
        int size;
        int result = 0;

        if(lines[0].length()>lines[1].length()){
            size = lines[0].length();
        }
        else{
            size = lines[1].length();
        }

        for(int i = 0;i<size;i++){
            try{
                result+=lines[0].charAt(i)*lines[1].charAt(i);
            }catch (IndexOutOfBoundsException e){
                try{
                    result+=lines[0].charAt(i);
                }catch (IndexOutOfBoundsException ex){
                    result+=lines[1].charAt(i);
                }
            }
        }

        System.out.println(result);
    }
}
