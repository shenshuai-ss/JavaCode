/**
 * 课堂练习：
 * 模拟银行的叫号系统：有三个柜台，随机叫号，且三个柜台叫的号码不能重复。叫的号码依次增加上限200。
 *
 */
class Counter extends Thread {
    private String name;//窗口名
    public static int count = 0;//计数器
    public Counter(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        while (count < 200) {
            count++;
            System.out.println("请" + count + "到" + name);
        }
    }
}

/**
 * start方法的剖析
 *   public synchronized void start() {
 * if (threadStatus != 0)//判断线程状态是否为0
 *      throw new IllegalThreadStateException();
 *       group.add(this);//当前线程加入到线程组中
 *
 *       boolean started = false;
 *        ry {
 *            start0();//是一个native 本地方法，调用run执行该线程
 *            started = true;
 *        } finally {
 *            try {
 *               if (!started) {
 *                   group.threadStartFailed(this);
 *                }
 *             } catch (Throwable ignore) {
 *            }
 *         }
 *     }
 *
 *     start方法首先是将线程加入一个线程组中，这时线程进入Runnable就绪状态start获取
 *     cpu资源执行程序自定义的run方法
 *     run方法就是一个普通的方法，不会启动一个新的线程，只是执行体
 *
 *     注意：线程在调用start方法的时候会有一个threadStatus ，如过不等于0说明已经启动
 *     不能沟重复启动，会报:Exception in thread "main" java.lang.IllegalThreadStateException
 */
class Counter2 implements Runnable{
    public static int count = 0;//计数器
    @Override
    public void run() {
        while (count < 20) {
            count++;
            System.out.println(Thread.currentThread().getName()+ "请到" + count );
        }
    }
}
public class Demo_02 {
    public static void main(String[] args) {
        Counter2 counter2 =new Counter2();
        Thread t1 = new Thread(counter2,"柜台一");
        Thread t2 = new Thread(counter2,"柜台二");
        Thread t3 = new Thread(counter2,"柜台三");
        Thread t4 = new Thread(counter2,"柜台四");
        t1.start();
        t1.start();
        t2.start();
        t3.start();
        t4.start();
//        Thread thread1 = new Counter("窗口1");
//        thread1.start();
//        Thread thread2 = new Counter("窗口2");
//        thread2.start();
//        Thread thread3 = new Counter("窗口3");
//        thread3.start();

    }
}
