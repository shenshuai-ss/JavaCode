
import java.util.Arrays;
class MyPriorityQueue<E extends Comparable<E>> {
    private E[] queue; //存放元素的容器
    private int size; // 有效元素个数
    private static final int initalCapacity = 5;
    public MyPriorityQueue(){
        this(initalCapacity);
    }

    
    public MyPriorityQueue(int capacity){
        queue = (E[]) new Comparable[capacity];
    }


    public void add(E value){
        //添加 满足添加前后底层都必须是一个小根堆
        //判满 扩容
        if(size == queue.length){
            queue = Arrays.copyOf(queue, queue.length*2);
        }
        //第一次添加元素
        if (size == 0){
            queue[0] = value;
            size++;
        }else{
            queue[size] = value;
            abjust(size, value);
            size++ ;
        }
        //反之添加元素到队尾之后维持当前的数组还需要是一个小根堆    
    }
    public void abjust(int index,E value) {
        while (index >0) {
            int parantIndex = (index -1)/2;
            if (queue[parantIndex].compareTo(value) > 0) {
                queue[index] = queue[parantIndex];
                index = parantIndex;
                queue[index] = value;
            } else{
                break;
            }
        }
    }
    public void swap(int index1, int index2) {
        E tmp = queue[index1];
        queue[index1] = queue[index2];
        queue[index2] = tmp;
    }
    public void shiftDown(int parantIndex, int size ) {
        while (2 * parantIndex+1 < size) {
            int j = 2*parantIndex+1;//j等于左子叶
            //判断是否存在右子叶，如果存在则判断左右子叶的大小，把最小值位置赋值给j
            if(j+1 < size && queue[j+1].compareTo(queue[j]) < 0) {
                j++;
            }
            //判断j的位置与parantIndex位置值的大小。如果j的位置的值小于parantIndex位置的值，则把两个位置的值替换位置。
            //并更新paranrIndex位置为j位置，继续满足小根堆结构。继续更新parantIndex位置 ，直到完成整个小根堆。
            if(queue[j].compareTo(queue[parantIndex]) < 0) {
                swap(parantIndex, j);
                parantIndex = j;
            }else {
                break;
            }                     
        }
    
    }

    public int getSize() {
        return size;                                            
    }
    public boolean remove() {
        //删除头
        if (size == 0) {
            return false;
        }
        swap(0, size-1);
        size--;                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     
        shiftDown(0, size);
        return true;
    }
    public int lookUp(E target) {
        for (int i=0; i<size ;++i) {
            if(queue[i].compareTo(target)==0) {
                return i;
            }
       }
       return -1;
    }
    public boolean remove(E target) {
        //删除指定元素。
        int n = lookUp(target);
        if(n == -1) {
            return false;
        } 
        for (int i=n; i<size-1; ++i) {
            queue[i] = queue[i+1];
        }
        size--;
        shiftDown(n,size);
        return true;
    }
        
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("[");
        for (int i=0; i<size; ++i) {
            s.append(queue[i]);
            if (i != size-1) {
                s.append(", ");
            }
        }
        s.append("]");
        return s.toString();
    }
}