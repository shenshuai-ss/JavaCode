import java.io.*;

public class Demo_011 {
   /**
    *
    * 转换流：
    *   InputStreamReader
    *   字节流通向字符流的桥梁  使用指定的编码表读取到字节后会将其解析为字符
    *   OutputStreamWriter
    *   字符流通向字节流的桥梁  使用指定的编码表将字符转字节，使用字节流将这些字节写入
    *
    *
    *    处理流
    *    基于节点流之上做了一层封装
    *
    * */
   public static void main(String [] args){
      //字符转字节
      FileOutputStream fos = null;
      OutputStreamWriter osw = null;
      try {

         //节点流
         fos = new FileOutputStream("a.txt");
         //处理流
         osw = new OutputStreamWriter(fos,"utf-8"); //字符转字节
         osw.write("你好中国！"); //写入字符
      } catch (FileNotFoundException e) {
         e.printStackTrace();
      } catch (UnsupportedEncodingException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      } finally {
         try {
         osw.close();//先关闭转换流
         fos.close();//在关闭写入流
         } catch (IOException e ) {
            e.printStackTrace();
         }
      }
      //字节转字符
//      FileInputStream fis = null;
//      InputStreamReader isr = null;
//      try {
//         //创建节点流
//         fis = new FileInputStream("a.txt");
//         //创建处理流
//         isr = new InputStreamReader(fis,"utf-8"); //utf-8 中文占3个字节 ，gbk中文占2个字节
//         int tmp;
//         while((tmp = isr.read()) != -1) {
//            System.out.println((char) tmp);
//         }
//      } catch (FileNotFoundException e) {
//         e.printStackTrace();
//      } catch (UnsupportedEncodingException e) {
//         e.printStackTrace();
//      } catch (IOException e) {
//         e.printStackTrace();
//      } finally {
//         try {
//            fis.close();
//            isr.close();
//         } catch ( IOException e) {
//            e.printStackTrace();
//         }
//      }

   }
}
