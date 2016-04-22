import java.util.Scanner;

/**
 * Created by Bludya on 18.3.2016 г..
 */
class Point{
    int x;
    int y;
    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }

}
public class TriangleArea{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter coordinates of point A: ");
        Point a = new Point(sc.nextInt(),sc.nextInt());

        System.out.print("Enter coordinates of point B: ");
        Point b = new Point(sc.nextInt(),sc.nextInt());

        System.out.print("Enter coordinates of point C: ");
        Point c = new Point(sc.nextInt(),sc.nextInt());

        System.out.println("The area of the triangle is: " + Math.abs(a.x*(b.y-c.y)+b.x*(c.y-a.y)+c.x*(a.y-b.y))/2);
    }
}
