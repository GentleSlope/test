package question3;

import java.util.Scanner;
        /**
         * @Description: 可以功能分别是时间转时间戳，和时间戳转时间;
         * @Param:
         * @return:
         * @auther: thankslana
         * @Date: 2018/12/23
         * @Time 17:53
        **/
public class question3 {

    public static void main(String[] args) {
        int year;//声明时间
        int month;
        int day;
        int hour;
        int minute;
        int second;
        long stamp;
        Scanner input = new Scanner(System.in);
        System.out.println("please input the time:");
        year = input.nextInt();//年
        month = input.nextInt();//月
        day = input.nextInt();//日
        hour = input.nextInt();//时
        minute = input.nextInt();//分
        second = input.nextInt();//秒
        System.out.println("\n" + "Please enter a timestamp:");
        stamp = input.nextLong();//时间戳
        TimeStamps tim = new TimeStamps(year, month, day, hour, minute, second);//实例化对象
        System.out.println(year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + second + " <-> GMT " + tim.timeStamp(year, month, day, hour, minute, second, tim.getFlatYear(), tim.getLeapYear()));
        System.out.println(stamp + " <-> GMT " + tim.stampTime(stamp, tim.getFlatYear(), tim.getLeapYear()));

    }
}