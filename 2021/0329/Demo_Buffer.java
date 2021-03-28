package FileReader;

import java.io.*;

/**
         *
         * 缓冲流
         * 为了提高IO流的读写速度
         * 按照流总的内容分为字节缓冲流和字符缓冲流
         *
         *
         *  字节缓冲输入流 BufferInputStream
         *  字节缓冲输出流 BufferOutputStream
         *
         *  缓冲流内部都有一个缓冲区(内存里面)，通过缓冲区进行读写，从外部访问到内部访问是内存到内存的过程，可以提高IO的速度
         *
         *  课堂练习：
         *  多种方式拷贝一本书。
         *  1）采用节点流，一次一字节的拷贝
         *  2）采用节点流，一次多个字节的拷贝
         *  3）采用处理流，一次一个字节的拷贝
         *  4）采用处理流，一次多个字节的拷贝
         *
         */
public class Demo_Buffer {

    //4）采用处理流，一次多个字节的拷贝
    public static void copy4(String src, String dest) {
        Long start = System.currentTimeMillis();
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            bis = new BufferedInputStream(new FileInputStream(src));
            bos = new BufferedOutputStream(new FileOutputStream(dest));
            byte [] chars = new byte[1024];
            int tmp;
            while ((tmp = bis.read(chars)) != -1) {
                bos.write(chars, 0, tmp);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bis.close();
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        long end  = System.currentTimeMillis();
        System.out.println("4）采用处理流，一次多个字节的拷贝所需要的时间："+ (end - start)+"纳秒");
    }


    //3）采用处理流，一次一个字节的拷贝
    public static void copy3(String src, String dest) {
        long start = System.currentTimeMillis();
        //使用缓冲流一次一个字节的拷贝
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            //缓冲输入流
            bis = new BufferedInputStream(new FileInputStream(src));
            //缓冲输出流
            bos = new BufferedOutputStream(new FileOutputStream(dest));
            //读取数据并输出
            int tmp;
            while ((tmp = bis.read()) != -1) {
                bos.write(tmp);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bis.close();
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        long end  = System.currentTimeMillis();
        System.out.println("3）采用处理流，一个字节一个字节的拷贝所需要的时间："+ (end - start)+"纳秒");
    }


    //1）采用节点流，一次一字节的拷贝
    public static void copy1(String src, String dest) {
        long start = System.currentTimeMillis();
        //采用节点流，一次一个字节的拷贝
        FileInputStream fos = null;
        FileOutputStream bos = null;
        try {
            //创建节点流
            fos = new FileInputStream(src);
            bos = new FileOutputStream(dest);
            int tmp;
            while ((tmp = fos.read()) != -1) {
                bos.write(tmp);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        long end  = System.currentTimeMillis();
        System.out.println("1）采用节点流，一个字节一个字节的拷贝所需要的时间："+ (end - start)+"纳秒");
    }


    // 2）采用节点流，一次多个字节的拷贝
    public static void copy2(String src, String dest) {
        long start = System.currentTimeMillis();
        FileInputStream fos = null;
        FileOutputStream bos = null;
        try {
            fos = new FileInputStream(src);
            bos = new FileOutputStream(dest);
            byte[] chars = new byte[1024];
            int tmp;
            while ((tmp = fos.read(chars)) != -1) {
                bos.write(chars,0,tmp);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        long end  = System.currentTimeMillis();
        System.out.println("2）采用节点流，一次多个字节的拷贝所需要的时间："+ (end - start)+"纳秒");
    }
    public static void main(String[] args) {
        copy1("Java设计模式（刘伟）.pdf","1.pdf");
        copy2("Java设计模式（刘伟）.pdf","2.pdf");
        copy3("Java设计模式（刘伟）.pdf","3.pdf");
        copy4("Java设计模式（刘伟）.pdf","4.pdf");
        //1）采用节点流，一个字节一个字节的拷贝所需要的时间：58962纳秒
        //2）采用节点流，一次多个字节的拷贝所需要的时间：100纳秒
        //3）采用处理流，一个字节一个字节的拷贝所需要的时间：197纳秒
        //4）采用处理流，一次多个字节的拷贝所需要的时间：25纳秒
    }
}
