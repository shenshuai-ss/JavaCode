import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

/**
 * 字节输入流：
 * 主要用于读取诸如图片视频之类的数据文件
 * 字节输出流：
 * 将字节码写入取诸如图片视频之类的数据文件。
 *
 * IO 流 & 字符流
 * IO 是我们的目的 ，达到这个目的，需要使用流机制
 * 字符流是什么？
 * 1》字符流可以读取字符的IO流
 * 2》字符流读取字符， 先读取字节数据，然后转为字符
 * 3》字符流写入字符，先把字符转为字节写入。
 *
 *
 * Reader 字符输入流（抽象类）  & FileReader
 * 构造函数：
 * public FileReader(String fileName)  文件名
 * public FileReader(File file)  文件对象
 *底层 new FileInputStream对象  继承自InputStreamReader 转换流   （字节转字符的过程）
 * 成员方法：
 * read ()  读取  长用三种方法 ：读取字符 （-1为结束标志）
 * read(char cbuf[]) （读到缓冲数组里面） （—1为结束标志）
 * 底层由read(char cbuf[], int offset（数组起始位置）, int length （数组长度）) 实现
 *
 * 知识点：
 * 1。read 读取的是字符、
 * 2。一个字符可能回去占用一个字节 两个字节，或者三个字节，（不同平台不同编码方式，占用字节大小不一样）
 * 3。编码方式 ASCII GBK unicode  （不同编码方式对中文或者英文占的字节数是不一一样的）
 * 4。读取放方式与FileInputStream 字节流差不多
 *
 * 课堂练习：读取a.txt中的内容并输出
 *
 *面试题：
 * 1。谈谈java IO 中的长见类 （字节流，字符流，接口/抽象类，实现类，那个方法有阻塞那个没有） （没有数据可以写入程序停止）】
 * 2。字符流字节流的区别 （音频，视频，图片，使用字节流，字符文件使用字符流比较好一点）
 *      概念 ，使用场景，字符流基于字节流实现
 *
 * */
public class Demo_FileReader01 {
    public static void main(String[] args) {
//        FileReader reader = null;
//        try {
//            //创建一个字符输入流
//            reader = new FileReader("a.txt");
//            //调用read 读取
//            int read ; //读取字符的编码值
//            while ((read = reader.read()) != -1) {
//                System.out.print((char)read); //转字符输出
//            }
//           //缓冲数组的方式
////            char[] chars = new char[32];
////            reader.read(chars);
////            System.out.println(Arrays.toString(chars));
//        } catch (FileNotFoundException e) {
//            //捕获异常
//            System.out.println("读取异常");
//            e.printStackTrace();//打印错误的栈信息
//        } catch (IOException e) {
//            System.out.println("异常");
//            e.printStackTrace();
//        } finally {
//            //善后工作：关闭流资源。
//            //最后都会执行
//            try {
//                reader.close();
//            } catch (IOException e) {
//                System.out.println("流关闭异常");
//                e.printStackTrace();
//            }
//        }
/**
 *
 * Writer 抽象类字符输出流 & FileWriter
 * 构造函数：
 *  public FileWriter(String fileName) throws IOException （传文件名）
 *  public FileWriter(String fileName, boolean append) throws IOException （传文件名 + 追加（不懂））
 *  public FileWriter(File file) throws IOException （ 文件对象）
 *
 * 成员方法：
 * public void write(String str) (  写入字符串)
 * public void write(String str, int off, int len) （确定字符串的起始和结束位置写入）
 * public void write(char cbuf[]) throws IOException （数组的形式）
 * abstract public void write(char cbuf[], int off, int len) throws IOException; （数组加位置的形式）
 * public void write(int c) throws IOException （编码值的形式。）
 *
 *
 * 课堂练习：
 * 往b.txt中写入字符
 * */

        //字符输出流
        FileWriter writer = null;
        try {
            writer = new FileWriter("b.txt");
            writer.write("中国");
        } catch ( FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
                //writer.flush(); //刷新操作 。会先保存在数组中（内存）刷新后或者数组到达一定或者关闭流资源 ，才写入磁盘。
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
