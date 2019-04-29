package 集合.排序;

import java.util.*;

public class Student implements Comparable<Student> {
    private String name;
    private Integer age;
    private int score;

    public Student(String name, Integer age) {
        // TODO Auto-generated constructor stub
        this.name = name;
        this.age = age;
    }

    public Student(String name, Integer age, int score) {
        this.name = name;
        this.age = age;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public int compareTo(Student o) {
        //1、先按年纪由小到大
        if (this.age > o.getAge()) {
            return 1;
        } else if (this.age < o.getAge()) {
            return -1;
        } else {    //如过相等，再按照分数由大到小
            if (this.score > o.score) {
                return -1;
            } else if (this.score < o.score) {
                return 1;
            } else {
                return 0;
            }
        }
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        List<Student> list = new ArrayList<Student>();
        Student s1 = new Student("T-F", 18);
        Student s2 = new Student("H胡歌", 28);
        Student s3 = new Student("Z周润发", 50);
        Student s4 = new Student("M梅兰芳", 100);
        Student s5 = new Student("M梅兰芳2", 100, 10);
        Student s6 = new Student("M梅兰芳3", 100, 20);
        list.add(s1);
        list.add(s4);
        list.add(s3);
        list.add(s2);
        list.add(s3);
        list.add(s4);
        list.add(s5);
        list.add(s6);


        System.out.println("******第一种： 在对象类中实现接口 comparable  ************");

        System.out.println("------默认排序（按年纪）-------");
        Collections.sort(list);
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            Student s = (Student) iterator.next();

        }
        for (Student student : list) {
            System.out.println(student.getName() + " " + student.getAge() + " " + student.getScore());
        }

        System.out.println("------倒序排序-------");
        Comparator comparator = Collections.reverseOrder();
        Collections.sort(list, comparator);
        Iterator iterator_reverse = list.iterator();
        while (iterator_reverse.hasNext()) {
            Student s = (Student) iterator_reverse.next();
            System.out.println(s.getName() + " " + s.getAge() + " " + s.getScore());
        }

        System.out.println("******第二种： 编写比较器 实现comparator,对象类不需要实现comparable接口  ************");

        System.out.println("------根据姓名排序-------");
        Collections.sort(list, new Comparator<Student>() {
                    @Override
                    public int compare(Student o1, Student o2) {
                        // TODO Auto-generated method stub
                        return o1.getName().compareTo(o2.getName());   //字符串 compareTo  离职状况
                    }
                }
        );
        Iterator iterator_name = list.iterator();
        while (iterator_name.hasNext()) {
            Student s = (Student) iterator_name.next();
            System.out.println(s.getName() + " " + s.getAge() + " " + s.getScore());
        }


        //按照scor由大到小排序
        System.out.println("------按照scor有大到小排序-------");
        Collections.sort(list, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.getScore() > o2.getScore() ? -1 : (o1.getScore() == o2.getScore() ? 0 : 1);
            }
        });

        for (Student student : list) {
            System.out.println(student.getName() + " " + student.getAge() + " " + student.getScore());
        }


        /*************************字符串长短排序***********************/
        System.out.println("*************************字符串长短排序***********************");
        String aa = "002001004001001000,002001004001001001001";
        String[] arrays = aa.split(",");
        List<String> stringList = Arrays.asList(arrays);
        for (String s : stringList) {
            System.out.println(s);
        }
        System.out.println("排序后");
        Collections.sort(stringList, new Comparator<String>() {
            @Override
            public int compare(String var1, String var2) {
                if (var1.length() > var2.length()) {
                    return 1;
                } else if (var1.length() == var2.length()) {
                    return 0;
                } else {
                    return -1;
                }

            }
        });
        for (String s : stringList) {
            System.out.println(s);
        }



        List<String> result = new ArrayList<>();

        System.out.println("过滤");
        for (int i = 0; i < arrays.length; i++) {
            boolean flag = true;
            for (int j = 0; j < arrays.length ; j++) {
                if (arrays[i].indexOf(arrays[j]) ==0 && !arrays[i].equals(arrays[j])) {
                    flag = false;
                    break;
                }
            }
            if(flag){
                result.add(arrays[i]);
            }
        }
        for (String s : result) {
            System.out.println(s);
        }

//        System.out.println("002001001001".indexOf("002001001001001"));
//        System.out.println("002001001001001".indexOf("002001001001"));
//        System.out.println("002001003002001010".indexOf("002001010"));

    }
}