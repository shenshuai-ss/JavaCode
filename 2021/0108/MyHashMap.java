
public class MyHashMap<K, V>{
    private int size; //表示map中有多少个键值对
    private Node<K, V>[] table;

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
    
    MyHashMap(int capacity) {
        table = new Node[capacity];
    }
    
    public int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }
    
    public void put(K key, V value){
        //key->Hash值->index
        int hash = hash(key);//散列码
        int index = table.length-1 & hash;
    
            //当前index位置不存在节点
        Node<K, V> firstNode = table[index];
        if(firstNode == null){
            //table[index]位置不存在节点 直接插入
            table[index] = new Node<K,V>(hash, key, value);
            size++;
        }
        //key不允许有重复的
        //查找当前链表中key是否已经存在
        if(firstNode.key.equals(key)){
            firstNode.value = value;
        } else{
            //遍历当前链表
            Node<K, V> tmp = firstNode;
            while(tmp.next != null && !tmp.key.equals(key)){
                tmp = tmp.next;
            }
    
            if (tmp.key.equals(key)) {
                tmp.value = value;
            }
    
            if(tmp.next == null){
                //表示最后一个节点之前的所有节点都不包含key
                if(tmp.key.equals(key)){
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
            int hash = hash(key);//散列码
            int index = table.length-1 & hash;
            Node<K, V> firstNode = table [index];
            if (firstNode == null) {
                return null ;
            }
            Node<K, V> tmp = firstNode; 
            while (tmp.next != null) {
                if (tmp.key.equals(key)) {
                    return tmp.value;
                }
                tmp = tmp.next;
            }
            return null;
            // while(tmp.next == null && !tmp.key.equals(key)) {
            //     tmp = tmp.next;
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
            while(tmp.next != null) {
                if (tmp.next.next.key.equals(key)) {
                    tmp.next.hash = 0;
                    tmp.next.key = null;
                    tmp.next = tmp.next.next;
                    tmp.next.next = null;
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
            Node<K, V>[] newTable = new Node[table.length*2];
           //重哈希
            for(int i=0; i<table.length; i++){
                rehash(i, newTable);
            }
            this.table = newTable;
        }
    
       public void rehash(int index, Node<K,V>[] newTable){
    
    }
    
}

