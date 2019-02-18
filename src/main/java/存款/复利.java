package 存款;

import java.util.Scanner;

/**
 * @Description:
 * @Author: MJ
 * @Date: Created in 2019/2/18
 */
public class 复利 {
    //每月存款数
    private double monthSave = 0;
    //年利率
    private double yearRate = 0;
    //存款年数
    private int yearNum = 0;
    //存入的本金
    private double sumSave = 0;
    //总金额
    private double sumMoney = 0;
    //总利息
    private double sumInterest = 0;


    public static void main(String[] args) {
        new 复利().start();
    }


    public void start() {
        System.out.print("请输入每个月存款数：");
        Scanner scan = new Scanner(System.in);
        monthSave = scan.nextDouble();
        sumSave = monthSave;
        sumMoney = monthSave;

        System.out.print("请输入年利率：");
        yearRate = scan.nextDouble();


        System.out.println("请输定投年限：");
        yearNum = scan.nextInt();
        printDetail();
    }

    public void printDetail() {
        for (int i = 1; i <= yearNum * 12; i++) {
            sumInterest += sumMoney * yearRate / 12;

            System.out.print("第" + i + "个月利息" + sumMoney * yearRate / 12 + "  ");
            sumMoney = sumSave + sumInterest;
            System.out.print("   第" + i + "个月的本金加利息" + sumMoney + "  即 " + year(i) + "年 " + month(i) + "月");
            System.out.println("  第" + i + "个月总利息：" + sumInterest + "  第" + i + "个月本金：" + sumSave);
            sumSave += monthSave;
            //临时
            sumMoney += monthSave;
        }
    }


    public int year(double n) {
        return (int) n / 12;
    }

    public int month(double n) {
        return (int) n % 12;
    }
}
