package 存款;

import java.util.Scanner;

/**
 * @Description: 利滚利
 * @Author: MJ
 * @Date: Created in 2018/7/4
 */
public class 存款 {
    double lixi = 0.03;
    double average_month_money = 12000;
    double result = 0;
    double sum_lixi = 0;
    double aim = 0;
    int n = 1;


    public static void main(String[] args) {
        new 存款().shru();

    }


    public void shru() {
        System.out.print("请输入目标存款金额：");
        Scanner scan = new Scanner(System.in);
        aim = scan.nextDouble();

        result = average_month_money;

        while (calc() < aim) {
            result += average_month_money;
            n++;
        }
        System.out.println("目标存款" + aim + "年利率" + lixi + "   每月存" + average_month_money + "  一共需要" + n + "个月,即 " + year(n) + "年" + month(n) + " 月");
        System.out.println(n + "个月总利息" + sum_lixi);

    }

    public double calc() {
        System.out.print("第" + n + "个月利息" + result * lixi / 12 + "  ");
        sum_lixi += result * lixi / 12;
        result += result * lixi / 12;
        System.out.print("   第" + n + "个月的本金加利息" + result + "  即 " + year(n) + "年 " + month(n) + "月");
        System.out.println("  第" + n + "个月总利息：" + sum_lixi + "  第" + n + "个月本金：" + (result - sum_lixi));
        return result;
    }

    public int year(double n) {
        return (int) n / 12;
    }

    public int month(double n) {
        return (int) n % 12;
    }


}

