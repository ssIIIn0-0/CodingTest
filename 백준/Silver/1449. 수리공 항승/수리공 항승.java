import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);

        int count = sc.nextInt();
        int tapeLength = sc.nextInt();

        int[] arr = new int[count];
        for (int i = 0; i < count; i++) {
            arr[i] = sc.nextInt();
        }

        Arrays.sort(arr);

        int temp = 0;
        int result = 0;
        for (int i = 0; i < count; i++) {
            if (arr[i] > temp) {
                result++;
                temp = arr[i];
                temp += tapeLength - 1;
            }
        }

        System.out.println(result);
    }
}