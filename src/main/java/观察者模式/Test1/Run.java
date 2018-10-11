package 观察者模式.Test1;

/**
 * @Description:
 * @Author: MJ
 * @Date: Created in 2018/10/11
 */
public class Run {
    public static void main(String[] args){
        News news = new News();
        new MyNews(news);

        news.setName("dfssf");
        news.show();

    }
}
