package task1;

public class Task1 {
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int m = Integer.parseInt(args[1]);
        int i = 0;
        do {
            System.out.print(i + 1);
            i = (i + (m - 1)) % n;
        } while (i != 0);
    }
}
