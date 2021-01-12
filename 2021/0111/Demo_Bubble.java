package Sort3;

import java.util.Arrays;

public class Demo_Bubble {
    public static void bubbleSort(int[] arr) {
        if (arr == null && arr.length == 0) {
            return;
        }
        boolean a = true;
        for (int i=0; i< arr.length-1 && a; i++ ) {
            a = false;
            for (int j=0; j < arr.length-1-i; j++) {
                if (arr[j]>arr[j+1]) {
                    swap(arr, j, j+1);
                    a = true;
                }
            }
        } 
    }
    public static void swap(int[] arr,int a, int b) {
        int tmp = arr[b];
        arr[b] = arr[a];
        arr[a] = tmp;
    }

    public static void main(String[] args) {
        int[] arr = {3,4,2,1,6,8,9,2,4,5};
        bubbleSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
