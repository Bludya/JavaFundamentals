import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Problem01_PalatkaConf {
    public static void main(String[] args) {
        Map<String,Integer> assets = new HashMap<>();
        assets.put("normal",2);
        assets.put("firstClass",3);
        assets.put("single",1);
        assets.put("double",2);
        assets.put("triple",3);
        assets.put("musaka",2);
        assets.put("zakuska",0);


        int beds=0;
        int food=0;

        Scanner sc = new Scanner(System.in);

        int people = Integer.parseInt(sc.nextLine());
        int n = Integer.parseInt(sc.nextLine());

        for(int i=0; i<n; i++){
            String[] line = sc.nextLine().split(" ");
            int newAssets = (Integer.parseInt(line[1]) * assets.get(line[2]));

            if(line[0].equals("tents") || line[0].equals("rooms")){
                beds+=newAssets;
            }
            else if(line[0].equals("food")){
                food+=newAssets;
            }
        }

        if(beds >= people){
            System.out.printf("Everyone is happy and sleeping well. Beds left: %d%n",beds-people);
        }
        else {
            System.out.printf("Some people are freezing cold. Beds needed: %d%n",people-beds);
        }
        if(food >= people){
            System.out.printf("Nobody left hungry. Meals left: %d%n",food-people);
        }
        else{
            System.out.printf("People are starving. Meals needed: %d%n",people-food);
        }
    }
}
