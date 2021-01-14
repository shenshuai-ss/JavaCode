package hashMap;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
/**
 * WeakHashMap Java中四大引用： 强引用 A a = new A(); //a是强引用 只要是强引用，GC就不会回收被引用的对象
 *
 * 软引用SoftReference 一般用户实现Java对象的缓存，缓存可以有可以没有，一般将有用但是非必须的 对象用软引用关联
 * 只要是软引用关联的对象，在Java发生内存溢出异常之前，会将这些对象列入要 回收的范围，如果回收之后发现内存还是不够，才会抛出OOM异常 map -》
 * SoftReference -》 SoftReference.get()
 *
 * 弱引用 WeakReference 弱引用是用来一些非必须的对象，比软引用更弱一些
 * 只要是被弱引用关联的对象，只能够生存到下一次垃圾回收之前，一旦发生垃圾回收， 无论当前内存是否够用，都会回收掉被弱饮用关联的对象
 *
 * 虚引用 PhantomReference 别名幽灵引用 最弱的引用关系，一个对象是否具有虚引用的存在，完全是不会对其生命
 * 周期产生影响，也无法通过虚引用获取一个对象的实例，它存在的唯一目的就是在对象被 垃圾回收之后收到一个系统通知
 *
 * 特殊的HashMap，WeakHashMap的键是弱引用对象，只能存活到下一次垃圾回收之前
 *
 *
 * WeakhashMap与HashMap之间的区别和联系： 1）类的继承关系 2）put方法逻辑/get/remove
 * null值的处理、插入节点的方式、求散列码的方式、扩容的方式、expungexxx等 3）使用场景 WeakHashMap的键是弱引用关联对象
 *
 * HashTable与HashMap之间的区别和联系？
 *
 */
class MyArray {
    byte[] b = new byte[3*1024*1024];
}
class Test {

}
public class Deme_02 {
    public static void main(String[] args) {
        // //软引用
        // //引入队列
        // ReferenceQueue<MyArray> queue = new ReferenceQueue<>();
        // //创建软引用对象
        // SoftReference<MyArray> softReference = new SoftReference<>(new MyArray(),queue);
        // //获取软引用对象
        // System.out.println(softReference.get());
        // //判断软引用是否被回收。回收则返回true.否则false
        // System.out.println(softReference.isEnqueued());
        // //模拟gc回收
        // //如果内存不够则被回收，否则不会。
        // System.gc();
        // byte[] b = new byte[3*1024*1024];
        // System.out.println("==================");
        // System.out.println(softReference.get());
        // System.out.println(softReference.isEnqueued());
        // System.out.println("==============================================");
        //弱引用
        // ReferenceQueue<Test> test = new ReferenceQueue<>();
        // WeakReference<Test> weak = new WeakReference<Test>(new Test(),test);
        // System.out.println(weak.get());
        // System.out.println(weak.isEnqueued());
        // System.out.println("==================");
        // //只能存活到一次gc 前
        // System.gc();
        // System.out.println(weak.get());
        // System.out.println(weak.isEnqueued());
        //虚引用
        ReferenceQueue<Test> queue = new ReferenceQueue<>();
        PhantomReference<Test> phantomReference =new PhantomReference<Test>(new Test(), queue);
        System.gc();
        if (phantomReference.isEnqueued()) {
            System.out.println("该对象已经被回收 ");
        }else {
            System.out.println("该对象没有被回收 ");
        }
    }
}
