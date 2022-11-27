package lambda.demo1.视频学习.exercise;

import lambda.demo1.视频学习.data.Person;

import java.util.TreeSet;

/**
 * @Description:
 * @Author: MJ
 * @Date: Created in 2021/4/27
 */
public class Exercise2 {
    public static void main(String[] args) {
//        TreeSet<Person> list = new TreeSet<>(Comparator.comparingInt(Person::getAge));
//        TreeSet<Person> list = new TreeSet<>((o1, o2) -> o2.getAge() - o1.getAge());
//        TreeSet<Person> list = new TreeSet<>((o1, o2) -> o2.getAge() - o1.getAge());
        TreeSet<Person> list = new TreeSet<>((o1, o2) -> {
            // treeset直接用上面一种，2个9只会有一个
            if (o1.getAge() > o2.getAge()) {
                return -1;
            } else {
                return 1;
            }
        });
//        TreeSet<Person> lis2 = new TreeSet<>((o1, o2) -> o1.getAge() - o2.getAge());
        list.add(new Person("a", 10));
        list.add(new Person("b", 17));
        list.add(new Person("c", 14));
        list.add(new Person("d", 8));
        list.add(new Person("f", 9));
        list.add(new Person("f", 9));

        list.forEach(person -> System.out.println(person.getAge()));
    }
}
