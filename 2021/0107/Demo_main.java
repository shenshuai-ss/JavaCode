package MinHeap;
import java.util.ArrayList;
import java.util.PriorityQueue;
public class Demo_main {
    public static ArrayList<Integer> Min_k(int k, int[] arr) {
        ArrayList<Integer> res = new ArrayList<>();
        MinHeap minHeap = new MinHeap(arr);
        for (int i=0; i<k; ++i) {
           res.add(minHeap.extractMin());
        }
        return res;
    }


    public static ArrayList<Integer> Max_k(int k, int[] arr) {
        // Comparator<Integer> cmp = new Comparator<Integer>(){
        //     public int compare(Integer o1, Integer o2) {
        //         // return o1-o2; 小根堆
        //         // return o2-o1; 大根堆
        //         return o2-o1;
        //     }
        // };
        // Comparable<Integer> cmp = (Integer o1, integer o2) -> o2-o1;
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o1-o2);
        ArrayList<Integer> res = new ArrayList<>();
        for (Integer x:arr) {
            pq.add(x);
        }

        for (int i=0; i<k; ++i) {
            res.add(pq.peek()); pq.poll();        
        }
        return res;
    }
    public static void main(String[] args) {
        int arr[] = {1,2,3,4,5,6,7};
        ArrayList<Integer> res = Max_k(3, arr);
        System.out.println(res.toString());
        // System.out.println("请输入一个数字n:");
        // Scanner scanner = new Scanner(System.in);
        // int n = scanner.nextInt();
        // System.out.println("请输入一个数字k:");
        // int k = scanner.nextInt();
        // int[] arr = new int[n];
        // System.out.println("请输入一个数组:");
        // for (int i=0; i<n; ++i) {
        //     int t = scanner.nextInt();
        //     arr[i] = t;
        // }
        // System.out.println(Arrays.toString(arr));

       

        // ArrayList<Integer> res = Min_k(k, arr);
        // System.out.println(res.toString());
        // scanner.close();
    }
}
