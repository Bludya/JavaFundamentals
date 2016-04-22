import java.io.*;
import java.util.ArrayList;

//////////////////////////////////////////////////////////////////////////////////////////////////////
//The file is premade and I kept the output file. When testing you may close the output file,
//so you see that the program is actually writing, or make file filled with double object,
//replace doubles.list in InputFIles and check if it copies the exact same stuff in the output file
//////////////////////////////////////////////////////////////////////////////////////////////////////
public class SaveAnArrayOfDoubles {
    public static void main(String[] args) {
        ArrayList<Double> doubles;
        try(ObjectOutputStream outputStream = new ObjectOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream("Resources/OutputFiles/doubles.list"))); //[12312.4124, 574574.112, 19064.32, 3.6]
            ObjectInputStream inputStream = new ObjectInputStream(
                    new BufferedInputStream(
                            new FileInputStream("Resources/InputFiles/doubles.list")))){

            doubles=(ArrayList<Double>)inputStream.readObject();
            outputStream.writeObject(doubles);
            outputStream.close();
           // System.out.print(doubles);
        } catch(Exception ex){
            System.out.println(ex);
        }

    }
}
