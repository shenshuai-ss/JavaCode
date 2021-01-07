package Sort2;

public class MaxHeap_lsc {
    private int[] data;
    private int capacity;
    private int cnt;


    private void shiftDown(int k) {
        while (2*k <= cnt) {
            int j = 2*k;
            if (j+1<=cnt && data[j+1]>data[j]) j ++;
            if (data[k] >= data[j]) {
                break;
            }
            swap(k, j);
            k = j;
        }
    }

    private void shiftUp(int k) {
        // k/2==1
        while (k >= 2 && data[k/2] < data[k]) {
            swap(k/2, k);
            k /= 2;
        }
    }

    private void swap(int i, int j) {
        int t = data[i];
        data[i] = data[j];
        data[j] = t;
    }

    MaxHeap_lsc(int n) {
        this.data = new int[n+1];
        this.capacity = n+1;
        this.cnt = 0;
    }

    public void insert(int x) {
        assert(cnt+1 <= capacity);
        data[++ cnt] = x;
        shiftUp(cnt);
    }

    MaxHeap_lsc(int[] arr) {
        int n = arr.length;
        this.data = new int[n+1];
        this.cnt = n;
        this.capacity = n+1;
        
        for (int i=1; i<=n; ++i) {
            data[i] = arr[i-1];
        }
        // heapify lgn 
        for (int i=n/2; i>=1; --i) {
            shiftDown(i);
        }
    }

    public int extracMax() {
        //提取最大值并返回。
        assert(this.cnt >= 1);
        int res = data[1];
        swap(1, cnt);
        cnt --;
        shiftDown(1);


        return res;
    }


    public int getCnt() {
        return cnt;
    }
}
