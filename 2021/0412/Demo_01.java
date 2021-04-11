import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

    /**
     * 多线程
     * 1。线程和进程的认识
     * 进程：一段正在运行的程序
     * 线程：一个程序在运行中可以执行多个任务，称之为线程
     *
     *2）进程是程序执行过程中资源分配和管理的基本单位
     *  线程是cpu执行的最小单位。
     *  进程有自己独立的地址空间，每启动一个进程，系统就会分配地址空间
     *  进程可以有多个线程，各个线程之间共享程序的内存空间
     *
     * 为什么出现线程？
     * 每个进程有自己的独立地址空间，多并发请求，为每一个请求创建一个进程
     * 导致系统开销大，用户请求效率低
     *
     * 串行 -》 批量处理 -》进程 -》 线程
     *
     * 面试题：
     * 多线程和多进程有哪些区别？
     * 1）每个进程拥有自己独立的变量，线程共享数据
     *      线程之间的通信相比进程之间的通信更有效 更容易
     * 2）线程相比于进程创建/销毁开销 更小
     * 3）进程是资源分配的最小单位，线程是cpu调度的最小单位
     * 4）多进程更加健壮 ，多线程程序只要有一个线程挂掉，对其共享资源的
     * 其他线程也会产生音响
     * 5）如何追求速度，选择线程
     * 如果频繁的创建和销毁，选择线程
     * 如果追求系统更加稳定，选择进程
     * 线程是轻量级的进程
     *
     * JVM -》一个进程
     * 2。线程的创建
     *
     * 1）继承Thread类，重写run（）方法
     *
     * 2) 实现Runnable接口，重写run（）方法
     *
     * 3）可以使用匿名内部类来实现
     *
     * 4) 实现Callable接口，重写Call方法
     *  操作步骤：通过Callable和FutureTask创建线程
     *  a.创建Callable接口的实现类，重写call方法 （线程执行体）
     *  b.创建Callable实例类的实例，使用FutureTask包装该实例
     *  c.将FutureTask实例作为参数创建线程对象
     *  d.启动线程
     *  e.调用FutureTask的get 方法获取子线程的执行结果
     *
     *  面试题：
     *  Callable和Runnable接口的区别
     *  1）直观上 在底层上Callable实现的是call的方法 Runnable 实现的是run 方法 。Callable 有返回值可以抛异常，而Runnable没有返回值且不能抛异常
     *  2）使用上的区别：Runnable需要将自己的实例作为参数实例化线程对象，然后调用start启动线程，
     *  而 Callable需要将自己封装为FutureTask 的实例，然后把FutureTask的实例作为参数实例线程对象。然后启动线程
     *  3）使用场景上：callable又返回值 ，而Runnable没有，那些地方需要那些不需要？
     *
     *
     * callable 接口存在Executor框架类中，不Runnable更加强大
     * a.Callable 可以在任务纸吸管结束之后一个返回值
     * b.call 方法可以抛出异常
     * c. 运行Callable任务可以拿到一个Future对象，Future提供了get方法
     * 拿到返回值后（异步 ） 操作
     * 同步：一个线程执行完在一个线程
     * 异步：不需要等待一个线程完了在一个线程，只要不停的轮循FutureTask的对象 看返回值后在做相应的事情 。主线程还可以做其他事情
     *
     * 课堂练习练习边吃饭边看电视
     *
     */
    class MyThread extends Thread {
        @Override
        public void run() {
            while (true) {
                System.out.println("eat food");
            }
        }
   }
   class MyCallable implements Callable<Integer> {
       @Override
       public Integer call() throws Exception {
           int sum = 0;
           for(int i=0; i<10000;i++) {
               sum += i;
           }
           return sum;
       }
   }
public class Demo_01 {
    public static void main(String[] args) {
        //Callable方法
        Callable<Integer> callableTask = new MyCallable();
        FutureTask<Integer> task = new FutureTask<>(callableTask);
        Thread thread = new Thread(task);
        thread.start();

        //接收线程执行后的结果。
        try {
            Integer integer = task.get();
            System.out.println("result" + integer);
        } catch (InterruptedException  e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

//        //extends Thread创建子线程
//        //创建子线程的对象
//       Thread thread= new MyThread();
//       //启动吃饭的thread
//       thread.start();//start -》run
//
//        //implements Runnable创建子线程
//        //创建子线程对象,将Runnable 实例作为参数实例化子线程对象
//        Thread thread = new Thread(new MyThread());
//        //启动吃饭的thread
//         thread.start();//start -》run

        //使用匿名内部类的方法创建线程
//        new Thread() {
//            @Override
//            public void run() {
//                while (true) {
//                    System.out.println("thread -0");
//                }
//            }
//        }.start();
//        //main线程
//        while (true) {
//            System.out.println("watch TV");
//        }
    }
}
