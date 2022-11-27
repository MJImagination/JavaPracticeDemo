package lambda.demo1.视频学习.syntax;

import lambda.demo1.视频学习.data.Person;

/**
 * @Description:
 * @Author: MJ
 * @Date: Created in 2021/4/27
 */
public class Syntax3 {
    public static void main(String[] args) {
        PersonCreater creater = () -> {
            return new Person();
        };

        PersonCreater creater1 = () -> new Person();


        //构造方法的引用：
        PersonCreater creater2 = Person::new;
        Person a = creater2.getPerson();


        PersonCreate2 personCreate2  = Person::new;
        Person b = personCreate2.getPerson("b", 29);


        print(new PersonCreater() {
            @Override
            public Person getPerson() {
                return null;
            }
        });

    }

    public static void print(PersonCreater personCreater){
        personCreater.getPerson();
    }
}

//需求:
interface PersonCreater {
    Person getPerson();
}

interface PersonCreate2{
    Person getPerson(String name, int age);
}
