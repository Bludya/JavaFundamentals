
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Problem02_SortArrayWithStreamAPI {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Integer> numbers = Arrays.asList(sc.nextLine().split(" ")).stream().map(Integer::parseInt).collect(Collectors.toList());
        String command = sc.nextLine();

        if(command.equals("Descending")){
            numbers.stream().sorted((el1,el2) -> el2.compareTo(el1)).forEach(p ->System.out.printf("%d ",p));
        }
        else if(command.equals("Ascending")){
            numbers.stream().sorted((el1,el2) -> el1.compareTo(el2)).forEach(p ->System.out.printf("%d ",p));
        }

    }
}
