package MinHeap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
public class Demo_main {
    public static ArrayList<Integer> Min_k(int k, int[] arr) {
        ArrayList<Integer> res = new ArrayList<>();
        MinHeap minHeap = new MinHeap(arr);
        for (int i=0; i<k; ++i) {
           res.add(minHeap.extractMin());
        }
        return res;
    }
    public static void main(String[] args) {
        System.out.println("请输入一个数字n:");
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        System.out.println("请输入一个数字k:");
        int k = scanner.nextInt();
        int[] arr = new int[n];
        System.out.println("请输入一个数组:");
        for (int i=0; i<n; ++i) {
            int t = scanner.nextInt();
            arr[i] = t;
        }
        System.out.println(Arrays.toString(arr));

       

        ArrayList<Integer> res = Min_k(k, arr);
        System.out.println(res.toString());
        scanner.close();
    }
}
