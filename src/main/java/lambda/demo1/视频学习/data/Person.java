package lambda.demo1.视频学习.data;

/**
 * @Description:
 * @Author: MJ
 * @Date: Created in 2021/4/27
 */
public class Person<E> {
    private String name;
    private int age;

    public Person() {
        super();
        System.out.println("person类的无参构造方法执行了");
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
        System.out.println("person类的有参构造方法执行了");
    }

//    public Person(PersonCreate3<? super E> personCreate3){
//        System.out.println(personCreate3.getPerson("dsf"));
//    }
//    public void  ddd(PersonCreate3<? super E> personCreate3){
//        personCreate3.getPerson();
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
