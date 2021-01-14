package multithread;

public class Demo_stop implements Runnable {
    private boolean flag = true;
    @Override
    public void run() {
        int i = 0;
        while(flag) {
            System.out.println("run......Thread"+ i++);
        }
    }
    public void stop() {
        this.flag = false;
    }
    public static void main(String[] args) {
        Demo_stop stop = new Demo_stop();
        new Thread(stop).start();
        for (int i=0; i<1000; i++) {
            System.out.println("main"+i);
            if(i==900) {
                stop.stop();
                System.out.println("该线程已经停止");
            }
        }
    }
}
