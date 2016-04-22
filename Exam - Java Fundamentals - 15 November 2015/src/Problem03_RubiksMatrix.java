import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Coordinates{
    int r;
    int c;
    public Coordinates(int r,int c){
        this.r = r;
        this.c = c;
    }
}

public class Problem03_RubiksMatrix {
    static int r;
    static int c;
    static int[][] matrix;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        r = sc.nextInt();
        c = sc.nextInt();
        sc.nextLine();
        matrix = new int[r][c];

        int n = Integer.parseInt(sc.nextLine());

        for(int count = 0; count < r*c; count++){
            matrix[count/c][count%c]=count;
        }

        for (int i = 0; i < n; i++) {
            String[] line = sc.nextLine().split(" ");
            int index = Integer.parseInt(line[0]);
            String command = line[1];
            long amount = Integer.parseInt(line[2]);

            switch (command){
                case "left":
                    moveRow(index,(int)amount%c);
                    break;
                case "right":
                    moveRow(index,(int)(c-amount%c));
                    break;
                case "up":
                    moveCol(index,(int)amount%r);
                    break;
                case "down":
                    moveCol(index,(int)(r-amount%r));
                    break;
            }

        }
        rearangeMatrix();

    }

    private static void rearangeMatrix() {
        Map<Integer,Coordinates> matrixMap = new HashMap<>();

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                matrixMap.put(matrix[i][j],new Coordinates(i,j));
            }
        }

        for (int count = 0; count < r*c; count++) {
            int i=count/c;
            int j=count%c;
            if(matrix[i][j] != count){
                Coordinates tempCoo = matrixMap.get(count);

                int temp = matrix[i][j];
                matrix[i][j] = matrix[tempCoo.r][tempCoo.c];
                matrix[tempCoo.r][tempCoo.c] = temp;

                matrixMap.replace(i*j,new Coordinates(i,j));
                matrixMap.replace(temp,tempCoo);

                System.out.printf("Swap (%d, %d) with (%d, %d)%n",i,j,tempCoo.r,tempCoo.c);
            }
            else{
                System.out.println("No swap required");
            }

        }

    }

    private static void moveRow(int row,int amount){
        int[] tempArr = new int[c];

        for(int i = 0; i < c; i++){
            tempArr[i] = matrix[row][(i+amount)%c];
        }

        matrix[row] = tempArr;
    }

    private static void moveCol(int col, int amount){
        int[] tempArr = new int[r];

        for (int i = 0; i < r; i++) {
            tempArr[i] = matrix[(i+amount)%r][col];
        }

        for (int i = 0; i < r; i++) {
            matrix[i][col] = tempArr[i];
        }

    }
}