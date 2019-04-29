package 多线程.生产者消费者.myTest;

import java.util.Date;

/**
 * @Description:
 * @Author: MJ
 * @Date: Created in 2019/4/29
 */
public class Product {
    private String name;
    private Date date;

    public Product(String name, Date date) {
        this.name = name;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", date=" + date +
                '}';
    }


    public Product(String name) {
        this.name = name;
    }
}
