package MinHeap;

import java.util.Arrays;

public class MinHeap {
    private int [] data;
    private int size;
    MinHeap(int[] array) {
        int n = array.length;
        this.data = new int [n+1];
        this.size = n;

        for (int i=1; i<=n; ++i) {
            data[i] = array[i-1];
        }
        for (int i=n/2; i>0; i--){
            shiftDown(i);
        }
    }
    public void shiftDown(int index) {
        while (2*index <= size) {
            int j = 2*index;
            if(j+1 <= size && data[j+1] < data[j]) {
                j ++;
            }
            if (data[index] <= data[j]) break;
            swap(index, j);
            index = j;
        }
    }
    public void swap(int i, int j) {
        int tmp = data[i];
        data[i] = data[j];
        data[j] = tmp; 
    }
    public int extractMin() {
        assert(size > 0);
        int res = data[1];
        swap(size, 1);
        size --;
        shiftDown(1);

        return res;
        
    }

}
