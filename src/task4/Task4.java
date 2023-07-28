package task4;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Task4 {
    public static void main(String[] args) throws IOException {
        FileInputStream file = new FileInputStream(args[0]);
        String[] text = new String(file.readAllBytes()).split("\\r?\\n");
        ArrayList<Integer> nums = new ArrayList<>();
        int sum = 0;
        for (String one:
             text) {
            try {
                sum += Integer.parseInt(one);
                nums.add(Integer.parseInt(one));
            } catch (Exception e){
            }
        }
        int count = 0;
        for (int num: nums) {
            if (num != sum / nums.size()){
                if (num > sum / nums.size()){
                    count += (num - sum / nums.size());
                } else {
                    count += ( - num + sum / nums.size());
                }
            }
        }
        System.out.print(count);
    }
}
