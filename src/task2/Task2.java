package task2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Task2 {
    public static void main(String[] args) throws IOException {
        FileInputStream test1 = new FileInputStream(args[0]);
        FileInputStream test2 = new FileInputStream(args[1]);
        String file1 = new String(test1.readAllBytes())
                .replace("\n"," ");
        float centerX = Float.parseFloat(file1.split(" ")[0]);
        float centerY = Float.parseFloat(file1.split(" ")[1]);
        float r = Float.parseFloat(file1.split(" ")[2]);
        String[] file2 = new String(test2.readAllBytes()).split("\n");
        boolean first = true;
        for (String point:
             file2) {
            if (first){
                first = false;
            } else {
                System.out.print(" ");
            }
            float x = Float.parseFloat(point.split(" ")[0]);
            float y = Float.parseFloat(point.split(" ")[1]);
            float R = (x - centerX)*(x - centerX) + (y - centerY) * (y - centerY);
            if (R > r * r)
                System.out.print(2);
            else if (R < r * r)
                System.out.print(1);
            else
                System.out.print(0);

        }

    }
}
