package lambda.demo1.视频学习.exercise;

import lambda.demo1.视频学习.data.Person;

import java.util.ArrayList;

/**
 * @Description:
 * @Author: MJ
 * @Date: Created in 2021/4/27
 */
public class Exercise1 {
    //集合排序
    public static void main(String[] args) {
        //需求，已知在一个ArrayList中有若干个person对象，将这些person对象按照年龄进行降序排序；
        ArrayList<Person> list = new ArrayList<>();
        list.add(new Person("a", 10));
        list.add(new Person("b", 17));
        list.add(new Person("c", 14));
        list.add(new Person("d", 8));
        list.add(new Person("f", 9));
        list.add(new Person("f", 9));
//        list.sort((o1, o2) -> {
//            return o2.getAge()-o1.getAge();
//        });
        list.sort((o1, o2) -> o2.getAge() - o1.getAge());
        list.forEach(person -> System.out.println(person.getAge()));
    }
}
