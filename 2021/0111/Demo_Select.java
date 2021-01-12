package Sort3;

import java.util.Arrays;

public class Demo_Select {
    public static void selectSort(int[] arr) {
        int l = 0;
        int r = arr.length-1;
        while (l<=r) {
            int min = l;
            int max = r;
            if (arr[min]>arr[max]) {
                swap(arr,l,r);
            }
            for (int i=l+1; i<=r-1; ++i) {
                if (arr[min]>arr[i]) {
                    min = i;
                }else if (arr[max]<arr[i]) {
                    max = i;
                }
            }
            if (min != l) {
                swap(arr, min, l);
            }
            if (max !=r) {
                swap(arr, max, r);
            }
            l++;
            r--;

        }
    }
public static void swap(int[] arr, int a, int b) {
    int tmp = arr[b];
    arr[b] = arr[a];
    arr[a] = tmp;
}
    public static void main(String[] args) {
        int [] arr = {4,2,7,8,9,1,3,2,9,10,23,4};
        selectSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
