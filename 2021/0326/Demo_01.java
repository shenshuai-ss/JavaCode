import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Demo_01 {
    public static void main(String[] args) throws IOException {
        //方法1 使用缓冲数组
        FileReader reader = null;
        FileWriter writer = null;
        try{
            reader = new FileReader("a.txt");
            writer = new FileWriter("b.txt");
            char [] chars = new char[32];
            int tmp ;
            while ( (tmp = reader.read(chars)) != -1){
            writer.write(chars,0,tmp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            reader.close();
            writer.close();
        }
    }


       // 方法2： 一个字节一个字节的拷贝
//        FileReader reader = null;
//        FileWriter writer = null;
//        try {
//            reader = new FileReader("a.txt");
//            writer = new FileWriter("b.txt");
//            int  tmp ;
//            while ((tmp = reader.read()) !=-1) {
//            writer.write(tmp); // int 转字符
//            }
//        } catch (IOException e) {
//                e.printStackTrace();
//        }finally {
//            reader.close();
//            writer.close();
//        }

}


