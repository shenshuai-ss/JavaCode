package TestDemo;
/**
 * HashMap的实现
 * 自己实现一个哈希表，hash算法类比HashMap中hash算法，解决哈希冲突采用链
 * 地址法，实现put(key, value), get(K key), remove(K Key)等方法
 *
 * 回顾：
 * 基于哈希表（散列表），jdk1.8之前采用数组+链表的结构解决哈希冲突，jdk1.8开始
 * 采用数组+链表+红黑树
 * key->f(key)->index O(1)
 * key->f(key)->index->LinkedList O(N)
 * -》红黑树  O(log2 N)
 * 以put方法引入
 * 自定义put方法
 * 1）key-> hash(key) 散列码 -> hash & table.length-1 index
 * 2）table[index] == null 是否存在节点
 * 3）不存在     直接将key-value键值对封装成为一个Node 直接放到index位置
 * 4）存在  key不允许重复
 * 5）存在 key重复       考虑新值去覆盖旧值
 * 6）存在 key不重复     尾插法 将key-value键值对封装成为一个Node 插入新节点
 */
public class MyHashMap<K, V>{
    private int size; //表示map中有多少个键值对
    private Node<K, V>[] table;
    private static final int initalCapacity = 16;

        class Node<K, V> {
            private K key;
            private V value;
            private Node<K, V> next;
            private int hash;
    
            public Node(int hash, K key, V value) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            }
        }
        public MyHashMap() {
            this(initalCapacity);
        }
    
    MyHashMap(int capacity) {
        table = new Node[capacity];
    }
    
    public int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }
    
    public void put(K key, V value){
        // if (size >= szie*0.75){
        //     resize();
        // }
        //key->Hash值->index
        int hash = hash(key);//散列码
        int index = table.length-1 & hash;//按位与 。
    
            //当前index位置不存在节点
        Node<K, V> firstNode = table[index];
        if (firstNode == null) {
            //table[index]位置不存在节点 直接插入
            table[index] = new Node<K,V>(hash, key, value);
            size++;
        }else if (firstNode.key.equals(key)) {
            firstNode.value = value;
        }else { 
            //遍历当前链表
            Node<K, V> tmp = firstNode;
            while(tmp.next != null && !tmp.key.equals(key)) {
                tmp = tmp.next;
            }
    
            if (tmp.key.equals(key)) {
                tmp.value = value;
            }
            if(tmp.next == null) {
                //表示最后一个节点之前的所有节点都不包含key
                if(tmp.key.equals(key)) {
                    //最后一个节点的key与当前所要插入的key是否相等，考虑新值覆盖旧值
                    tmp.value = value;
                }else {
                    //如果不存在，new Node，尾插法插入链表当中
                    tmp.next = new Node<K,V>(hash, key, value);
                    size++;
                }
            }   
        }   
    }
       //课后练习：
    public V get(K key){
        //获取key所对应的value
        int hash = hash(key);//获取散列码
        int index = table.length-1 & hash; //
        Node<K, V> firstNode = table [index];
        if (firstNode == null) {
            return null ;
        }
        Node<K, V> tmp = firstNode;
        if (tmp.key.equals(key)) {
            return tmp.value;
        } else {
             tmp = firstNode.next;
            while (tmp != null) {
                if (tmp.key.equals(key)) {
                return tmp.value;
                }
                tmp = tmp.next;
            }
            return null;
        }
        // while(tmp.next == null && !tmp.key.equals(key)) {
        //    tmp = tmp.next;
        // }
        // if(tmp.key.equals(key)) {
        //     return tmp.value;
        // }
        // return res;
        //key->index
        //在index位置的所有节点中找与当前key相等的key
    }
    
    public boolean remove(K key){
        //key->index
        int hash = hash(key);
        int index = table.length-1 & hash;
        Node<K, V> firstNode = table [index];
        if (firstNode == null) {
            return false;
        }
        Node<K, V> tmp = firstNode;
        if (tmp.key.equals(key)) {
            table[index] = tmp.next;
            size--;
            return true;
        }
        while(tmp.next != null) {
            if (tmp.next.key.equals(key)) {
                tmp.next.hash = 0;
                tmp.next.key = null;
                tmp.next = tmp.next.next;
                tmp.next.next = null;
                size--;
                return true;
            }
            tmp = tmp.next;
        }
        return false;
        //当前位置中寻找当前key所对应的节点
        //找到之后相当于在链表中删除一个节点
    }
    
    public void resize(){
        //HashMap的扩容
        //table进行扩容 2倍的方式
        //
        Node<K, V>[] newTable = new Node[table.length*2];
        //重哈希
        for(int i=0; i<table.length; i++) {
            rehash(i, newTable);
        }
            this.table = newTable;
    }
    
    public void rehash(int index, Node<K,V>[] newTable){
        Node<K, V> temporary = table [index];
        if (temporary == null) {
            return;
        }
        int hash = hash(temporary.key); //重新计算散列码
        int newIndex = newTable.length-1 & hash;
        Node<K, V> firstNode = newTable[newIndex];
        if (firstNode ==  null) {
            newTable[index] = new Node<K,V>(hash, temporary.key, temporary.value);
        }
        Node<K, V> tmp = firstNode;
        while (tmp.next != null) {
            tmp = tmp.next;
        }
        if (tmp.next == null) {
            tmp.next = new Node<K,V>(hash, temporary.key, temporary.value);
        }
    
    }
    public void rehash_1(int index, Node<K, V> [] newTable) {
        //有的会留在原地，有的会移动扩容的长度
        //怎么判断他是留在原地还是移动一个扩容的长度。
        //重新计算hash值，如果新index等于旧的index 则放到新数组的index ，如果不等与则需要加一个扩容的大小
         //相当于对原先哈希表中每一个有效节点 进行 重哈希的过程
         Node<K,V> currentNode = table[index];
         if(currentNode == null){
             return;
         }
 
         Node<K,V> lowHead = null; //低位的头
         Node<K,V> lowTail = null;//低位的尾
         Node<K,V> highHead = null;//高位的头
         Node<K,V> highTail = null;//高位的尾
 
         while(currentNode != null){
             //遍历index位置的所有节点
             int newIndex = hash(currentNode.key) & (newTable.length-1);
             if(newIndex == index){
                 //当前节点链到lowTail之后
                 if(lowHead == null){
                     lowHead = currentNode;
                     lowTail = currentNode;
                 }else{
                     lowTail.next = currentNode;
                     lowTail = lowTail.next;
                 }
             }else{
                 //当前节点链到highTail之后
                 if(highHead == null){
                     highHead = currentNode;
                     highTail = currentNode;
                 }else{
                     highTail.next = currentNode;
                     highTail = highTail.next;
                 }
             }
             currentNode = currentNode.next;
         }
         //要么在原位置 （低位位置）
         if(lowHead != null && lowTail != null){
             lowTail.next = null;
             newTable[index] = lowHead;
 
         }
 
         //要么跑到原位置 + 扩容前长度 （高位位置）
         if(highHead != null && highTail != null){
             highTail.next = null;
             newTable[index + table.length] = highHead;
         }
     }
    }
    public void tostring() {
        for (int i=0; i<size;++i) {
            Node<K, V> tmp = table[i]; 
            System.out.println("---------------------------");
            while (tmp != null && tmp.next!=null) {
                System.out.println(tmp.key+"的值："+tmp.value);
                System.out.println("+++++++++++++++++++++++++++");
            }
        }
    }
    
}

