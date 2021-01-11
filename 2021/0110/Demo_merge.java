package Sort3;

import java.util.Arrays;


public class Demo_merge {
    public static void merge(int []arr,int l,int m ,int r ) {
        int leftsize = (m-l)+1;
        int rightsize = r-m;
        int[] leftarr = new int [leftsize];
        int[] rightarr = new int [rightsize];
        for (int i=l; i<=(r-l)+l; ++i) {
            if (i<=m) {
                leftarr[i-l] = arr[i];
            }else {
                rightarr[i-m-1] = arr[i];
            }
        } 
        int i = 0;
        int j = 0;
        int k = l;
        while (i <leftsize && j < rightsize) {
            if(leftarr[i]>=rightarr[j]){
                arr[k++] = rightarr[j++];
            }else {
                arr[k++] = leftarr[i++];
            }
        }
        while(i < leftsize) {
            arr[k++] = leftarr[i++];
        }
        while(j < rightsize) {
            arr[k++] = rightarr[j++];
        }


    }

    public static void mergeSort(int[] arr, int l,int r) {
        if (l >= r) {
            return;
        } 
        int m = (l+r)/2;
        mergeSort(arr, l, m);
        mergeSort(arr, m+1, r);
        merge(arr,l,m,r);
    }
    public static void main(String[] args) {
        int[] a = {3,6,6,8,2,5,4,12,9}; 
        mergeSort(a,0,a.length-1);
        System.out.println(Arrays.toString(a));
    }
}
