package hashMap;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
class Student { 
    private String name;
    private int age;
    Student() {

    }
    Student(String name,int age) {
        this.name = name;
        this.age = age;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name=name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int a) {
        this.age = a;
    };
    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        Student student = (Student) obj;
        return (this.name.equals(student.name) && this.age == student.age);
    }
    @Override
    public int hashCode() {
        if (name != null) {
            return name.hashCode() + age;
        }
        return 0;
    }    
}
public class Demo_01 {
    public static void main(String[] args) {
        HashMap<Student, String> map = new HashMap<>();
        map.put(new Student("张三", 20), "hdjhshjh");
        map.put(new Student("张三", 20), "tulun111");
        //e.hash==hash && e.key == key || e.key.equals(key)
        map.put(new Student("李四", 25), "jdshjd");
        map.put(new Student("王五", 22), "teiehff");
        map.put(new Student("小明", 18), "okeejjej");
        map.put(new Student("小李", 19), "fds1");
        Iterator<Map.Entry<Student, String>> itr = map.entrySet().iterator();
        while(itr.hasNext()) {
           Map.Entry<Student,String> next = itr.next();
           System.out.println(next.getKey()+"::"+next.getValue());
        }
    }
}

