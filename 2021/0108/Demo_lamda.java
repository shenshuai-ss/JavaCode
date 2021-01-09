package multithread;

public class Demo_lamda {
    public static void main(String[] args){
        //lamda 表达式的使用，可以直接重写接口，
        Love love = a->System.out.println("zhansnalove:"+a);
        love.love("lisi");
        
    }
}
interface Love{
    void love (String a);
}